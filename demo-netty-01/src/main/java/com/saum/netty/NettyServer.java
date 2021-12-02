package com.saum.netty;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @Author saum
 * @Description:
 */
public class NettyServer {
    public static void main(String[] args) {
        // 处理连接请求
        EventLoopGroup boss = new NioEventLoopGroup();
        // 处理每一条连接的数据读写事件
        EventLoopGroup worker = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(boss, worker)
                // 指定服务端的IO模型为NIO
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new StringEncoder());
                        ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
                                System.out.println(msg);
                            }
                        });
                    }
                });
        ChannelFuture channelFuture = serverBootstrap.bind(8000);
        channelFuture.addListener(future -> {
            if(future.isSuccess()){
                System.out.println("端口绑定成功!");
            }else{
                System.out.println("端口绑定成功!");
            }
        });

    }
}
