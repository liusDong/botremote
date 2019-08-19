package com.lsd.client.constant;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 持有通行证的实体类，具有原子更新
 *
 *
 */
public class PassLicense {
    private static PassLicense ourInstance = new PassLicense();

    public static PassLicense getInstance() {
        return ourInstance;
    }

    private PassLicense() {
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        atomicBoolean.set(false);
        this.isPass = atomicBoolean;
    }

    private volatile AtomicBoolean isPass;

    public boolean getIsPass() {
        return isPass.get();
    }

    public boolean setPass(boolean b){
        this.isPass.set(b);
        return this.isPass.get();
    }

}
