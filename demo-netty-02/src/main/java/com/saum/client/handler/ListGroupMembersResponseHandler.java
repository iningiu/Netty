package com.saum.client.handler;

import com.saum.protocol.response.ListGroupMembersResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author saum
 * @Description:
 */
public class ListGroupMembersResponseHandler extends SimpleChannelInboundHandler<ListGroupMembersResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersResponsePacket msg) {
        System.out.println("群[" + msg.getGroupId() + "]中的人包括：" + msg.getSessionList());
    }
}
