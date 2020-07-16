package com.kong.behavior;

import com.kong.behavior.impl.BaseComposite;
import com.kong.common.obj.MonsterActor;
import com.kong.common.obj.Performer;
import com.sh.common.config.ConfigDataManager;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class BehaviorFactory {


    public static void createTree(MonsterActor monsterActor){

        int configId = monsterActor.getBehaviorType();
        if (configId == 0){
            return;
        }
        BehaviorTreeConfig config = ConfigDataManager.getInstance().getById(BehaviorTreeConfig.class,configId);
        if (config == null){
            log.error("BehaviorTreeConfig配置错误,configId:" + configId);
            return;
        }
        IBehavior root = createBehavior(monsterActor,config);
        if (root == null){
            return;
        }

        BehaviorTree tree = new BehaviorTree(root);
        monsterActor.setBehaviorTree(tree);
    }

    private static IBehavior createBehavior(MonsterActor monsterActor, BehaviorTreeConfig config){
        IBehavior behavior;

        BehaviorConst.BehaviorType type = BehaviorConst.BehaviorType.type(config.getType());
        int subType = config.getSubType();
        if (type == BehaviorConst.BehaviorType.Composite){
            behavior = BehaviorConst.CompositeType.type(subType).createBehavior(config);
            if (config.getValue() != null && config.getValue().length != 0){
                for (int i = 0 ; i < config.getValue().length ; i ++){
                    BehaviorTreeConfig childConfig = ConfigDataManager.getInstance().getById(BehaviorTreeConfig.class,config.getValue()[i]);
                    if (childConfig == null){
                        log.error("BehaviorTreeConfig配置错误," + config.getValue()[i]);
                        continue;
                    }
                    IBehavior childBehavior = createBehavior(monsterActor,childConfig);
                    if (childBehavior == null){
                        continue;
                    }
                    ((BaseComposite)behavior).addService(childBehavior);
                }
            }

        } else if (type == BehaviorConst.BehaviorType.Decorator){
            behavior = BehaviorConst.DecoratorType.type(subType).createBehavior(config);
        } else{
            behavior = BehaviorConst.ActionType.type(subType).createBehavior(config);
        }

        behavior.setPerformer(monsterActor);
        int[] children = config.getNextId();
        if (children != null && children.length != 0){
            for (int i = 0 ; i < children.length ; i ++){
                BehaviorTreeConfig childConfig = ConfigDataManager.getInstance().getById(BehaviorTreeConfig.class,children[i]);
                if (childConfig == null){
                    log.error("BehaviorTreeConfig配置错误," + children[i]);
                    continue;
                }
                IBehavior childBehavior = createBehavior(monsterActor,childConfig);
                if (childBehavior == null){
                    continue;
                }
                behavior.addChild(childBehavior);
            }
        }

        for (int decId : config.getDecorators()){
            BehaviorTreeConfig deConfig = ConfigDataManager.getInstance().getById(BehaviorTreeConfig.class,decId);
            BehaviorConst.BehaviorType detype = BehaviorConst.BehaviorType.type(deConfig.getType());
            if (deConfig == null || detype != BehaviorConst.BehaviorType.Decorator){
                log.error("BehaviorTreeConfig配置错误,配置了错误的装饰器," + decId);
                continue;
            }

            BehaviorConst.DecoratorType decoratorType = BehaviorConst.DecoratorType.type(deConfig.getSubType());
            if (decoratorType == null){
                log.error("DecoratorType类型不存在,subType:" + deConfig.getSubType());
                continue;
            }

            IBehavior decorator = BehaviorConst.DecoratorType.type(deConfig.getSubType()).createBehavior(deConfig);
            decorator.setPerformer(monsterActor);
            behavior.addDecorator(decorator);
        }

        return behavior;
    }
}
