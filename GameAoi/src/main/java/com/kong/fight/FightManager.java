package com.kong.fight;

import com.kong.cd.CDUtil;
import com.kong.common.obj.PlayerActor;
import com.sh.common.config.ConfigDataManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FightManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(FightManager.class);
    private static final FightManager INSTANCE = new FightManager();
    public static FightManager getInstance(){
        return INSTANCE;
    }

    public void playerSkill(PlayerActor playerActor, int skillId, long targetID){
        SkillConfig skillConfig = ConfigDataManager.getInstance().getById(SkillConfig.class, skillId);
        if(!checkPlayerCd(playerActor, skillConfig, System.currentTimeMillis())){
            return;
        }

    }

    public boolean checkPlayerCd(PlayerActor playerActor, SkillConfig config, long curTime){
        long cd = CDUtil.getCDtime(playerActor, 1, config.getId());
        return cd <= curTime;
    }
}
