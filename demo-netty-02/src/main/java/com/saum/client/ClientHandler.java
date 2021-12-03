package com.saum.client;

import com.saum.protocol.request.LoginRequestPacket;
import com.saum.protocol.Packet;
import com.saum.protocol.PacketCodeC;
import com.saum.protocol.response.LoginResponsePacket;
import com.saum.protocol.response.MessageResponsePacket;
import com.saum.util.LoginUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.UUID;

/**
 * @Author saum
 * @Description:
 */
@Slf4j
public class ClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + " 客户端开始登陆");

        // 创建登陆对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("zhangsan");
        loginRequestPacket.setPassword("123");

        ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc().ioBuffer(),loginRequestPacket);
        ctx.channel().writeAndFlush(byteBuf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);

        if(packet instanceof LoginResponsePacket){
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;
            if(loginResponsePacket.isSuccess()){
                System.out.println(new Date() + " 客户端登录成功");
                LoginUtil.markAsLogin(ctx.channel());
            }else{
                System.out.println(new Date() + " 客户端登录失败，原因是：" + loginResponsePacket.getReason());
            }
        }else if(packet instanceof MessageResponsePacket){
            MessageResponsePacket messageResponsePacket = (MessageResponsePacket) packet;
            System.out.println(new Date() + " 收到服务端消息：" + messageResponsePacket.getMessage());
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("连接异常关闭{}", cause.getMessage());
        ctx.close();
    }
}
