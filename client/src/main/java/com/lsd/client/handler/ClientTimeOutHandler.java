package com.lsd.client.handler;

import com.lsd.client.connect.ClientConnect;
import com.lsd.common.constant.MessageConstant;
import com.lsd.common.domain.MessageBeat;
import com.lsd.common.domain.MessageBody;
import com.lsd.common.factory.JsonSerializerFactory;
import com.lsd.common.serializer.impl.JsonSerializer;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

import java.sql.Connection;
import java.util.Date;

/**
 * @program:botremote
 * @Author:liushengdong
 * @Description:
 * @Date:Created in 2019-08-17 14:02
 * @Modified By:
 */
public class ClientTimeOutHandler extends ChannelInboundHandlerAdapter {
    JsonSerializer serializer = (JsonSerializer) JsonSerializerFactory.instance();

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){
            MessageBody messageBody = new MessageBody();
            messageBody.setMessageType(MessageConstant.BEAT);
            MessageBeat messageBeat = new MessageBeat();
            messageBeat.setDate(new Date());
            messageBody.setMessage(messageBeat);
            System.out.println("发现读写超时，需要确认心跳");
            ctx.writeAndFlush(serializer.objToString(messageBody)).addListener(future -> {
                if(future.isSuccess()){
                    System.out.println("心跳发送成功，不需要重连");
                }else{
                    new ClientConnect().connect("localhost",8899,true);
                }
            });
        }else
        {
            super.userEventTriggered(ctx,evt);
        }
    }


}
