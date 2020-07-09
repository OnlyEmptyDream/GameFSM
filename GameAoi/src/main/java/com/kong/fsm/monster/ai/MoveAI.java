package com.kong.fsm.monster.ai;

import com.kong.aoi.obj.Vector2f;
import com.kong.common.constant.Dir;
import com.kong.common.obj.MapObject;
import com.kong.common.obj.MapScene;
import com.kong.common.obj.MonsterActor;
import com.kong.path.GeomUtil;
import com.kong.path.PathFinder;

import java.util.List;

public class MoveAI extends AbstractAI {
    List<Vector2f> pathList;
    Vector2f targetPos;
    @Override
    public boolean activeUpdate(MonsterActor monster, int dt) {
        MapObject target = MapScene.getInstance().getObjectMap().get(monster.getWhoActiveMe());
        if(targetPos == null || targetPos == target.getVector3().toVector2f()){
            pathList = MapScene.getInstance().getPathFinder().route(monster.getVector3().toVector2f(), target.getVector3().toVector2f(),100);
        }
        targetPos = target.getVector3().toVector2f();
        if(pathList.size() > 0){
            Dir dir = GeomUtil.getDir(monster.getVector3().getX(), monster.getVector3().getY(), pathList.get(0).getX(), pathList.get(0).getY());
            monster.setVector3(pathList.get(0).getX(), pathList.get(0).getY(), monster.getVector3().getZ());
            System.out.println(monster.getName() + ":向" + dir.getChineseName(dir.getIndex()) + "发生移动,当前位置" + monster.getVector3().toVector2f().toString());
        }
        return true;
    }
}
