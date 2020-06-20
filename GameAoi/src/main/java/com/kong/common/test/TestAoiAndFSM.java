package com.kong.common.test;

import com.kong.aoi.obj.Vector3f;
import com.kong.common.obj.MapScene;
import com.kong.common.obj.MonsterActor;
import com.kong.common.obj.PlayerActor;
import com.kong.fsm.FSMMachine;
import com.kong.fsm.FSMState;
import com.kong.fsm.heart.ExecutorUtil;
import com.kong.fsm.heart.MonsterHeart;
import com.kong.fsm.monster.MonsterActiveState;
import com.kong.fsm.monster.MonsterDieState;
import com.kong.fsm.monster.MonsterFightState;
import com.kong.fsm.monster.MonsterSleepState;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestAoiAndFSM {
    public static void main(String[] args) {
        MapScene mapScene = MapScene.getInstance();
        //将对象加入地图中
        MonsterActor monsterActor1 = new MonsterActor(1, "怪物A");
        // 设置状态机
        List<FSMState<MonsterActor>> states = new ArrayList<>();
        states.add(new MonsterSleepState(FSMState.Sleep, monsterActor1));
        states.add(new MonsterActiveState(FSMState.Active, monsterActor1));
        states.add(new MonsterDieState(FSMState.Die, monsterActor1));
        states.add(new MonsterFightState(FSMState.Fight, monsterActor1));
        monsterActor1.setMachine(new FSMMachine<>(states, states.get(0)));
        ExecutorUtil.scheduleAtFixedRate(new MonsterHeart(monsterActor1), -1, 2000);

        PlayerActor playerActor1 = new PlayerActor(2, "侠客甲");
        PlayerActor playerActor2 = new PlayerActor(3, "侠客乙");

        mapScene.enterPlayer(playerActor1, new Vector3f(53,50,0));
        mapScene.enterMonter(monsterActor1, new Vector3f(50,50,0));
//        mapScene.enterPlayer(playerActor2, new Vector3f(49,49,0));

        Scanner input = new Scanner(System.in);
        while(input.hasNext()){
            String str = input.nextLine();
            switch (str){
                case "w":
                   mapScene.movePosition(playerActor1, addY(playerActor1.getVector3(), 1));
                   break;
                case "s":
                    mapScene.movePosition(playerActor1, addY(playerActor1.getVector3(), -1));
                    break;
                case "a":
                    mapScene.movePosition(playerActor1, addX(playerActor1.getVector3(), -1));
                    break;
                case "d":
                    mapScene.movePosition(playerActor1, addX(playerActor1.getVector3(), 1));
                    break;
            }
        }
        input.close();
    }

    public static Vector3f addX(Vector3f v3, int pos){
        int x = v3.getX() + pos < 0 ? v3.getX() : v3.getX() + pos;
        return new Vector3f(x, v3.getY(), v3.getZ());
    }

    public static Vector3f addY(Vector3f v3, int pos){
        int y = v3.getY() + pos < 0 ? v3.getY() : v3.getY() + pos;
        return new Vector3f(v3.getX(), y, v3.getZ());
    }
}
