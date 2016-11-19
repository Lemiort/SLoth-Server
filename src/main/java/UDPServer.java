import java.net.*;
import java.sql.Time;
import java.time.LocalTime;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import ru.etu.sapr.*;

public class UDPServer {

    public static void main(String[] args) throws Exception {
        // Initialization
        DatagramSocket serverSocket = new DatagramSocket(9876);
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];

        float f = 0;
        float df = 0.5F;

        JSONParser parser = new JSONParser();
        Transform transform = new Transform();


        /*//десереиализация
        Object obj0 = parser.parse("{\"position\":{\"x\":0.0,\"y\":0.14022529125213624,\"z\":0.0}}");
        JSONObject jsonObject = (JSONObject) obj0;
        JSONObject jsonPosition = (JSONObject) jsonObject.get("position");
        Double x = (Double) jsonPosition.get("x");
        Double y = (Double)jsonPosition.get("y");
        Double z = (Double)jsonPosition.get("z");
        Vector3 position = new Vector3();
        position.x = x.floatValue();
        position.y = y.floatValue();
        position.z = z.floatValue();

        position.y = (float)Math.cos(LocalTime.now().getSecond());
        transform.position = position;


        //сериализация
        JSONObject positionToSend = new JSONObject();
        positionToSend.put("x", transform.position.x);
        positionToSend.put("y", transform.position.y);
        positionToSend.put("z", transform.position.z);
        JSONObject transformToSend = new JSONObject();
        transformToSend.put("position", positionToSend);

        System.out.println("transform: " + transformToSend.toJSONString());
        System.out.println("SENT: " + transformToSend.toJSONString());*/

        // Main loop
        while (true){
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String sentence = new String( receivePacket.getData());
            sentence = new String(receivePacket.getData(),0, receivePacket.getLength());
            System.out.println("RECEIVED: " + sentence);

            //десереиализация
            Object obj = parser.parse(sentence);
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject jsonPosition = (JSONObject) jsonObject.get("position");
            Double x = (Double) jsonPosition.get("x");
            Double y = (Double)jsonPosition.get("y");
            Double z = (Double)jsonPosition.get("z");
            Vector3 position = new Vector3();
            position.x = x.floatValue();
            position.y = y.floatValue();
            position.z = z.floatValue();

            position.y = (float)Math.cos(f);
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

            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            //String capitalizedSentence = sentence.toUpperCase();
            //sendData = capitalizedSentence.getBytes();

            sendData = transformToSend.toJSONString().getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            serverSocket.send(sendPacket);
            System.out.println("SENT: " + transformToSend.toJSONString());

        }
    }
}
