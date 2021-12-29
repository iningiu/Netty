package com.saum.client;

import com.saum.client.console.ConsoleCommandManager;
import com.saum.client.console.LoginConsoleCommand;
import com.saum.client.handler.*;
import com.saum.codec.PacketDecoder;
import com.saum.codec.PacketEncoder;
import com.saum.codec.ProcotolFrameDecoder;
import com.saum.server.handler.IMIdleStateHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * @Author saum
 * @Description:
 */
@Slf4j
public class NettyClient {
    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();
        LoggingHandler LOGGING_HANDLER = new LoggingHandler(LogLevel.INFO);

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
//                            ch.pipeline().addLast(LOGGING_HANDLER);
                            ch.pipeline().addLast(new IMIdleStateHandler());
                            ch.pipeline().addLast(new ProcotolFrameDecoder());
                            ch.pipeline().addLast(new PacketDecoder());
                            ch.pipeline().addLast(new LoginResponseHandler());
                            ch.pipeline().addLast(new LogoutResponseHandler());
                            ch.pipeline().addLast(new MessageResponseHandler());
                            ch.pipeline().addLast(new CreateGroupResponseHandler());
                            ch.pipeline().addLast(new JoinGroupResponseHandler());
                            ch.pipeline().addLast(new ListGroupMembersResponseHandler());
                            ch.pipeline().addLast(new GroupMessageResponseHandler());
                            ch.pipeline().addLast(new QuitGroupResponseHandler());
                            ch.pipeline().addLast(new PacketEncoder());
                            ch.pipeline().addLast(new HeartBeatTimerHandler());
                        }
                    });

            ChannelFuture channelFuture = bootstrap.connect("localhost", 8000).sync();
            startConsoleThread(channelFuture.channel());
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            log.error("client error");
        } finally {
            group.shutdownGracefully();
        }
    }

    private static void startConsoleThread(Channel channel){
        Scanner sc = new Scanner(System.in);
        LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();
        ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();

        loginConsoleCommand.exec(sc, channel);
        new Thread(()->{
            while(!Thread.interrupted()){
                consoleCommandManager.exec(sc, channel);
//                if(!SessionUtil.hasLogin(channel)){
//                    loginConsoleCommand.exec(sc, channel);
//                }else{
//                    consoleCommandManager.exec(sc, channel);
//                }
            }
        }).start();
    }
}
