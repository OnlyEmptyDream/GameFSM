package com.kong.fsm;

import com.kong.fsm.heart.ExecutorUtil;
import com.kong.fsm.heart.MonsterHeart;
import com.kong.fsm.monster.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DoMonsterAI {
    public static void main(String[] args) {
        MonsterActor monster = new MonsterActor();
        // 设置状态机
        List<FSMState<MonsterActor>> states = new ArrayList<>();
        states.add(new MonsterSleepState(FSMState.Sleep, monster));
        states.add(new MonsterActiveState(FSMState.Active, monster));
        states.add(new MonsterDieState(FSMState.Die, monster));
        states.add(new MonsterFightState(FSMState.Fight, monster));
        monster.setMachine(new FSMMachine<>(states, new MonsterSleepState(FSMState.Sleep, monster)));
        ExecutorUtil.scheduleAtFixedRate(new MonsterHeart(monster), -1, 2000);

        Scanner input = new Scanner(System.in);
        while(input.hasNext()){
            String str = input.nextLine();
            switch (str){
                case "1":
                    System.out.println("发现玩家");
                    monster.getMachine().getAiData().setFindPlayer(true);
                    break;
                case "2":
                    System.out.println("被玩家攻击");
                    monster.setWhoAttackMe(1);
                    break;
                case "3":
                    System.out.println("被玩家杀死");
                    monster.setDead(true);
                    monster.setWhoAttackMe(0);
                    monster.getMachine().getAiData().setFindPlayer(false);
                    break;
                case "4":
                    System.out.println("开始重生");
                    monster.setDead(false);
                    break;
            }
        }
        input.close();
    }
}
