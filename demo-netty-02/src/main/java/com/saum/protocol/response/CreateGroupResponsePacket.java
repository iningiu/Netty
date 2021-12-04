package com.saum.protocol.response;

import com.saum.protocol.Command;
import com.saum.protocol.Packet;
import lombok.Data;

import java.util.List;

/**
 * @Author saum
 * @Description:
 */
@Data
public class CreateGroupResponsePacket extends Packet {
    private boolean success;
    private String groupId;
    private List<String> userNameList;

    @Override
    public Byte getCommandType() {
        return Command.CREATE_GROUP_RESPONSE;
    }
}
