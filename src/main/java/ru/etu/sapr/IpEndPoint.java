package ru.etu.sapr;

import java.net.InetAddress;

/**
 * Created by Red on 20.11.2016.
 */
public class IpEndPoint {
    InetAddress ip;
    int port;

    public  IpEndPoint(InetAddress inetAddress, int port) {
        this.ip = inetAddress;
        this.port = port;
    }

    public InetAddress getIp() {
        return  ip;
    }

    public  int getPort() {
        return  port;
    }
}
