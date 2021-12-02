package com.saum.protocol.request;

import com.saum.protocol.Command;
import com.saum.protocol.Packet;
import lombok.Data;

/**
 * @Author saum
 * @Description: 客户端登陆请求
 */
@Data
public class LoginRequestPacket extends Packet {
    private String userId;
    private String username;
    private String password;

    @Override
    public Byte getCommandType() {
        return Command.LOGIN_REQUEST;
    }
}
