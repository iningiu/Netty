package com.saum.serialize;

import java.io.*;

/**
 * @Author saum
 * @Description:
 */
public enum SerializerAlgorithm implements Serializer{

    JAVA{
        @Override
        public byte getSerializerAlgorithm() {
            return 0;
        }

        @Override
        public <T> byte[] serialize(T object) {
            try {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(bos);
                oos.writeObject(object);
                return bos.toByteArray();
            } catch (IOException e) {
                throw new RuntimeException("序列化失败", e);
            }
        }

        @Override
        public <T> T deserialize(Class<T> clazz, byte[] bytes) {
            try {
                ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bis);
                return (T)ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException("反序列化失败", e);
            }
        }
    },
    JSON{
        @Override
        public byte getSerializerAlgorithm() {
            return 1;
        }

        @Override
        public <T> byte[] serialize(T object) {
            return new byte[0];
        }

        @Override
        public <T> T deserialize(Class<T> clazz, byte[] bytes) {
            return null;
        }
    }
}
