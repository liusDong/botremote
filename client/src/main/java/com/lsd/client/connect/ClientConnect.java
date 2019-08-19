package com.lsd.client.connect;

import com.lsd.client.init.ClientChannelInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.concurrent.TimeUnit;


/**
 * @program:botremote
 * @Author:liushengdong
 * @Description:
 * @Date:Created in 2019-08-17 14:11
 * @Modified By:
 */
public class ClientConnect implements Runnable{

    private final EventLoopGroup eventLoopGroup = new NioEventLoopGroup(1);

    private Bootstrap bootstrap;

    private Channel channel;

    private String address;

    private int port;

    Logger logger = LoggerFactory.getLogger(ClientConnect.class);

    public ClientConnect(){

    }
    public ClientConnect(String address,int port){
        this.address = address;
        this.port = port;
    }

    public void connect(String address,int port) throws InterruptedException {
         this.connect(address,port,false);
    }

    public void connect(String address, int port,boolean isReConnect) throws InterruptedException {
        try{
            if(!isReConnect){
                logger.info("connect");
                bootstrap = new Bootstrap();
                bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new ClientChannelInitializer());
                this.channel = bootstrap.connect(address, port).sync().channel();
            }else {
                logger.info("re connect ing ......");

                if(null != ClientChannel.channel()){
                    ClientChannel.channel().close().sync();
                }
                bootstrap = new Bootstrap();
                bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new ClientChannelInitializer());
                this.channel = bootstrap.connect(address, port).sync().channel();
            }
        }catch (Exception e){
            logger.info("网络异常，请检查网络是否正常");
            TimeUnit.SECONDS.sleep(20);
        }

    }

    @Override
    public void run() {
        Objects.requireNonNull(address);
        Objects.requireNonNull(port);
        try {
            this.connect(address,port);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getPort() {
        return port;
    }

    public ClientConnect setPort(int port) {
        this.port = port;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public ClientConnect setAddress(String address) {
        this.address = address;
        return this;
    }
}
