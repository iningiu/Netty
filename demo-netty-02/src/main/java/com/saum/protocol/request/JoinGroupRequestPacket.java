package com.saum.protocol.request;

import com.saum.protocol.Command;
import com.saum.protocol.Packet;
import lombok.Data;

/**
 * @Author saum
 * @Description:
 */
@Data
public class JoinGroupRequestPacket extends Packet {

    private String gorupId;

    @Override
    public Byte getCommandType() {
        return Command.JOIN_GROUP_REQUEST;
    }
}
