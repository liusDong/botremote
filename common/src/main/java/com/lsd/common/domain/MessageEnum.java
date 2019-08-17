package com.lsd.common.domain;


/**
*  fastJson在序列号和反序列化的时候，不能够很好的序列好Enum，
*  需要进行一些特殊配置，所以这里暂时用字符串代替，后续想到对应方案的时候再进行替换
*
* */
public enum MessageEnum {
    Beat,Request,Response
}
