package com.saum.protocol.response;

import com.saum.protocol.Command;
import com.saum.protocol.Packet;
import com.saum.session.Session;
import lombok.Data;

import java.util.List;

/**
 * @Author saum
 * @Description:
 */
@Data
public class ListGroupMembersResponsePacket extends Packet {
    private String groupId;
    private List<Session> sessionList;

    @Override
    public Byte getCommandType() {
        return Command.LIST_GROUP_MEMBERS_RESPONSE;
    }
}
