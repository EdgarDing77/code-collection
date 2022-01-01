package socket.TCPdemo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Description:
 * socket client
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/11/9
 **/
public class Client {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("127.0.0.1", 17777);
        client.setSoTimeout(10000);
        DataInputStream inputStream = new DataInputStream(client.getInputStream());
        DataOutputStream outputStream = new DataOutputStream(client.getOutputStream());
        boolean flag = true;
        while (flag) {
            outputStream.writeUTF("dadsadsadadas");
            System.out.println("response: " + inputStream.readUTF());
        }
    }
}
