package ru.etu.sapr.net;

import java.io.IOException;

/**
 * Created by Nikita on 19.11.2016.
 */
public interface INetClient {
    IpEndPoint getClientEndPoint() throws  NullPointerException;

    //принять от текущего клиента или от ещё неизвестного
    //после принятия он запоминается
    byte[] Receive() throws IOException;

    //отправить на своего клиента
    void Send(byte[] data) throws IOException;

    void Send(byte[] data, IpEndPoint ep) throws IOException;
}
