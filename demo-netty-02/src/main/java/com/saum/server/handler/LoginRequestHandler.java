package com.saum.server.handler;

import com.saum.codec.PacketCodeC;
import com.saum.protocol.request.LoginRequestPacket;
import com.saum.protocol.response.LoginResponsePacket;
import com.saum.util.LoginUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * @Author saum
 * @Description:
 */
@Slf4j
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
        InetSocketAddress socketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        int port = socketAddress.getPort();

        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(msg.getVersion());
        // 登陆校验
        if(valid(msg)){
            loginResponsePacket.setSuccess(true);
            LoginUtil.markAsLogin(ctx.channel());
            log.info("客户端[" + port + "] 登录成功");
        }else{
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setReason("用户名或密码错误");
            log.info("客户端[" + port + "] 登录失败");
        }
        ctx.writeAndFlush(loginResponsePacket);
    }

    private boolean valid(LoginRequestPacket loginRequestPacket){
        if("123".equals(loginRequestPacket.getPassword())){
            return true;
        }
        return false;
    }
}
