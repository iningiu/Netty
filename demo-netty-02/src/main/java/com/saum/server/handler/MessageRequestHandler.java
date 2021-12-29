package com.saum.server.handler;

import com.saum.codec.PacketCodeC;
import com.saum.protocol.request.MessageRequestPacket;
import com.saum.protocol.response.MessageResponsePacket;
import com.saum.session.Session;
import com.saum.util.SessionUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.InetSocketAddress;
import java.util.Date;

/**
 * @Author saum
 * @Description:
 */
@ChannelHandler.Sharable
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    public static final MessageRequestHandler INSTANCE = new MessageRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) throws Exception {
        // 拿到消息发送方的会话消息
        Session fromSession = SessionUtil.getSession(ctx.channel());
        Channel toUserChannel = SessionUtil.getChannel(msg.getToUserId());
        Session toSession = SessionUtil.getSession(toUserChannel);
        System.out.println(new Date() + " 收到客户端[" + fromSession.getUserName() + "]转发给客户端[" + toSession.getUserName() + "]的消息：" + msg.getMessage());

        // 通过消息发送方的会话信息构造要发送的消息
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setFromUserId(fromSession.getUserId());
        messageResponsePacket.setFromUserName(fromSession.getUserName());
        messageResponsePacket.setMessage(msg.getMessage());

        if(toUserChannel != null && SessionUtil.hasLogin(toUserChannel)){
            toUserChannel.writeAndFlush(messageResponsePacket);
        }else{
            System.err.println("[" + msg.getToUserId() + "] 不在线，发送失败！" );
        }
    }
}
