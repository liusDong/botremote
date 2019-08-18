package com.lsd.client.handler;

import com.lsd.client.connect.ClientChannel;
import com.lsd.common.constant.MessageConstant;
import com.lsd.common.domain.MessageBody;
import com.lsd.common.factory.JsonSerializerFactory;
import com.lsd.common.serializer.impl.JsonSerializer;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientResponseHandler extends SimpleChannelInboundHandler<String> {
    private final JsonSerializer serializer = (JsonSerializer) JsonSerializerFactory.instance();

    Logger logger = LoggerFactory.getLogger(ClientResponseHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        MessageBody messageBody = serializer.stringToObj(msg,MessageBody.class);
        if (MessageConstant.RESPONSE .equals( messageBody.getMessageType()))
        {
            logger.info(msg);
        }else if(MessageConstant.BEAT .equals( messageBody.getMessageType())){
            logger.info(msg);
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
