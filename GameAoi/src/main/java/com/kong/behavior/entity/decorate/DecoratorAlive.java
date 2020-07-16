package com.kong.behavior.entity.decorate;

import com.kong.behavior.BehaviorTreeConfig;
import com.kong.behavior.impl.BaseDecorator;

/**
 * 是否存活

 **/
public class DecoratorAlive extends BaseDecorator {

    public DecoratorAlive(BehaviorTreeConfig config) {
        super(config);
    }

    @Override
    public boolean isPass() {
        return false;
    }
}
