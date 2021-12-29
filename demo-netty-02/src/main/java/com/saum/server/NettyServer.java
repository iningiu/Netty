package com.saum.server;

import com.saum.codec.PacketCodecHandler;
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
                            ch.pipeline().addLast(new IMIdleStateHandler());
                            ch.pipeline().addLast(new ProcotolFrameDecoder());
                            ch.pipeline().addLast(PacketCodecHandler.INSTANCE);
                            ch.pipeline().addLast(LoginRequestHandler.INSTANCE);
                            ch.pipeline().addLast(HeartBeatRequestHandler.INSTANCE);
                            ch.pipeline().addLast(AuthHandler.INSTANCE);
                            ch.pipeline().addLast(IMHandler.INSTANCE);
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
