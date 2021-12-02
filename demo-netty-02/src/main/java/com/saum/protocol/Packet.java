package com.saum.protocol;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author saum
 * @Description:
 */
@Data
public abstract class Packet {
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
    }

    public static Class<? extends Packet> getPacketClass(byte commandType){
        return packetMap.get(commandType);
    }

}
