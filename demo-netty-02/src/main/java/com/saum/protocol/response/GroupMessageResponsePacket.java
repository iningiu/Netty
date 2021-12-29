package com.saum.protocol.response;

import com.saum.protocol.Command;
import com.saum.protocol.Packet;
import com.saum.session.Session;
import lombok.Data;

/**
 * @Author saum
 * @Description:
 */
@Data
public class GroupMessageResponsePacket extends Packet {
    private String fromGroupId;
    private Session fromUser;
    private String message;

    @Override
    public Byte getCommandType() {
        return Command.GROUP_MESSAGE_RESPONSE;
    }
}
