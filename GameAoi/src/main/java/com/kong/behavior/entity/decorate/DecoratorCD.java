package com.kong.behavior.entity.decorate;


import com.kong.behavior.BehaviorTreeConfig;
import com.kong.behavior.impl.BaseDecorator;

/**
 * CD 时间

 **/
public class DecoratorCD extends BaseDecorator {

    private long startTime;

    public DecoratorCD(BehaviorTreeConfig config) {
        super(config);
    }

    @Override
    public boolean isPass() {

        long now = System.currentTimeMillis();
        if (startTime == 0){
            startTime = now;
        }

        boolean isTrue = System.currentTimeMillis() - startTime > config.getValue()[0] * 1000;
        if (isTrue){
            startTime = 0;
        }

        return isTrue;
    }
}
