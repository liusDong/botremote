package com.lsd.server.handler;

import com.lsd.common.constant.MessageConstant;
import com.lsd.common.domain.MessageBeat;
import com.lsd.common.domain.MessageBody;
import com.lsd.common.factory.JsonSerializerFactory;
import com.lsd.common.serializer.impl.JsonSerializer;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

import java.util.Date;

/**
 * @program:botremote
 * @Author:liushengdong
 * @Description:
 * @Date:Created in 2019-08-17 12:23
 * @Modified By:
 */
public class ServerTimeOutHandler extends ChannelInboundHandlerAdapter {

    private final JsonSerializer serializer= (JsonSerializer) JsonSerializerFactory.instance();


    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){
            MessageBody messageBody = new MessageBody();
            messageBody.setMessageType(MessageConstant.BEAT);
            MessageBeat messageBeat = new MessageBeat();
            messageBeat.setDate(new Date());
            messageBody.setMessage(messageBeat);
            ctx.writeAndFlush(serializer.objToString(messageBody)).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);

        }else
        {
            super.userEventTriggered(ctx,evt);
        }
    }
}