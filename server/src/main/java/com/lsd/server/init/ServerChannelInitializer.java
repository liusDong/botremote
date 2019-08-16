package com.lsd.server.init;

import com.lsd.common.domain.MessageRequest;
import com.lsd.common.domain.MessageResponse;
import com.lsd.server.handler.PassHandler;
import com.lsd.server.rpc.JsonDecoder;
import com.lsd.server.rpc.JsonEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

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
        pipeline.addLast(new LengthFieldBasedFrameDecoder(8096,4,4));
        pipeline.addLast(new JsonDecoder<MessageRequest>());
        pipeline.addLast(new JsonEncoder<MessageResponse>());
        pipeline.addLast(new PassHandler());
    }
}
