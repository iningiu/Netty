package com.saum.protocol.request;

import com.saum.protocol.Command;
import com.saum.protocol.Packet;
import lombok.Data;

/**
 * @Author saum
 * @Description:
 */
@Data
public class ListGroupMembersRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommandType() {
        return Command.LIST_GROUP_MEMBERS_REQUEST;
    }
}
