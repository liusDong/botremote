package com.lsd.client.run;

import com.lsd.client.connect.ClientChannel;
import com.lsd.client.connect.ClientConnect;
import com.lsd.client.constant.ConstantInfo;
import com.lsd.client.constant.ServerConfig;
import com.lsd.client.init.ClientChannelInitializer;
import com.lsd.common.constant.MessageConstant;
import com.lsd.common.domain.MessageBody;
import com.lsd.common.domain.MessageRequest;
import com.lsd.common.factory.JsonSerializerFactory;
import com.lsd.common.serializer.impl.JsonSerializer;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.ConnectException;
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
public class ClientRunner{
    private static Logger logger = LoggerFactory.getLogger(ClientRunner.class);

    public static void main(String[] args) throws InterruptedException, UnknownHostException, SocketException {

        ClientConnect clientConnect = new ClientConnect();
        clientConnect.setAddress(ServerConfig.IP).setPort(ServerConfig.PORT);

        new Thread(clientConnect).start();

        MessageRequest messageRequest = new MessageRequest();
        messageRequest.setMacAddress(ConstantInfo.CPU_ID);

        MessageBody messageBody = new MessageBody();
        messageBody.setMessageType(MessageConstant.REQUEST);
        messageBody.setMessage(messageRequest);

        JsonSerializer instance = (JsonSerializer) JsonSerializerFactory.instance();
        while (true){
            if(ClientChannel.channel() !=null && ClientChannel.channel().isRegistered()){
                Channel channel = ClientChannel.channel();
                channel.writeAndFlush(instance.objToString(messageBody));
                TimeUnit.SECONDS.sleep(2);
            }else {
                logger.info("尝试重新连接服务器");

                try{
                    ClientConnect clientConnect1 = new ClientConnect();
                    clientConnect1.connect(ServerConfig.IP,ServerConfig.PORT,true);
                }
                catch (Exception e){
                    TimeUnit.SECONDS.sleep(30);
                }
            }

        }
    }
}
