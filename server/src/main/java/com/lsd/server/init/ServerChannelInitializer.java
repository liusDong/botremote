package com.lsd.server.init;

import com.lsd.common.domain.MessageBeat;
import com.lsd.common.domain.MessageRequest;
import com.lsd.common.domain.MessageResponse;
import com.lsd.server.handler.PassHandler;
import com.lsd.common.rpc.JsonDecoder;
import com.lsd.common.rpc.JsonEncoder;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * @program:botremote
 * @Author:liushengdong
 * @Description:
 * @Date:Created in 2019-08-16 16:05
 * @Modified By:
 */
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //pipeline.addLast(new LengthFieldBasedFrameDecoder(8096,4,4));
        pipeline.addLast(new StringDecoder(Charset.forName("utf-8")));
        pipeline.addLast(new StringEncoder(Charset.forName("utf-8")));
        pipeline.addLast(new IdleStateHandler(60,60,120, TimeUnit.SECONDS));



        pipeline.addLast(new PassHandler());
    }

}
