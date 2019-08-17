package com.lsd.client.handler;

import com.lsd.common.domain.MessageBeat;
import com.lsd.common.domain.MessageResponse;
import com.lsd.common.factory.JsonSerializerFactory;
import com.lsd.common.factory.SerializerFactory;
import com.lsd.common.serializer.Serializer;
import com.lsd.common.serializer.impl.JsonSerializer;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @program:botremote
 * @Author:liushengdong
 * @Description:
 * @Date:Created in 2019-08-17 09:40
 * @Modified By:
 */
public class ClientBeatHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        JsonSerializer instance = (JsonSerializer) JsonSerializerFactory.instance();
        MessageResponse messageResponse = instance.stringToObj(msg, MessageResponse.class);
        System.out.println(messageResponse.isPass());
    }
}
