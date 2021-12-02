package com.saum.serialize;

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
        public byte[] serialize(Object object) {
            return new byte[0];
        }

        @Override
        public <T> T deserialize(Class<T> clazz, byte[] bytes) {
            return null;
        }
    },
    JSON{
        @Override
        public byte getSerializerAlgorithm() {
            return 1;
        }

        @Override
        public byte[] serialize(Object object) {
            return new byte[0];
        }

        @Override
        public <T> T deserialize(Class<T> clazz, byte[] bytes) {
            return null;
        }
    }
}
