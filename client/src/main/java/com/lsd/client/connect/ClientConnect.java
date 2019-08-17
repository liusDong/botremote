package com.lsd.client.connect;

import io.netty.channel.Channel;

/**
 * @program:botremote
 * @Author:liushengdong
 * @Description:
 * @Date:Created in 2019-08-17 09:44
 * @Modified By:
 */
public class ClientConnect {
    private ChannelCurrent channelCurrent= new ChannelCurrent();

    private ClientConnect(){}

    public static Channel channel(){return ConnectHolder.channel.getChannel();}

    public static Channel channel(Channel channel){ return ConnectHolder.channel.setChannel(channel); }

    private ChannelCurrent getChannelCurrent() { return channelCurrent; }

    private static final class ConnectHolder{
        private static final ChannelCurrent channel = newChannelCurrent();
    }

    private static ChannelCurrent newChannelCurrent(){
        return new ClientConnect().getChannelCurrent();
    }

    private class ChannelCurrent{
        private Channel channel;

        public Channel getChannel() {
            return channel;
        }

        public Channel setChannel(Channel channel) {
            this.channel = channel;
            return this.channel;
        }
    }
}
