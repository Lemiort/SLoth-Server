import java.net.*;
import ru.etu.sapr.*;

public class UDPServer {

    public static void main(String[] args) throws Exception {

        Thread netServerThread = new Thread(new NetServer());
        netServerThread.start();
    }
}
