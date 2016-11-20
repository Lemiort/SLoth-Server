package ru.etu.sapr;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.SocketException;

/**
 * Created by Red on 20.11.2016.
 */
public class NetServer implements Runnable {

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
        Transform transform = new Transform();


        try {
            // Main loop
            while (true) {
                receiveData = udpClient.Receive();
                String sentence = new String(receiveData, 0, receiveData.length);
                System.out.println("RECEIVED: " + sentence);

                //десереиализация
                Object obj = parser.parse(sentence);
                JSONObject jsonObject = (JSONObject) obj;
                JSONObject jsonPosition = (JSONObject) jsonObject.get("position");
                Double x = (Double) jsonPosition.get("x");
                Double y = (Double) jsonPosition.get("y");
                Double z = (Double) jsonPosition.get("z");
                Vector3 position = new Vector3();
                position.x = x.floatValue();
                position.y = y.floatValue();
                position.z = z.floatValue();

                position.y = (float) Math.cos(f);
                f += df;
                transform.position = position;

                //сериализация
                JSONObject positionToSend = new JSONObject();
                positionToSend.put("x", transform.position.x);
                positionToSend.put("y", transform.position.y);
                positionToSend.put("z", transform.position.z);
                JSONObject transformToSend = new JSONObject();
                transformToSend.put("position", positionToSend);

                System.out.println("transform: " + transformToSend.toJSONString());

                udpClient.Send(transformToSend.toJSONString().getBytes());

                System.out.println("SENT: " + transformToSend.toJSONString());

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
