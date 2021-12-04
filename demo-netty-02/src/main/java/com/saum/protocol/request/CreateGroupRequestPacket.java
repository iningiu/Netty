package com.saum.protocol.request;

import com.saum.protocol.Command;
import com.saum.protocol.Packet;
import lombok.Data;

import java.util.List;

/**
 * @Author saum
 * @Description:
 */
@Data
public class CreateGroupRequestPacket extends Packet {

    private List<String> userIdList;

    @Override
    public Byte getCommandType() {
        return Command.CREATE_GROUP_REQUEST;
    }
}
