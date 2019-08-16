package com.lsd.server.connect;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class ConnectedChannelGroup {
    private ConnectedChannelGroup(){}
    public static ChannelGroup instance(){
        return ChannelGroupHolder.channelGroup;
    }
    private final static class ChannelGroupHolder{
        private final static ChannelGroup channelGroup= new DefaultChannelGroup( GlobalEventExecutor.INSTANCE);
    }
}
