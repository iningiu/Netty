package com.saum.server;

import com.saum.protocol.request.LoginRequestPacket;
import com.saum.protocol.Packet;
import com.saum.protocol.PacketCodeC;
import com.saum.protocol.response.LoginResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author saum
 * @Description:
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        // 解码
        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);

        // 判断是否是登陆请求数据包
        if(packet instanceof LoginRequestPacket){
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;

            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            loginResponsePacket.setVersion(packet.getVersion());
            // 登陆校验
            if(valid(loginRequestPacket)){
                loginResponsePacket.setSuccess(true);
            }else{
                loginResponsePacket.setSuccess(false);
                loginResponsePacket.setReason("用户名或密码错误");
            }
            ByteBuf responseBuf = PacketCodeC.INSTANCE.encode(loginResponsePacket);
            ctx.writeAndFlush(responseBuf);
        }
    }

    private boolean valid(LoginRequestPacket loginRequestPacket){
        if("123".equals(loginRequestPacket.getPassword())){
            return true;
        }
        return false;
    }
}
