package com.lsd.client.handler;

import com.lsd.client.connect.ClientConnect;
import com.lsd.client.constant.ConstantInfo;
import com.lsd.common.constant.MessageConstant;
import com.lsd.common.domain.MessageBody;
import com.lsd.common.domain.MessageEnum;
import com.lsd.common.domain.MessageResponse;
import com.lsd.common.factory.JsonSerializerFactory;
import com.lsd.common.serializer.Serializer;
import com.lsd.common.serializer.impl.JsonSerializer;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.NetworkInterface;

public class ClientResponseHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        JsonSerializer instance = (JsonSerializer) JsonSerializerFactory.instance();
        MessageBody messageBody = instance.stringToObj(msg,MessageBody.class);
        if (MessageConstant.RESPONSE == messageBody.getMessageType())
        {
            System.out.println(msg);
        }else if(MessageConstant.BEAT == messageBody.getMessageType()){
            System.out.println(msg);
        }

    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        ClientConnect.channel(ctx.channel());
    }
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        ctx.channel().close();
    }
}
