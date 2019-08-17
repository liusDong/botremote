package com.lsd.client.init;

import com.lsd.client.handler.ClientBeatHandler;
import com.lsd.client.handler.ClientResponseHandler;
import com.lsd.common.domain.MessageBeat;
import com.lsd.common.domain.MessageRequest;
import com.lsd.common.domain.MessageResponse;
import com.lsd.common.rpc.JsonDecoder;
import com.lsd.common.rpc.JsonEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;

public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new StringDecoder(Charset.forName("utf-8")));
        pipeline.addLast(new StringEncoder(Charset.forName("utf-8")));
        pipeline.addLast(new ClientResponseHandler());

    }
}
