package com.saum.protocol;

import com.saum.serialize.Serializer;
import com.saum.serialize.SerializerAlgorithm;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * @Author saum
 * @Description: 消息编解码
 */
public class PacketCodeC {
    private static final int MAGIC_NUMBER = 0X12345678;
    public static final PacketCodeC INSTANCE = new PacketCodeC();

    public ByteBuf encode(ByteBuf byteBuf, Packet packet){
        // 序列化
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        // 编码
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommandType());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }

    public Packet decode(ByteBuf byteBuf){
        int magic_number = byteBuf.readInt();
        byte version = byteBuf.readByte();

        byte serializeAlgorithmType = byteBuf.readByte();
        byte command = byteBuf.readByte();
        int length = byteBuf.readInt();
        byte[] data = new byte[length];
        byteBuf.readBytes(data);
        // 反序列化
        SerializerAlgorithm algorithm = SerializerAlgorithm.values()[serializeAlgorithmType];
        Class<? extends Packet> packetClass = Packet.getPacketClass(command);
        if(packetClass != null && algorithm != null){
            Packet packet = algorithm.deserialize(packetClass, data);
            return packet;
        }
        return null;
    }
}
