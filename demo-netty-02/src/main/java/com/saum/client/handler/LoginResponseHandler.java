package com.saum.client.handler;

import com.saum.codec.PacketCodeC;
import com.saum.protocol.request.LoginRequestPacket;
import com.saum.protocol.response.LoginResponsePacket;
import com.saum.util.LoginUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;
import java.util.UUID;

/**
 * @Author saum
 * @Description:
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + " 客户端开始登陆");

        // 创建登陆对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("zhangsan");
        loginRequestPacket.setPassword("123");


        ctx.writeAndFlush(loginRequestPacket);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket msg) throws Exception {
        if(msg.isSuccess()){
            System.out.println(new Date() + " 客户端登录成功");
            LoginUtil.markAsLogin(ctx.channel());
        }else{
            System.out.println(new Date() + " 客户端登录失败，原因是：" + msg.getReason());
        }
    }
}
