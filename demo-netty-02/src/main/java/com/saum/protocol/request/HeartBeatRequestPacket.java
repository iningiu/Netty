package com.saum.protocol.request;

import com.saum.protocol.Command;
import com.saum.protocol.Packet;

/**
 * @Author saum
 * @Description:
 */
public class HeartBeatRequestPacket extends Packet {
    @Override
    public Byte getCommandType() {
        return Command.HEARTBEAT_REQUEST;
    }
}
