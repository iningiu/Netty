package com.saum.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * @Author saum
 * @Description:
 */
public class NIOServer {
    public static void main(String[] args) throws IOException {
        Selector serverSelector = Selector.open();
        Selector clientSelector = Selector.open();

        new Thread(()->{
            try {
                ServerSocketChannel listenerChannel = ServerSocketChannel.open();
                listenerChannel.socket().bind(new InetSocketAddress(8000));
                listenerChannel.configureBlocking(false);
                listenerChannel.register(serverSelector, SelectionKey.OP_ACCEPT);

                while(true){
                    if(serverSelector.select(100) > 0){
                        Iterator<SelectionKey> iter = serverSelector.selectedKeys().iterator();
                        while(iter.hasNext()){
                            SelectionKey key = iter.next();
                            if(key.isAcceptable()){
                                try {
                                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                                    SocketChannel clientChannel  = serverSocketChannel.accept();
                                    System.out.println("socket[" + clientChannel.socket().getPort() + "]" + "成功建立连接");
                                    clientChannel.configureBlocking(false);
                                    clientChannel.register(clientSelector, SelectionKey.OP_READ);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }finally {
                                    iter.remove();
                                }
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                while(true){
                    if(clientSelector.select(100) > 0){
                        Iterator<SelectionKey> iter = clientSelector.selectedKeys().iterator();
                        while(iter.hasNext()){
                            SelectionKey key = iter.next();
                            if(key.isReadable()){
                                try {
                                    SocketChannel clientChannel  = (SocketChannel) key.channel();
                                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                                    clientChannel.read(buffer);
                                    buffer.flip();
                                    System.out.print("收到客户端[" + clientChannel.socket().getPort() + "]发来的数据：");
                                    System.out.println(Charset.defaultCharset().newDecoder().decode(buffer).toString());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }finally {
                                    iter.remove();
                                    key.interestOps(SelectionKey.OP_READ);
                                }
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
