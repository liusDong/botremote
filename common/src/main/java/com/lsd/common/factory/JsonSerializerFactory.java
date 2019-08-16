package com.lsd.common.factory;

import com.lsd.common.serializer.Serializer;
import com.lsd.common.serializer.impl.JsonSerializer;

/**
 * @program:botremote
 * @Author:liushengdong
 * @Description:
 * @Date:Created in 2019-08-16 16:30
 * @Modified By:
 */
public class JsonSerializerFactory implements SerializerFactory {

    private JsonSerializerFactory(){}

    public static Serializer instance() {
        return FactoryHolder.serializer;
    }
    private static class FactoryHolder{
        public static final Serializer serializer = new JsonSerializer();
    }

}
