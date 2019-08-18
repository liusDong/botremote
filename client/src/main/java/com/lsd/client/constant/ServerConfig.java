package com.lsd.client.constant;

import com.google.common.io.Resources;
import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.net.URL;
import java.nio.charset.Charset;

public class ServerConfig {


    public static final String IP = getIp();
    public static final int PORT = getPort();



    private static String getIp(){
        try {
            String ip = new Configurations().xml("server-config.xml").getString("ip");
            return ip;
        }catch (Exception e){
            e.printStackTrace();
            return "localhost";
        }
    }

    private static int getPort(){
        try {
            int port = new Configurations().xml("server-config.xml").getInt("port");
            return port;
        }catch (Exception e){
            e.printStackTrace();
            return 8899;
        }
    }
}
