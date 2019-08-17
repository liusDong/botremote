package com.lsd.server.handler;

import com.alibaba.fastjson.JSON;
import com.lsd.common.constant.MessageConstant;
import com.lsd.common.domain.MessageBody;
import com.lsd.common.domain.MessageEnum;
import com.lsd.common.domain.MessageRequest;
import com.lsd.common.domain.MessageResponse;
import com.lsd.common.factory.JsonSerializerFactory;
import com.lsd.common.serializer.Serializer;
import com.lsd.common.serializer.impl.JsonSerializer;
import com.lsd.server.connect.ConnectedChannelGroup;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import static com.lsd.common.domain.MessageEnum.*;

/**
 * @program:botremote
 * @Author:liushengdong
 * @Description:
 * @Date:Created in 2019-08-16 16:54
 * @Modified By:
 */
public class PassHandler extends SimpleChannelInboundHandler<String> {
    private final JsonSerializer serializer = (JsonSerializer) JsonSerializerFactory.instance();
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        System.out.println(msg);
        MessageBody messageBody = serializer.stringToObj(msg,MessageBody.class);

        if(MessageConstant.REQUEST == messageBody.getMessageType()){
            System.out.println(msg);
            MessageRequest messageRequest = (MessageRequest) messageBody.getMessage();
            MessageResponse messageResponse = new MessageResponse();
            messageResponse.setPass(true);
            MessageBody responseBody = new MessageBody();
            responseBody.setMessageType(MessageConstant.RESPONSE);
            responseBody.setMessage(messageResponse);
            ctx.writeAndFlush(serializer.objToString(responseBody));
            System.out.println("call from :"+messageRequest.getMacAddress());
        }else if(MessageConstant.BEAT == messageBody.getMessageType()){

        }

    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("regi");
        ConnectedChannelGroup.instance().add(ctx.channel());
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("unregi");
        ConnectedChannelGroup.instance().remove(ctx.channel());
    }

}
