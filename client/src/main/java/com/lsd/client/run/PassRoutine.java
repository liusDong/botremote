package com.lsd.client.run;

import com.lsd.client.constant.ConstantInfo;
import com.lsd.client.constant.PassLicense;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @program:botremote
 * @Author:liushengdong
 * @Description:每一段时间让通行证制否一次
 * @Date:Created in 2019-08-19 15:14
 * @Modified By:
 */
public class PassRoutine implements Runnable{

    private static final PassLicense license= PassLicense.getInstance();

    @Override
    public void run() {
        try {
            license.setPass(false);
            TimeUnit.MINUTES.sleep(ConstantInfo.RESET_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
