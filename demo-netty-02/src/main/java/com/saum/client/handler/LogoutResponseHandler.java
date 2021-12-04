package com.saum.client.handler;

import com.saum.protocol.response.LoginResponsePacket;
import com.saum.protocol.response.LogoutResponsePacket;
import com.saum.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author saum
 * @Description:
 */
public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutResponsePacket msg) throws Exception {
        System.out.println("您已退出登录...");
        SessionUtil.unbindSession(ctx.channel());
    }
}
