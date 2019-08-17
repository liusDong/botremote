package com.lsd.client.run;

import com.lsd.client.connect.ClientConnect;
import com.lsd.client.constant.ConstantInfo;
import com.lsd.client.init.ClientChannelInitializer;
import com.lsd.common.constant.MessageConstant;
import com.lsd.common.domain.MessageBody;
import com.lsd.common.domain.MessageEnum;
import com.lsd.common.domain.MessageRequest;
import com.lsd.common.domain.MessageResponse;
import com.lsd.common.factory.JsonSerializerFactory;
import com.lsd.common.serializer.impl.JsonSerializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import javax.xml.ws.Response;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

/**
 * @program:botremote
 * @Author:liushengdong
 * @Description:
 * @Date:Created in 2019-08-17 09:48
 * @Modified By:
 */
public class ClientRunner implements Runnable{


    public static void main(String[] args) throws InterruptedException, UnknownHostException, SocketException {
        new Thread(new ClientRunner()).start();

        MessageRequest messageRequest = new MessageRequest();
        messageRequest.setMacAddress(ConstantInfo.CPU_ID);

        MessageBody messageBody = new MessageBody();
        messageBody.setMessageType(MessageConstant.REQUEST);
        messageBody.setMessage(messageRequest);


        //messageRequest.setMacAddress(NetworkInterface.getByInetAddress(InetAddress.getLocalHost()).getHardwareAddress().toString());
        JsonSerializer instance = (JsonSerializer) JsonSerializerFactory.instance();
        while (true){
            if(ClientConnect.channel() !=null){
                Channel channel = ClientConnect.channel();
                System.out.println(instance.objToString(messageBody));
                channel.writeAndFlush(instance.objToString(messageBody));
                TimeUnit.SECONDS.sleep(2);
            }

        }



    }

    @Override
    public void run() {
        EventLoopGroup worker = new NioEventLoopGroup(1);

        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(worker).channel(NioSocketChannel.class).handler(new ClientChannelInitializer());
            ChannelFuture channelFuture = bootstrap.connect("localhost", 8899).sync();
            channelFuture.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            worker.shutdownGracefully();
        }
    }
}
