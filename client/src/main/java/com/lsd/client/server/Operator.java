package com.lsd.client.server;

import java.awt.*;

public interface Operator {

    void keyPress(int code);

    void keyRelease(int code);

    void delay(int ms);
}
