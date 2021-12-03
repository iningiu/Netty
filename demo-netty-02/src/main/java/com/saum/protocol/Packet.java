package com.saum.protocol;

import com.saum.protocol.request.LoginRequestPacket;
import com.saum.protocol.request.MessageRequestPacket;
import com.saum.protocol.response.LoginResponsePacket;
import com.saum.protocol.response.MessageResponsePacket;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author saum
 * @Description:
 */
@Data
public abstract class Packet implements Serializable {
    /**
    * 协议版本号，默认为1
    */
    private Byte version = 1;

    private int sequenceId;
    private int commandType;

    /**
     * 获取指令，指令字段1个字节
     */
    public abstract Byte getCommandType();


    private static final Map<Byte, Class<? extends Packet>> packetMap = new HashMap<>();
    static {
        packetMap.put(Command.LOGIN_REQUEST, LoginRequestPacket.class);
        packetMap.put(Command.LOGIN_RESPONSE, LoginResponsePacket.class);
        packetMap.put(Command.MESSAGE_REQUEST, MessageRequestPacket.class);
        packetMap.put(Command.MESSAGE_RESPONSE, MessageResponsePacket.class);
    }

    public static Class<? extends Packet> getPacketClass(byte commandType){
        return packetMap.get(commandType);
    }

}
