package com.lsd.client.constant;

import java.awt.*;

public class RobotSingleton {
    private static RobotSingleton ourInstance = new RobotSingleton();

    public static RobotSingleton getInstance() {
        return ourInstance;
    }

    private RobotSingleton() {
        try {
            this.robot = new Robot();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private Robot robot;

    public Robot getRobot() {
        return robot;
    }
}
