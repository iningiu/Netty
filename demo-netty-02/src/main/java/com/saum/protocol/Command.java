package com.saum.protocol;

/**
 * @Author saum
 * @Description:
 */
public class Command {
    public static final Byte LOGIN_REQUEST = 1; // 登陆请求
    public static final Byte LOGIN_RESPONSE = 2; // 登陆响应
    public static final Byte MESSAGE_REQUEST = 3; // 消息请求
    public static final Byte MESSAGE_RESPONSE = 4; // 消息返回
    public static final Byte LOGOUT_REQUEST  = 5; // 登出请求
    public static final Byte LOGOUT_RESPONSE  = 6; // 登出响应
    public static final Byte CREATE_GROUP_REQUEST  = 7; // 消息返回
    public static final Byte CREATE_GROUP_RESPONSE  = 8; // 消息返回

}
