package com.saum.protocol;

import lombok.Data;

/**
 * @Author saum
 * @Description: 客户端登陆请求
 */
@Data
public class LoginRequestPacket extends Packet {
    private Integer userId;
    private String username;
    private String password;

    @Override
    public Byte getCommandType() {
        return Command.LOGIN_REQUEST;
    }
}
