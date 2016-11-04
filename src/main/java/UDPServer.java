import java.net.*;
import java.sql.Time;
import java.time.LocalTime;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class UDPServer {

    public static void main(String[] args) throws Exception {
        // Initialization
        DatagramSocket serverSocket = new DatagramSocket(9876);
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];

        JSONParser parser = new JSONParser();
        Transform transform = new Transform();


        //десереиализация
        /*Object obj0 = parser.parse("{\"position\":{\"x\":0.0,\"y\":0.14022529125213624,\"z\":0.0}}");
        JSONObject jsonObject0 = (JSONObject) obj0;
        JSONObject jsonPosition0 = (JSONObject) jsonObject0.get("position");
        double x0 = (Double)jsonPosition0.get("x");
        //float x0 = Float.parseFloat((String)jsonPosition0.get("x"));
        double y0 = (Double)jsonPosition0.get("y");
        double z0 = (Double)jsonPosition0.get("z");
        String str = new String()
        Vector3 position0 = new Vector3();
        position0.x = (float)x0;
        position0.y = (float)y0;
        position0.z = (float)z0;
        transform.position = position0;
        System.out.println("x: " + x0);
        System.out.println("y: " + y0);
        System.out.println("z: " + z0);*/
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
