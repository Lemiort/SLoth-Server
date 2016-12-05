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
import java.util.Dictionary;
import java.util.HashMap;

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

    private int prevTransactionCount;

    private HashMap<Long,SimpleCube> simpleCubeHashMap;

    private Long ownCubeId;

    private void UnpackToContainer(String str, JsonContainer container) throws ParseException
    {
        container.clear();
        //десереиализация
        Object obj = parser.parse(str);
        JSONObject jsonObject = (JSONObject) obj;
        container.Parse(jsonObject);

        System.out.println("Client received: " + jsonObject.toJSONString());
    }


    /*private int UnpackCurrentNumTransaction(JsonContainer container) throws ParseException
    {
        if (container.getObjectTypeStr().contains("currentNum")) {
            JSONObject jsonObject = (JSONObject) parser.parse(container.getJsonObject(0));
            return  ((Long) jsonObject.get("number")).intValue();
        }
        else
            return -1;
    }*/

    public void run()
    {
        parser = new JSONParser();
        simpleCubeHashMap = new HashMap<Long, SimpleCube>();
        try {
            this.udpClient = new UdpClient(9877);
        }
        catch (SocketException e){
            System.out.println(e.getMessage());
            return;
        }

        this.cube = new SimpleCube();
        ownCubeId = this.cube.getInstanceID();
        simpleCubeHashMap.put(cube.getInstanceID(), cube);
        try {
            this.serverEP = new IpEndPoint(InetAddress.getByName(serverIP), serverPort);
        }
        catch (UnknownHostException e){
            System.out.println(e.getMessage());
            return;
        }

        this.jsonContainer = new JsonContainer();

        prevTransactionCount = 0;

        while (true)
        //for(int i=0; i<10;i++)
        {
            this.Update();
            try {
                Thread.sleep(60);
            }
            catch (InterruptedException ex)
            {

            }
        }
    }

    private void Update()
    {

        this.cube.getTransformation().position.x = f;
        f+= 0.01;
        int transactionsCount;
        try {
            String sentence;
            //current num
            {
                //sent
                jsonContainer.FormGetCurrentNumContainer();
                this.udpClient.Send(this.jsonContainer.toJSONObject().toJSONString().getBytes(), this.serverEP);
                System.out.println("Client sent: " + jsonContainer.toJSONObject().toJSONString());

                //receive
                this.receiveData = this.udpClient.Receive();
                sentence = new String(receiveData, 0, receiveData.length);
                //unpack
                UnpackToContainer(sentence, jsonContainer);
                //decode
                transactionsCount = (Integer) jsonContainer.getObject(0);
                System.out.println("num =" + transactionsCount);
            }
            //update transaction
            for(int i = prevTransactionCount; i<transactionsCount; i++)
            {
                jsonContainer.FormGetTransaction(i);

                this.udpClient.Send(this.jsonContainer.toJSONObject().toJSONString().getBytes(), this.serverEP);
                System.out.println("Client sent: " + jsonContainer.toJSONObject().toJSONString());

                //receive
                this.receiveData = this.udpClient.Receive();
                sentence = new String(receiveData, 0, receiveData.length);
                //unpack
                UnpackToContainer(sentence, jsonContainer);
                if(jsonContainer.getContainerType() == ContainerType.setPosition){
                    SimpleCube temp = (SimpleCube) jsonContainer.getObject(0);
                    simpleCubeHashMap.put(temp.getInstanceID(),temp);

                    if(simpleCubeHashMap.get(temp.getInstanceID()).getInstanceID() == cube.getInstanceID())
                    {
                        cube = temp;
                    }
                }
            }
            prevTransactionCount = transactionsCount;

            //set  position
            {
                cube = simpleCubeHashMap.get(ownCubeId);
                this.cube.getTransformation().position.x = f;
                jsonContainer.FormSetPositionContainer(cube);
                this.udpClient.Send(this.jsonContainer.toJSONObject().toJSONString().getBytes(), this.serverEP);
                System.out.println("Client sent: " + jsonContainer.toJSONObject().toJSONString());

               /* //receive
                this.receiveData = this.udpClient.Receive();
                sentence = new String(receiveData, 0, receiveData.length);
                //unpack
                UnpackToContainer(sentence, jsonContainer);
                if (jsonContainer.getContainerType() == ContainerType.setPosition) {
                    cube = (SimpleCube) jsonContainer.getObject(0);
                } else {
                    System.out.println("Type is : " + jsonContainer.getContainerType().toString());
                }*/
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
