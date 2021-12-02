package com.saum.client;

import com.saum.protocol.request.LoginRequestPacket;
import com.saum.protocol.Packet;
import com.saum.protocol.PacketCodeC;
import com.saum.protocol.response.LoginResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;
import java.util.UUID;

/**
 * @Author saum
 * @Description:
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + "：客户端开始登陆");

        // 创建登陆对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("zhangsan");
        loginRequestPacket.setPassword("123");

        ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(loginRequestPacket);
        ctx.channel().writeAndFlush(byteBuf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);

        if(packet instanceof LoginResponsePacket){
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;
            if(loginResponsePacket.isSuccess()){
                System.out.println(new Date() + "：客户端登陆成功");
            }else{
                System.out.println(new Date() + "：客户端登陆失败，原因是：" + loginResponsePacket.getReason());
            }
        }
    }
}
