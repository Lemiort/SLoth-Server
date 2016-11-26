package ru.etu.sapr;

import ru.etu.sapr.game.GameClient;
import ru.etu.sapr.game.GameServer;
import ru.etu.sapr.net.NetServer;
import ru.etu.sapr.net.UdpClient;

/**
 * Created by Nikita on 26.11.2016.
 */
public class SLothClient {

    public static void main(String[] args) throws Exception {

        GameClient game = new GameClient();

        Thread gameThread = new Thread(game);
        gameThread.start();


    }
}
