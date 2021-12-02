package com.saum.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Author saum
 * @Description:
 */
public class NettyClient {
    public static final int MAX_RETRY = 10;
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new StringEncoder());
                    }
                });

//        connect(bootstrap, "localhost", 8000, 0);
        Channel channel = bootstrap.connect("localhost", 8000).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功!");
            } else {
                System.err.println("连接失败!");
            }
        }).channel();

        while(true){
            channel.writeAndFlush(new Date() + ": hello world");
            Thread.sleep(2000);
        }
    }

    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("客户端和服务端连接成功！");
                } else if (retry == MAX_RETRY) {
                    System.out.println("重试次数已用完，放弃连接！");
                } else {
                    int order = retry + 1;
                    System.err.println(new Date() + ": 连接失败，第" + order + "次重连");
                    int delay = 1 << order;
                    bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry + 1), delay, TimeUnit.SECONDS);
                }
            }
        });
    }
}
