package com.saum.bio;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

/**
 * @Author saum
 * @Description:
 */
public class BIOClient {
    public static void main(String[] args) {
        new Thread(()->{
            try {
                Socket socket = new Socket("localhost", 8000);
                while(true){
                    try {
                        System.out.println("向服务端发送数据：" + new String(new Date() + ": hello world"));
                        socket.getOutputStream().write((new Date() + ": hello world").getBytes());
                        Thread.sleep(2000);
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
