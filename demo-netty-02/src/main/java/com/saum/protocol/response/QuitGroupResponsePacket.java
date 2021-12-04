package com.saum.protocol.response;

import com.saum.protocol.Command;
import com.saum.protocol.Packet;
import lombok.Data;

/**
 * @Author saum
 * @Description:
 */
@Data
public class QuitGroupResponsePacket extends Packet {
    private String groupId;
    private boolean success;
    private String reason;


    @Override
    public Byte getCommandType() {
        return Command.QUIT_GROUP_RESPONSE;
    }
}
