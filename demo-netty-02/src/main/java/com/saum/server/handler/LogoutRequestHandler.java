package com.saum.server.handler;

import com.saum.protocol.request.LogoutRequestPacket;
import com.saum.protocol.response.LogoutResponsePacket;
import com.saum.session.Session;
import com.saum.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author saum
 * @Description:
 */
@Slf4j
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutRequestPacket msg) throws Exception {
        Session session = SessionUtil.getSession(ctx.channel());
        log.info("客户端[" + session.getUserName() + "]登出...");
        SessionUtil.unbindSession(ctx.channel());
        LogoutResponsePacket logoutResponsePacket = new LogoutResponsePacket();
        logoutResponsePacket.setSuccess(true);
        ctx.channel().writeAndFlush(logoutResponsePacket);
    }
}
