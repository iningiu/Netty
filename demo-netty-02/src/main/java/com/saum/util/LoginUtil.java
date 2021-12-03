package com.saum.util;


import com.saum.attribute.Attributes;
import io.netty.channel.Channel;
import io.netty.util.Attribute;

/**
 * @Author saum
 * @Description:
 */
public class LoginUtil {
    /**
    * @Description 标记为已登录
    */
    public static void markAsLogin(Channel channel){
        channel.attr(Attributes.LOGIN).set(true);
    }

    /**
    * @Description 服务端每次接收消息前都要校验是否已经登陆
    */
    public static boolean hasLogin(Channel channel){
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);
        return loginAttr.get() != null;
    }
}
