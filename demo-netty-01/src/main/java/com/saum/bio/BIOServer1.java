package com.saum.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author saum
 * @Description:
 */
public class BIOServer1 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);
        new Thread(()->{
            while(true){
                try {
                    Socket socket = serverSocket.accept();

                    new Thread(()->{
                        try {
                            int len;
                            byte[] data = new byte[1024];
                            InputStream inputStream = socket.getInputStream();
                            while((len = inputStream.read(data)) != -1){
                                System.out.println("收到客户端[" + socket.getPort() + "]发来的数据：" + new String(data, 0, len));
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
