package com.saum.server.handler;

import com.saum.protocol.request.LoginRequestPacket;
import com.saum.protocol.response.LoginResponsePacket;
import com.saum.session.Session;
import com.saum.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * @Author saum
 * @Description:
 */
@Slf4j
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(msg.getVersion());
         loginResponsePacket.setUserName(msg.getUsername());
        // 登陆校验
        if(valid(msg)){
            loginResponsePacket.setSuccess(true);
            String userId = randomUserId();
            loginResponsePacket.setUserId(userId);
            log.info("客户端[" + msg.getUsername() + "] 登录成功");
            SessionUtil.bindSession(new Session(userId, msg.getUsername()), ctx.channel());
        }else{
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setReason("用户名或密码错误");
            log.info("客户端[" + msg.getUsername() + "] 登录失败");
        }
        ctx.writeAndFlush(loginResponsePacket);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SessionUtil.unbindSession(ctx.channel());
    }

    private boolean valid(LoginRequestPacket loginRequestPacket){
        if("123".equals(loginRequestPacket.getPassword())){
            return true;
        }
        return false;
    }

    private static String randomUserId(){
        return UUID.randomUUID().toString().split("-")[0];
    }
}
