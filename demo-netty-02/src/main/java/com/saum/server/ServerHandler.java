package com.saum.server;

import com.saum.protocol.request.LoginRequestPacket;
import com.saum.protocol.Packet;
import com.saum.codec.PacketCodeC;
import com.saum.protocol.request.MessageRequestPacket;
import com.saum.protocol.response.LoginResponsePacket;
import com.saum.protocol.response.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.Date;

/**
 * @Author saum
 * @Description:
 */
@Slf4j
@Deprecated
public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        // 解码
        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);
        InetSocketAddress socketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        int port = socketAddress.getPort();

        // 判断是否是登陆请求数据包
        if(packet instanceof LoginRequestPacket){
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;

            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            loginResponsePacket.setVersion(packet.getVersion());
            // 登陆校验
            if(valid(loginRequestPacket)){
                loginResponsePacket.setSuccess(true);

                log.info("客户端[" + port + "] 连接成功");
            }else{
                loginResponsePacket.setSuccess(false);
                loginResponsePacket.setReason("用户名或密码错误");
            }
            ByteBuf responseBuf = PacketCodeC.INSTANCE.encode(ctx.alloc().ioBuffer(), loginResponsePacket);
            ctx.writeAndFlush(responseBuf);
        }else if(packet instanceof MessageRequestPacket){
            MessageRequestPacket messageRequestPacket = (MessageRequestPacket) packet;
            System.out.println(new Date() + " 收到客户端[" + port + "]消息：" + messageRequestPacket.getMessage());

            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
            messageResponsePacket.setMessage("服务端回复【" + messageRequestPacket.getMessage() + "】");
            ByteBuf responseBuf = PacketCodeC.INSTANCE.encode(ctx.alloc().ioBuffer(), messageResponsePacket);
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
