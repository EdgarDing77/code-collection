package socket.TCPdemo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Description:
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/11/9
 **/
public class ServerHandler implements Runnable {
    private Socket server = null;

    public ServerHandler(Socket server) {
        this.server = server;
    }

    @Override
    public void run() {
        try {
            System.out.print("开始处理：");
            // 缓存接收socket的输入流
            DataInputStream inputStream = new DataInputStream(server.getInputStream());
            // 获取socket的输出流，用于回应消息
            DataOutputStream outputStream = new DataOutputStream(server.getOutputStream());
            System.out.println("收到消息：" + inputStream.readUTF());
            outputStream.writeUTF("感谢连接！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
