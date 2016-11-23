package ru.etu.sapr;

import ru.etu.sapr.INetClient;
import ru.etu.sapr.IpEndPoint;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created by Red on 20.11.2016.
 */
public class UdpClient implements INetClient {
    IpEndPoint clientEndPoint;

    //приёмный сокет
    DatagramSocket receiveSocket;
    int port;

    byte[] receiveData;

    public IpEndPoint getClientEndPoint() throws  NullPointerException {
        return clientEndPoint;
    }

    public UdpClient(int port) throws SocketException {
        this.port = port;
        // Initialization
        receiveSocket = new DatagramSocket(port);
       receiveData = new byte[1024];
    }

    //принять от текущего клиента или от ещё неизвестного
    //после принятия он запоминается
    public byte[] Receive() throws IOException {
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        receiveSocket.receive(receivePacket);
        clientEndPoint = new IpEndPoint(receivePacket.getAddress(), receivePacket.getPort());
        receiveData = receivePacket.getData();
        byte[] returnData = new byte[receivePacket.getLength()];
        System.arraycopy(receiveData,0, returnData,0, receivePacket.getLength());
        return  returnData;
    }

    //отправить на своего клиента
    public void Send(byte[] data) throws IOException {
        DatagramPacket sendPacket = new DatagramPacket(data, data.length, clientEndPoint.getIp(), clientEndPoint.getPort());
        receiveSocket.send(sendPacket);
    }

    //отправить на любого клиента
    public void Send(byte[] data, IpEndPoint ep) throws IOException {
        DatagramPacket sendPacket = new DatagramPacket(data, data.length, ep.getIp(), ep.getPort());
        receiveSocket.send(sendPacket);
    }
}
