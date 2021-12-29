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
    public static final Byte CREATE_GROUP_REQUEST  = 7; // 创建群聊请求
    public static final Byte CREATE_GROUP_RESPONSE  = 8; // 创建群聊响应
    public static final Byte LIST_GROUP_MEMBERS_REQUEST = 9; // 列出群聊成员请求
    public static final Byte LIST_GROUP_MEMBERS_RESPONSE = 10; // 列出群聊成员响应
    public static final Byte JOIN_GROUP_REQUEST = 11; // 加入群聊请求
    public static final Byte JOIN_GROUP_RESPONSE = 12; // 加入群聊响应
    public static final Byte QUIT_GROUP_REQUEST = 13; // 退群请求
    public static final Byte QUIT_GROUP_RESPONSE = 14; // 退群响应
    public static final Byte GROUP_MESSAGE_REQUEST = 15; // 群聊发送消息请求
    public static final Byte GROUP_MESSAGE_RESPONSE = 16; // 群聊发送消息响应
    public static final Byte HEARTBEAT_REQUEST  = 17; // 心跳请求
    public static final Byte HEARTBEAT_RESPONSE  = 18; // 心跳响应

}
