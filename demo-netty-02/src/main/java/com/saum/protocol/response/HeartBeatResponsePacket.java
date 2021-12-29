package com.saum.protocol.response;

import com.saum.protocol.Packet;

import static com.saum.protocol.Command.HEARTBEAT_RESPONSE;

/**
 * @Author saum
 * @Description:
 */
public class HeartBeatResponsePacket extends Packet {
    @Override
    public Byte getCommandType() {
        return HEARTBEAT_RESPONSE;
    }
}
