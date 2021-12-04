package com.saum.server;

import com.saum.codec.PacketDecoder;
import com.saum.codec.PacketEncoder;
import com.saum.codec.ProcotolFrameDecoder;
import com.saum.server.handler.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author saum
 * @Description:
 */
@Slf4j
public class NettyServer {
    public static void main(String[] args) {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();

        LoggingHandler LOGGING_HANDLER = new LoggingHandler(LogLevel.INFO);
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
//                            ch.pipeline().addLast(LOGGING_HANDLER);
                            ch.pipeline().addLast(new ProcotolFrameDecoder());
                            ch.pipeline().addLast(new PacketDecoder());
                            ch.pipeline().addLast(new PacketEncoder());
                            ch.pipeline().addLast(new LoginRequestHandler());
                            ch.pipeline().addLast(new AuthHandler());
                            ch.pipeline().addLast(new MessageRequestHandler());
                            ch.pipeline().addLast(new CreateGroupRequestHandler());
                            ch.pipeline().addLast(new LogoutRequestHandler());
                        }
                    });

            Channel channel = serverBootstrap.bind(8000).sync().channel();
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            log.error("server error");
        }finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
