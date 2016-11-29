package ru.etu.sapr.net;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ru.etu.sapr.game.GameServer;

import java.io.IOException;
import java.net.SocketException;

/**
 * Created by Red on 20.11.2016.
 */
public class NetServer implements Runnable {

    private GameServer game;
    private JsonContainer message = new JsonContainer();


    public  NetServer(GameServer game)
    {
       this.game = game;
    }

    public void run()
    {
        UdpClient udpClient;
        try {
            udpClient = new UdpClient(9876);
        }
        catch (SocketException ex)
        {
            //TODO
            return;
        }

        // Initialization
        byte[] receiveData;
        byte[] sendData;

        float f = 0;
        float df = 0.5F;

        JSONParser parser = new JSONParser();
        //Transform transform = new Transform();
        try {
            // Main loop
            while (true) {
                receiveData = udpClient.Receive();
                String sentence = new String(receiveData, 0, receiveData.length);
                System.out.println("RECEIVED: " + sentence);

                //десереиализация
                Object obj = parser.parse(sentence);
                JSONObject jsonObject = (JSONObject) obj;
                message.Parse(jsonObject);
                if(message.getObjectType().contains("setPosition"))
                {
                    jsonObject = (JSONObject) parser.parse( message.getObject(0));
                    game.getSimpleCube().Parse(jsonObject);
                    game.getSimpleCube().getTransformation().position.y = (float) Math.cos(f);
                    f += df;

                    // сериализация
                    //message = new JsonContainer();
                    message.clear();

                    message.addObject(game.getSimpleCube().toJSONObject(),0);

                    udpClient.Send(message.toJSONObject().toJSONString().getBytes());

                    System.out.println("SENT: " + message.toJSONObject().toJSONString());
                }
                else if(message.getObjectType().contains("currentNum"))
                {
                    this.game.AddToQueue(message);

                    message.clear();
                    jsonObject.clear();
                    jsonObject.put("number",this.game.GetCurrentTransactionNum());
                    message.addObject(jsonObject,0);
                    udpClient.Send(message.toJSONObject().toJSONString().getBytes());

                    System.out.println("SENT: " + message.toJSONObject().toJSONString());
                }
                else
                {
                    System.out.println("Type is : " + message.getObjectType());
                }
               /* game.getSimpleCube().Parse(jsonObject);

                game.getSimpleCube().transformation.position.y = (float) Math.cos(f);
                f += df;

                //сериализация
                udpClient.Send(game.getSimpleCube().toJSONObject().toJSONString().getBytes());

                System.out.println("SENT: " + game.getSimpleCube().toJSONObject().toJSONString());*/

            }
        }
        catch (SocketException ex)
        {
            //TODO
            return;
        }
        catch (IOException ex)
        {
            //TODO
            return;
        }
        catch (ParseException ex)
        {
            //TODO
            return;
        }
    }
}
