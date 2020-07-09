package com.kong.common.obj;

import com.kong.aoi.AOIEventListenerImpl;
import com.kong.aoi.MapObjectType;
import com.kong.aoi.TowerAOI;
import com.kong.aoi.obj.Vector2f;
import com.kong.aoi.obj.Vector3f;
import com.kong.common.constant.Dir;
import com.kong.path.GeomUtil;
import com.kong.path.PathFinder;
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
        int width = 21;
        int height = 21;
        pointArray = createEightTree(width, height, pointArray);
        aoi = new TowerAOI(width,height,0,0);
        aoi.addListener(AOIEventListenerImpl.getInstance());
        pathFinder = new PathFinder(width, height);
    }

    Vector2f pointArray[][];

    protected PathFinder pathFinder;

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
            aoi.updateWatcher(obj, v3);
        }
    }


    //寻路相关
    private Vector2f[][] createEightTree(int width, int height, Vector2f pointArray[][]) {
        pointArray = new Vector2f[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Vector2f point = new Vector2f(x, y);
                pointArray[x][y] = point;
            }
        }
        // 构建八叉树
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Vector2f p = pointArray[x][y];
                // 遍历该格子周围的格子，进行周围是否能走的判断
                Vector2f[] nears = new Vector2f[8];
                for (int index = 0; index < 16; index += 2) {
                    int tx = x + GeomUtil.EIGHT_DIR_OFFSET[index];
                    int ty = y + GeomUtil.EIGHT_DIR_OFFSET[index + 1];
                    if (tx < 0 || ty < 0 || tx >= width || ty >= height) {
                        continue;
                    }
                    Vector2f tp = pointArray[tx][ty];
                    Dir dir = GeomUtil.getDir(x, y, tx, ty);
                    nears[dir.getIndex()] = tp;
                }
                p.setNears(nears);
            }
        }
        return pointArray;
    }
}
