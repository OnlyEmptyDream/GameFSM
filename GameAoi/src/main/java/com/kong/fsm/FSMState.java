package com.kong.fsm;

import com.kong.aoi.MapObjectType;
import com.kong.aoi.obj.Vector3f;
import com.kong.common.obj.MapObject;
import com.kong.common.obj.MapScene;
import com.kong.common.obj.Performer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 状态机中的状态
 * @param <T>
 */
public class FSMState<T extends Performer> {
    protected static final Logger LOGGER = LoggerFactory.getLogger(FSMState.class);

    public static final int Sleep = 1;

    public static final int Active = 2;

    public static final int Fight = 3;

    public static final int Die = 4;

    public static final int Animate = 5;



    public static final int SleepDistance = 130;
    public static final int ActiveDistance = 3;
    public static final int FightDistance = 2;
    public static final int DieDistance = 1;

    protected int type;

    /**
     * 执行者
     */
    protected T performer;

    public FSMState(int type, T performer){
        this.type = type;
        this.performer = performer;
    }

    /**
     * 进入状态
     *
     */
    public void enter() {

    }

    /**
     * 退出状态
     *
     */
    public void exit() {

    }

    /**
     * 更新状态
     *
     * @param delta
     */
    public void update(int delta) {
    }

    /**
     * 检查状态转换
     *
     * @return
     */
    public int checkTransition() {
        MapScene mapScene = MapScene.getInstance();
        int minDistance = Integer.MAX_VALUE;
        MapObject effectPlayer = null;
        for(MapObject player : mapScene.getPlayerMap().values()){
            int distance = getDistance(player.getVector3(), performer.getVector3());
            if(minDistance > distance){
                minDistance = distance;
                effectPlayer = player;
            }
        }
        if(effectPlayer != null){
            changeStateByDistance(performer, effectPlayer, minDistance, false);
        }
        performer.setWhoActiveMe(performer.getTargetObject().getId());
        return 0;
    }

    private int changeStateByDistance(MapObject obj, MapObject player, int distance, boolean changeOnce) {
        //实现一下简单的状态机AI
        int nowState = 0;
        if(distance >= SleepDistance){
                if(obj.getTempChangeStateType() != FSMState.Die){
                    nowState = FSMState.Sleep;
                    obj.setTempChangeStateType(FSMState.Sleep);
                    obj.setTargetObject(player);
                }
            }else if(distance < SleepDistance && distance >= ActiveDistance){
                if(obj.getTempChangeStateType() != FSMState.Die){
                    nowState = FSMState.Active;
                    obj.setTempChangeStateType(FSMState.Active);
                    obj.setTargetObject(player);
                }
            }else if(distance < ActiveDistance && distance >= FightDistance){
                if(obj.getTempChangeStateType() != FSMState.Die){
                    nowState = FSMState.Fight;
                    obj.setTempChangeStateType(FSMState.Fight);
                    obj.setTargetObject(player);
                }
            }else if(distance < FightDistance){
                if(obj.getTempChangeStateType() != FSMState.Die){
                    nowState = FSMState.Die;
                    obj.setTempChangeStateType(FSMState.Die);
                    obj.setTargetObject(player);
                }
            }
        return nowState;
    }


    /**
     * 拷贝原状态机休眠时间到当前状态机
     *
     * @param sleepTime
     */
    public void copyTime(int sleepTime) {

    }

    /**
     * 获取当前状态机休眠时间
     *
     * @return
     */
    public int getTime() {
        return 0;
    }

    public int getDistance(Vector3f startV3, Vector3f endV3){
        return (int)Math.sqrt (Math.pow(startV3.getX() - endV3.getX(), 2) + Math.pow(startV3.getY() - endV3.getY(), 2) + Math.pow(startV3.getZ() - endV3.getZ(), 2));
    }
}
