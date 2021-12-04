package com.saum.client.handler;

import com.saum.protocol.response.LoginResponsePacket;
import com.saum.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author saum
 * @Description:
 */
public class LogoutResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket msg) throws Exception {
        SessionUtil.unbindSession(ctx.channel());
        System.out.println("您已退出登录...");
    }
}
