package ru.etu.sapr.game;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ru.etu.sapr.net.IpEndPoint;
import ru.etu.sapr.net.JsonContainer;
import ru.etu.sapr.net.UdpClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by Nikita on 26.11.2016.
 */
public class GameClient implements Runnable {

    private final String serverIP = "127.0.0.1";
    private final int serverPort = 9876;


    private SimpleCube cube;

    private UdpClient udpClient;

    private JsonContainer jsonContainer;

    private IpEndPoint serverEP;

    private byte[] receiveData;

    private JSONParser parser;

    private float f;

    private void MakeSetPositionTransaction(JsonContainer container, SimpleCube simpleCube)
    {
        container.clear();
        container.setObjectType("setPosition");
        container.addObject(simpleCube.toJSONObject(), 0);
    }

    private void MakeGetCurrentNumTransaction(JsonContainer container)
    {
        container.clear();
        container.setObjectType("currentNum");
    }

    private void UnpackToContainer(String str, JsonContainer container) throws ParseException
    {
        container.clear();
        //десереиализация
        Object obj = parser.parse(str);
        JSONObject jsonObject = (JSONObject) obj;
        container.Parse(jsonObject);

        System.out.println("Client received: " + jsonObject.toJSONString());
    }


    private int UnpackCurrentNumTransaction(JsonContainer container) throws ParseException
    {
        if (container.getObjectType().contains("currentNum")) {
            JSONObject jsonObject = (JSONObject) parser.parse(container.getObject(0));
            return  ((Long) jsonObject.get("number")).intValue();
        }
        else
            return -1;
    }

    public void run()
    {
        parser = new JSONParser();
        try {
            this.udpClient = new UdpClient(9877);
        }
        catch (SocketException e){
            System.out.println(e.getMessage());
            return;
        }

        this.cube = new SimpleCube();
        try {
            this.serverEP = new IpEndPoint(InetAddress.getByName(serverIP), serverPort);
        }
        catch (UnknownHostException e){
            System.out.println(e.getMessage());
            return;
        }

        this.jsonContainer = new JsonContainer();
        MakeSetPositionTransaction(jsonContainer,cube);

        /*try {
            this.udpClient.Send(this.jsonContainer.toJSONObject().toJSONString().getBytes(),this.serverEP);
            this.receiveData = this.udpClient.Receive();
        }
        catch (IOException e){
            System.out.println(e.getMessage());
            return;
        }

        this.jsonContainer.clear();

        // подготовка
        String sentence = new String(receiveData, 0, receiveData.length);

        //десереиализация
        try {
            parser = new JSONParser();
            Object obj = parser.parse(sentence);
            JSONObject jsonObject = (JSONObject) obj;
            jsonContainer.Parse(jsonObject);
            if (jsonContainer.getObjectType().contains("setPosition")) {
                jsonObject = (JSONObject) parser.parse(jsonContainer.getObject(0));
                cube.Parse(jsonObject);

                System.out.println("Client received: " + jsonObject.toJSONString());
                // TODO:


            } else {
                System.out.println("Type is : " + jsonContainer.getObjectType());
            }
        }
        catch (ParseException e){
            System.out.println(e.getMessage());
            return;
        }*/

        while (true)
        {
            this.Update();
            try {
                Thread.sleep(33);
            }
            catch (InterruptedException ex)
            {

            }
        }
    }

    private void Update()
    {

        this.cube.getTransformation().position.x = f;
        f+= 1.0;

        try {
            //current num
            //sent
            MakeGetCurrentNumTransaction(jsonContainer);
            this.udpClient.Send(this.jsonContainer.toJSONObject().toJSONString().getBytes(),this.serverEP);
            System.out.println("Client sent: " + jsonContainer.toJSONObject().toJSONString());

            //receive
            this.receiveData = this.udpClient.Receive();
            String sentence = new String(receiveData, 0, receiveData.length);
            //unpack
            UnpackToContainer(sentence, jsonContainer);
            //decode
            int num = UnpackCurrentNumTransaction(jsonContainer);
            System.out.println("num ="+num);

            //set  position
            MakeSetPositionTransaction(jsonContainer,cube);
            this.udpClient.Send(this.jsonContainer.toJSONObject().toJSONString().getBytes(),this.serverEP);
            System.out.println("Client sent: " + jsonContainer.toJSONObject().toJSONString());
            //receive
            this.receiveData = this.udpClient.Receive();
            sentence = new String(receiveData, 0, receiveData.length);
            //unpack
            UnpackToContainer(sentence, jsonContainer);
            /*//десереиализация
            parser = new JSONParser();
            Object obj = parser.parse(sentence);
            JSONObject jsonObject = (JSONObject) obj;
            jsonContainer.Parse(jsonObject);*/
            if (jsonContainer.getObjectType().contains("setPosition")) {
                JSONObject jsonObject = (JSONObject) parser.parse(jsonContainer.getObject(0));
                cube.Parse(jsonObject);

                //System.out.println("Client received: " + jsonObject.toJSONString());
                // TODO:


            } else {
                System.out.println("Type is : " + jsonContainer.getObjectType());
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
            return;
        }
        catch (ParseException e){
            System.out.println(e.getMessage());
            return;
        }
    }
}
