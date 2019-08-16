package com.lsd.server.run;

import com.lsd.server.connect.ConnectedChannelGroup;
import com.lsd.server.init.ServerChannelInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.util.concurrent.TimeUnit;

/**
 * @program:botremote
 * @Author:liushengdong
 * @Description:
 * @Date:Created in 2019-08-16 17:38
 * @Modified By:
 */
public class ServerRunner implements Runnable {

    private static final ChannelGroup channelGroup = ConnectedChannelGroup.instance();

    private int port = 8899;

    ServerRunner(){}

    ServerRunner(int port){
        this.port = port;
    }

    @Override
    public void run() {
        NioEventLoopGroup bossLoopGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerLoopGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossLoopGroup,workerLoopGroup).channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ServerChannelInitializer());

            ChannelFuture channelFuture = bootstrap.bind(port).sync().addListener(future -> {
                while (true){
                    if(channelGroup.size() > 0 ){
                        channelGroup.writeAndFlush("");
                        System.out.println("write and flush ");
                    }
                    System.out.println("wait");
                    TimeUnit.SECONDS.sleep(5);
                }
            });

            channelFuture.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

        }

    }

    public static void main(String[] args) {
        new Thread(new ServerRunner()).start();
    }

}
