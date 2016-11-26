package ru.etu.sapr;

import ru.etu.sapr.game.GameServer;
import ru.etu.sapr.net.NetServer;

public class SLothServer {

    public static void main(String[] args) throws Exception {

        GameServer game = new GameServer();
        NetServer netServer = new NetServer(game);

        Thread gameThread = new Thread(game);
        gameThread.start();

        Thread netServerThread = new Thread(netServer);
        netServerThread.start();


    }
}
