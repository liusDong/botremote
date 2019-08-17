package com.lsd.common.domain;

import java.io.Serializable;
import java.util.Date;

public class MessageBeat implements Serializable {
    private static final long serialVersionUID = -6041499671124234731L;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
