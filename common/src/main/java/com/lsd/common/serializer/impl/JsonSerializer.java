package com.lsd.common.serializer.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lsd.common.serializer.Serializer;

import java.nio.charset.Charset;

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

    public String objToString(Object object){
        //int features=SerializerFeature.config(JSON.DEFAULT_GENERATE_FEATURE, SerializerFeature.WriteEnumUsingName, false);
        //obj,features,SerializerFeature.EMPTY;
        return JSON.toJSONString(object);}

    public <T> T stringToObj(String str,Class<T> clazz){


        return JSON.parseObject(str).toJavaObject(clazz);};


}
