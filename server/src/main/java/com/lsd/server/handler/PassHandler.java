package com.lsd.server.handler;

import com.lsd.common.domain.MessageRequest;
import com.lsd.common.domain.MessageResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @program:botremote
 * @Author:liushengdong
 * @Description:
 * @Date:Created in 2019-08-16 16:54
 * @Modified By:
 */
public class PassHandler extends SimpleChannelInboundHandler<MessageRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequest msg) throws Exception {
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setPass(true);

    }
}
