package socket.TCPdemo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Description:
 * Socket server
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/11/9
 **/
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(17777);
        Socket client = null;
        boolean flag = true;
        while (flag) {
            client = serverSocket.accept();
            System.out.println(
                "client " + client.getLocalAddress().toString() + ":" + client.getPort() +
                    " connect successfully");
            new Thread(new ServerHandler(client)).start();
        }
    }
}
