package com.lsd.common.domain;

import java.io.Serializable;

/**
 * @program:botremote
 * @Author:liushengdong
 * @Description:
 * @Date:Created in 2019-08-16 15:22
 * @Modified By:
 */
public class MessageResponse implements Serializable {
    private static final long serialVersionUID = 8878462380468390558L;

    private boolean isPass;


    public boolean isPass() {
        return isPass;
    }

    public void setPass(boolean pass) {
        isPass = pass;
    }
}
