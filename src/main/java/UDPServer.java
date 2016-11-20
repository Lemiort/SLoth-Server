import java.net.*;
import ru.etu.sapr.*;

public class UDPServer {

    public static void main(String[] args) throws Exception {

        Game game = new Game();
        NetServer netServer = new NetServer(game);

        Thread gameThread = new Thread(game);
        gameThread.start();

        Thread netServerThread = new Thread(netServer);
        netServerThread.start();


    }
}
