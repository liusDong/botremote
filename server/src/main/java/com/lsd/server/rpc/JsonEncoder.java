package com.lsd.server.rpc;

import com.lsd.common.factory.JsonSerializerFactory;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import com.lsd.common.serializer.impl.JsonSerializer;
/**
 * @program:botremote
 * @Author:liushengdong
 * @Description:
 * @Date:Created in 2019-08-16 16:09
 * @Modified By:
 */
public class JsonEncoder<T> extends MessageToByteEncoder<T> {

    private static final JsonSerializer jsonSerializer= new JsonSerializer();

    @Override
    public void encode(ChannelHandlerContext ctx, T msg, ByteBuf out) throws Exception {
        byte[] bytes = JsonSerializerFactory.instance().serializer(msg);
        out.writeInt(bytes.length);
        out.writeBytes(bytes);
    }
}
