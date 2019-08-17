package com.lsd.common.domain;

import java.io.Serializable;

/**
 * @program:botremote
 * @Author:liushengdong
 * @Description:
 * @Date:Created in 2019-08-17 12:46
 * @Modified By:
 */
public class MessageBody implements IMessage, Serializable {
    private static final long serialVersionUID = -2026308274760122238L;
    private String messageType;
    private IMessage iMessage;

    public String  getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public IMessage getMessage() {
        return iMessage;
    }

    public void setMessage(IMessage iMessage) {
        this.iMessage = iMessage;
    }
}
