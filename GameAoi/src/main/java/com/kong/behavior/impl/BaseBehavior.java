package com.kong.behavior.impl;


import com.kong.behavior.BehaviorConst;
import com.kong.behavior.BehaviorTreeConfig;
import com.kong.behavior.IBehavior;
import com.kong.common.obj.MonsterActor;
import com.kong.common.obj.Performer;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseBehavior implements IBehavior {


    protected BehaviorConst.EStatus status;

    protected Performer performer;

    protected List<IBehavior> decorators = new ArrayList<>();

    protected BehaviorTreeConfig config;


    public BaseBehavior(BehaviorTreeConfig config) {
        setStatus(BehaviorConst.EStatus.Invalid);
        this.config = config;
    }


    public BehaviorConst.EStatus tick() {

        if (status == BehaviorConst.EStatus.Invalid) {
            onInitialize();
        }

        status = update();

        if (status != BehaviorConst.EStatus.Running) {
            onTerminate(status);
        }

        return status;
    }

    //释放对象所占资源
    public void release(){

    }

    public void setStatus(BehaviorConst.EStatus status) {
        this.status = status;
    }

    public BehaviorConst.EStatus getStatus() {
        return status;
    }

    @Override
    public void onInitialize() {
    }

    @Override
    public void onTerminate(BehaviorConst.EStatus Status) {

    }

    @Override
    public void reset() {

        for (IBehavior behavior : decorators){
            behavior.reset();
        }

        setStatus(BehaviorConst.EStatus.Invalid);

    }

    @Override
    public void setPerformer(MonsterActor monsterActor) {
        this.performer = monsterActor;
    }


    @Override
    public void addDecorator(IBehavior decorator){
        this.decorators.add(decorator);
    }

    @Override
    public boolean isNegation(){

        boolean isNegation = false;
        for (IBehavior decorator : decorators){
            isNegation = decorator.isNegation();
            if (isNegation){
                break;
            }
        }

        return isNegation;
    }
    @Override
    public void addChild(IBehavior child) {

    }

}
