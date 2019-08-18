package com.lsd.server.handler;

import com.alibaba.fastjson.JSON;
import com.lsd.common.constant.MessageConstant;
import com.lsd.common.domain.*;
import com.lsd.common.factory.JsonSerializerFactory;
import com.lsd.common.serializer.Serializer;
import com.lsd.common.serializer.impl.JsonSerializer;
import com.lsd.server.connect.ConnectedChannelGroup;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.lsd.common.domain.MessageEnum.*;

/**
 * @program:botremote
 * @Author:liushengdong
 * @Description:
 * @Date:Created in 2019-08-16 16:54
 * @Modified By:
 */
public class PassHandler extends SimpleChannelInboundHandler<String> {

    Logger logger = LoggerFactory.getLogger(PassHandler.class);

    private final JsonSerializer serializer = (JsonSerializer) JsonSerializerFactory.instance();
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        //System.out.println(msg);
        MessageBody messageBody = serializer.stringToObj(msg,MessageBody.class);
        IMessage message = messageBody.getMessage();
        if(MessageConstant.REQUEST .equals( messageBody.getMessageType())){
            MessageRequest messageRequest =  serializer.stringToObj(message.toString(),MessageRequest.class);
            MessageResponse messageResponse = new MessageResponse();
            messageResponse.setPass(true);
            MessageBody responseBody = new MessageBody();
            responseBody.setMessageType(MessageConstant.RESPONSE);
            responseBody.setMessage(messageResponse);
            ctx.writeAndFlush(serializer.objToString(responseBody));

            logger.info("call from :"+messageRequest.getMacAddress());
        }else if(MessageConstant.BEAT == messageBody.getMessageType()){
            logger.info("beat heart from :"+ctx.channel().remoteAddress());
        }

    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
       logger.info(ctx.channel().remoteAddress()+" register to the server");
        ConnectedChannelGroup.instance().add(ctx.channel());
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        logger.info(ctx.channel().remoteAddress()+" unregister to the server");
        ConnectedChannelGroup.instance().remove(ctx.channel());
    }

}
