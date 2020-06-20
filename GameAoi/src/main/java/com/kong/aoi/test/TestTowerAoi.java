package com.kong.aoi.test;

import com.kong.aoi.TowerAOI;
import com.kong.aoi.obj.Vector3f;
import com.kong.common.obj.MapScene;
import com.kong.common.obj.MonsterActor;
import com.kong.common.obj.PlayerActor;

public class TestTowerAoi {

    public static void main(String[] args) {
        MapScene mapScene = MapScene.getInstance();
        MonsterActor monsterActor1 = new MonsterActor(1, "怪物A");
        PlayerActor playerActor1 = new PlayerActor(2, "侠客甲");
        PlayerActor playerActor2 = new PlayerActor(3, "侠客乙");
        MonsterActor monsterActor2 = new MonsterActor(4, "怪物B");

        mapScene.enterPlayer(playerActor1, new Vector3f(51,50,0));
        mapScene.enterMonter(monsterActor1, new Vector3f(50,50,0));
        mapScene.enterPlayer(playerActor2, new Vector3f(49,49,0));
        mapScene.enterMonter(monsterActor2, new Vector3f(50,53,0));
        mapScene.movePosition(playerActor1, new Vector3f(51, 54, 0));
    }
}
