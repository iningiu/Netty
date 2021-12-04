package com.saum.protocol.request;

import com.saum.protocol.Command;
import com.saum.protocol.Packet;

/**
 * @Author saum
 * @Description:
 */
public class LogoutRequestPacket extends Packet {
    @Override
    public Byte getCommandType() {
        return Command.LOGOUT_REQUEST;
    }
}
