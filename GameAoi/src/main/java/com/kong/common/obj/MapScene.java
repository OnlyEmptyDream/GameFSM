package com.kong.common.obj;

import com.kong.aoi.AOIEventListenerImpl;
import com.kong.aoi.MapObjectType;
import com.kong.aoi.TowerAOI;
import com.kong.aoi.obj.Vector2f;
import com.kong.aoi.obj.Vector3f;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Getter
@Setter
public class MapScene {
    private  static MapScene INSTANCE = new MapScene();

    public static MapScene getInstance(){
        return INSTANCE;
    }

    public MapScene() {
        aoi = new TowerAOI(100,100,0,0);
        aoi.addListener(AOIEventListenerImpl.getInstance());
    }

    protected TowerAOI aoi;

    protected ConcurrentMap<Long, MapObject> objectMap = new ConcurrentHashMap<>();
    protected ConcurrentMap<Long, MapObject> playerMap = new ConcurrentHashMap<>();
    protected ConcurrentMap<Long, MapObject> monsterMap = new ConcurrentHashMap<>();

    public void enterPlayer(PlayerActor playerActor, Vector3f v3){
        playerActor.setVector3(v3);
        objectMap.put(playerActor.getId(), playerActor);
        playerMap.put(playerActor.getId(), playerActor);
        aoi.addObject(playerActor, playerActor.getVector3().toVector2f());
        aoi.addWatcher(playerActor, playerActor.getVector3().toVector2f());
    }

    public void enterMonter(MonsterActor monster, Vector3f v3){
        monster.setVector3(v3);
        objectMap.put(monster.getId(), monster);
        monsterMap.put(monster.getId(), monster);
        aoi.addObject(monster, monster.getVector3().toVector2f());
    }

    public void movePosition(MapObject obj, Vector3f v3){
        Vector2f v2 = v3.toVector2f();
        aoi.updateObject(obj, v2);
        if(obj.getType() == MapObjectType.Player){
            aoi.updateWatcher(obj, v2);
        }
    }
}
