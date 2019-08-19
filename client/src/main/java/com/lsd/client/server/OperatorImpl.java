package com.lsd.client.server;

import com.lsd.client.constant.RobotSingleton;

import java.awt.*;
import java.util.concurrent.TimeUnit;

/**
 * @program:botremote
 * @Author:liushengdong
 * @Description:
 * @Date:Created in 2019-08-19 16:01
 * @Modified By:
 */


public class OperatorImpl implements Operator {

    private static final RobotSingleton robotSingleton = RobotSingleton.getInstance();

    @Override
    public void keyPress(int code) {
        robotSingleton.getRobot().keyPress(code);
    }

    @Override
    public void keyRelease(int code) {
        robotSingleton.getRobot().keyRelease(code);
    }

    @Override
    public void delay(int ms){
        try {
            TimeUnit.MILLISECONDS.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
