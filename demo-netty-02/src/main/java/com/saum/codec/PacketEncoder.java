package com.saum.codec;

import com.saum.protocol.Packet;
import com.saum.serialize.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Author saum
 * @Description:
 */
public class PacketEncoder extends MessageToByteEncoder<Packet> {
    private static final int MAGIC_NUMBER = 0X12345678;

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
        // 序列化
        byte[] bytes = Serializer.DEFAULT.serialize(msg);

        // 编码
        out.writeInt(MAGIC_NUMBER);
        out.writeByte(msg.getVersion());
        out.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        out.writeByte(msg.getCommandType());
        out.writeInt(bytes.length);
        out.writeBytes(bytes);
    }
}
