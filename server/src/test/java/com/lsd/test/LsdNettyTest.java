package com.lsd.test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.junit.Before;
import org.junit.Test;

/**
 * @program:botremote
 * @Author:liushengdong
 * @Description:
 * @Date:Created in 2019-08-16 14:48
 * @Modified By:
 */
public class LsdNettyTest {

    private EventLoopGroup bossEventGroup;
    private EventLoopGroup workerEventGroup;
    private ServerBootstrap bootstrap;


    @Before
    public void init(){
        bossEventGroup = new NioEventLoopGroup(1);
        workerEventGroup = new NioEventLoopGroup();
        try{
            bootstrap = new ServerBootstrap();
            bootstrap.group(bossEventGroup,workerEventGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();


                        }
                    });


        }finally {

        }




    }

    @Test
    public void test(){




    }


}
