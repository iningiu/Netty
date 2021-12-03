package com.saum.codec;

import com.saum.protocol.Packet;
import com.saum.serialize.SerializerAlgorithm;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @Author saum
 * @Description:
 */
public class PacketDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int magic_number = in.readInt();
        byte version = in.readByte();

        byte serializeAlgorithmType = in.readByte();
        byte command = in.readByte();
        int length = in.readInt();
        byte[] data = new byte[length];
        in.readBytes(data);
        // 反序列化
        SerializerAlgorithm algorithm = SerializerAlgorithm.values()[serializeAlgorithmType];
        Class<? extends Packet> packetClass = Packet.getPacketClass(command);
        Packet packet = null;
        if(packetClass != null && algorithm != null){
            packet = algorithm.deserialize(packetClass, data);
        }
        out.add(packet);
    }
}
