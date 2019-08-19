package com.lsd.client.server;

import com.lsd.client.constant.PassLicense;
import com.lsd.client.domain.Tags;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program:botremote
 * @Author:liushengdong
 * @Description:
 * @Date:Created in 2019-08-19 16:54
 * @Modified By:
 */
public abstract class ScriptBase implements Runnable{
    private Operator operator;
    private Tags tags;
    public ScriptBase(Operator operator,Tags tags){
        this.operator = operator;
        this.tags = tags;
    }

    Logger logger = LoggerFactory.getLogger(ScriptBase.class);

    @Override
    public void run() {
        while (true){
            if(PassLicense.getInstance().getIsPass()){
                doRoutine();
            }else {
                logger.info("验证失败，请检查网络或者重启服务");
            }

        }
    }
    public abstract void doRoutine();



}
