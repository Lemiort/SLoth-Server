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

    public void run()
    {
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
        this.jsonContainer.setObjectType("setPosition");
        this.jsonContainer.addObject(this.cube.toJSONObject(), 0);

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
        this.jsonContainer.clear();

        this.cube.getTransformation().position.x = f;
        f+= 1.0;


        this.jsonContainer.setObjectType("setPosition");
        this.jsonContainer.addObject(this.cube.toJSONObject(), 0);

        try {
            this.udpClient.Send(this.jsonContainer.toJSONObject().toJSONString().getBytes(),this.serverEP);
            System.out.println("Client sent: " + jsonContainer.toJSONObject().toJSONString());
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
        }
    }
}
