package com.kong.fight;

import com.kong.common.obj.PlayerActor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FightManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(FightManager.class);
    private static final FightManager INSTANCE = new FightManager();
    public static FightManager getInstance(){
        return INSTANCE;
    }

    public void playerSkill(PlayerActor playerActor, int skillId, long targetID){
//        SkillConfig skillConfig = ConfigDataManager.getInstance().getById(SkillConfig.class, skillId);
    }

}
