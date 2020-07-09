package com.kong.fsm.monster.ai;

import com.kong.fsm.AIType;

import java.util.HashMap;
import java.util.Map;

public class AIFactory {
    private static final Map<Integer, ObjectAI> AI_MAP = new HashMap<>();
    static{
        AI_MAP.put(AIType.NORMAL, new AbstractAI());
        AI_MAP.put(AIType.MOVE, new MoveAI());
    }
    public static ObjectAI getAI(int type){
        return AI_MAP.getOrDefault(type, null);
    }
}
