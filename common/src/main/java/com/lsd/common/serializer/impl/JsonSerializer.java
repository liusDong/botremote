package com.lsd.common.serializer.impl;

import com.alibaba.fastjson.JSON;
import com.lsd.common.serializer.Serializer;

/**
 * @program:botremote
 * @Author:liushengdong
 * @Description:
 * @Date:Created in 2019-08-16 16:03
 * @Modified By:
 */
public class JsonSerializer implements Serializer {
    public byte[] serializer(Object object) {
        return JSON.toJSONBytes(object);
    }

    public <T> T deserializer(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes,clazz);
    }
}
