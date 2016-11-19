package ru.etu.sapr;

/**
 * Created by Nikita on 19.11.2016.
 */
public interface INetClient {
    // TODO: геттер клиента

    //принять от текущего клиента или от ещё неизвестного
    //после принятия он запоминается
    byte[] Receive();

    //отправить на своего клиента
    void Send();
}
