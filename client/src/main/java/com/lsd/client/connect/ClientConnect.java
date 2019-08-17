package com.lsd.client.connect;

import com.lsd.client.init.ClientChannelInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Objects;


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



    public ClientConnect(){

    }
    public ClientConnect(String address,int port){
        this.address = address;
        this.port = port;
    }

    public Channel connect(String address,int port) throws InterruptedException {
        return this.connect(address,port,false);
    }

    public Channel connect(String address, int port,boolean isReConnect) throws InterruptedException {
        try{
            if(!isReConnect){
                bootstrap = new Bootstrap();
                bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new ClientChannelInitializer());
                this.channel = bootstrap.connect(address, port).sync().channel();
                return ClientChannel.channel(channel);
            }else {
                System.out.println("re connect ing ......");
                ClientChannel.channel().close();
                bootstrap = new Bootstrap();
                bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new ClientChannelInitializer());
                this.channel = bootstrap.connect(address, port).sync().channel();
                return ClientChannel.channel(channel);
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
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
