package com.saum.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author saum
 * @Description: 线程池
 */
public class BIOServer2 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        new Thread(()->{
            while(true){
                try {
                    Socket socket = serverSocket.accept();
                    System.out.println("socket[" + socket.getPort() + "]" + "成功建立连接");
                    threadPool.execute(()->{
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
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
