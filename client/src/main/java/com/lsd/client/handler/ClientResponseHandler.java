package com.lsd.client.handler;

import com.lsd.client.connect.ClientChannel;
import com.lsd.common.constant.MessageConstant;
import com.lsd.common.domain.MessageBody;
import com.lsd.common.factory.JsonSerializerFactory;
import com.lsd.common.serializer.impl.JsonSerializer;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ClientResponseHandler extends SimpleChannelInboundHandler<String> {
    private final JsonSerializer serializer = (JsonSerializer) JsonSerializerFactory.instance();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        MessageBody messageBody = serializer.stringToObj(msg,MessageBody.class);
        if (MessageConstant.RESPONSE == messageBody.getMessageType())
        {
            System.out.println(msg);
        }else if(MessageConstant.BEAT == messageBody.getMessageType()){
            System.out.println(msg);
        }

    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        ClientChannel.channel(ctx.channel());
    }
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        ctx.channel().close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.channel().close();
        ClientChannel.channel(null);
    }
}
