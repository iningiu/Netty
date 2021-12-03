package com.saum.attribute;

import com.saum.session.Session;
import io.netty.util.AttributeKey;

/**
 * @Author saum
 * @Description:
 */
public interface Attributes {
    // 接口中的变量只能是 public static final
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
