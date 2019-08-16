package com.lsd.server.codec;

import io.netty.channel.ChannelHandler;

/**
 * TODO
 * @DESCRIPTION 一个抽象的编码器Codec接口
 * @author: liushengdong
 * @Date: 2019/8/16
*/

public interface Codec {


    ChannelHandler newEncoder();

    ChannelHandler newDecoder();


}
