package com.saum.protocol.request;

import com.saum.protocol.Command;
import com.saum.protocol.Packet;
import lombok.Data;

/**
 * @Author saum
 * @Description:
 */
@Data
public class MessageRequestPacket extends Packet {

    private String message;

    @Override
    public Byte getCommandType() {
        return Command.MESSAGE_REQUEST;
    }
}
