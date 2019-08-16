package com.lsd.common.serializer;

public interface Serializer {

    byte[] serializer(Object object);

    <T> T deserializer(byte[] bytes,Class<T> clazz);


}
