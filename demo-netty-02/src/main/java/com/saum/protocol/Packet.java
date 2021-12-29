package com.saum.protocol;

import com.saum.protocol.request.*;
import com.saum.protocol.response.*;
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
        packetMap.put(Command.LOGOUT_REQUEST, LogoutRequestPacket.class);
        packetMap.put(Command.LOGOUT_RESPONSE, LogoutResponsePacket.class);
        packetMap.put(Command.CREATE_GROUP_REQUEST, CreateGroupRequestPacket.class);
        packetMap.put(Command.CREATE_GROUP_RESPONSE, CreateGroupResponsePacket.class);
        packetMap.put(Command.JOIN_GROUP_REQUEST, JoinGroupRequestPacket.class);
        packetMap.put(Command.JOIN_GROUP_RESPONSE, JoinGroupResponsePacket.class);
        packetMap.put(Command.LIST_GROUP_MEMBERS_REQUEST, ListGroupMembersRequestPacket.class);
        packetMap.put(Command.LIST_GROUP_MEMBERS_RESPONSE, ListGroupMembersResponsePacket.class);
        packetMap.put(Command.QUIT_GROUP_REQUEST, QuitGroupRequestPacket.class);
        packetMap.put(Command.QUIT_GROUP_RESPONSE, QuitGroupResponsePacket.class);
        packetMap.put(Command.GROUP_MESSAGE_REQUEST , GroupMessageRequestPacket.class);
        packetMap.put(Command.GROUP_MESSAGE_RESPONSE , GroupMessageResponsePacket.class);
    }

    public static Class<? extends Packet> getPacketClass(byte commandType){
        return packetMap.get(commandType);
    }

}
