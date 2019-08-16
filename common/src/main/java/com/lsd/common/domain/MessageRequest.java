package com.lsd.common.domain;

import java.io.Serializable;

/**
 * @program:botremote
 * @Author:liushengdong
 * @Description: 客户端传输的消息体
 * @Date:Created in 2019-08-16 15:07
 * @Modified By:
 */
public class MessageRequest implements Serializable {
    private static final long serialVersionUID = -679031726652245379L;
    private String macAddress;

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }
}
