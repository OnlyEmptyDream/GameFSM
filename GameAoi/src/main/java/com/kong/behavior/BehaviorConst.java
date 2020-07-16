package com.kong.behavior;

import com.kong.behavior.entity.action.ActionIdle;
import com.kong.behavior.entity.composite.SelectorImpl;
import com.kong.behavior.entity.composite.SequenceImpl;
import com.kong.behavior.entity.decorate.DecoratorAlive;

import java.util.HashMap;
import java.util.Map;


public interface BehaviorConst {

    interface IBehaviorType{
        IBehavior createBehavior(BehaviorTreeConfig config);
        int getType();
    }

    enum ActionType implements IBehaviorType{

        /**
         * 待机
         */
        IDLE(1){
            @Override
            public IBehavior createBehavior(BehaviorTreeConfig config) {
                return new ActionIdle(config);
            }
        }
        ;
        final int type;

        private static Map<Integer,ActionType> actionTypeMap = new HashMap<>();

        static {
            for (ActionType behaviorType : ActionType.values()){
                actionTypeMap.put(behaviorType.getType(),behaviorType);
            }
        }

        ActionType(int type){
            this.type = type;
        }

        public int getType(){
            return this.type;
        }

        public static ActionType type(int type){
            if (!actionTypeMap.containsKey(type)){
                return IDLE;
            }

            return actionTypeMap.get(type);
        }
    }

    enum EStatus{
        Invalid,   //初始状态
        Success,   //成功
        Failure,   //失败
        Running,   //运行
        Aborted,   //终止
    }


    enum DecoratorType implements IBehaviorType{
        /**
         * 是否存活
         */
        Alive(1){
            @Override
            public IBehavior createBehavior(BehaviorTreeConfig config) {
                return new DecoratorAlive(config);
            }
        },

        ;
        private int type;

        private static Map<Integer,DecoratorType> typeMap = new HashMap<>();

        static {
            for (DecoratorType behaviorType : DecoratorType.values()){
                typeMap.put(behaviorType.getType(),behaviorType);
            }
        }

        DecoratorType(int type){
            this.type = type;
        }

        public int getType(){
            return this.type;
        }

        public static DecoratorType type(int type){
            if (!typeMap.containsKey(type)){
                return null;
            }

            return typeMap.get(type);
        }
    }


    enum CompositeType implements IBehaviorType{

        Selector(0){
            @Override
            public IBehavior createBehavior(BehaviorTreeConfig config) {
                return new SelectorImpl(config);
            }
        },

        Sequence(1) {
            @Override
            public IBehavior createBehavior(BehaviorTreeConfig config) {
                return new SequenceImpl(config);
            }
        },
        ;
        private int type;

        CompositeType(int type){
            this.type = type;
        }

        public int getType(){
            return this.type;
        }
        private static Map<Integer,CompositeType> typeMap = new HashMap<>();

        static {
            for (CompositeType behaviorType : CompositeType.values()){
                typeMap.put(behaviorType.getType(),behaviorType);
            }
        }


        public static CompositeType type(int type){
            if (!typeMap.containsKey(type)){
                return Selector;
            }

            return typeMap.get(type);
        }
    }

    enum BehaviorType{
        Composite(0),
        Decorator(1),
        Action(2),
        ;
        private int type;

        BehaviorType(int type){
            this.type = type;
        }

        private static Map<Integer,BehaviorType> typeMap = new HashMap<>();

        static {
            for (BehaviorType behaviorType : BehaviorType.values()){
                typeMap.put(behaviorType.getType(),behaviorType);
            }
        }

        public int getType(){
            return type;
        }

        public static BehaviorType type(int type){
            if (!typeMap.containsKey(type)){
                return Composite;
            }

            return typeMap.get(type);
        }
    }
}
