package com.kong.aoi.test;

import com.kong.aoi.PointAoi;
import com.kong.aoi.Tower;
import com.kong.aoi.TowerAOI;
import com.kong.common.obj.MapObject;
import com.kong.common.obj.MapScene;
import com.kong.aoi.obj.Vector3f;

public class AoiUtil {
    public static MapScene mapScene = MapScene.getInstance();

    public static void moveObject(){
//        PointAoi pointAoi = mapScene.getAoi().transPos(vector3f.toVector2f());
//        Tower tower = mapScene.getAoi().getTowers()[pointAoi.getX()][pointAoi.getY()];
//        tower.addObject(mapObject);
    }
    public static void putDownObject(MapObject mapObject, Vector3f vector3f){
        PointAoi pointAoi = mapScene.getAoi().transPos(vector3f.toVector2f());
        Tower tower = mapScene.getAoi().getTowers()[pointAoi.getX()][pointAoi.getY()];
        tower.addObject(mapObject);
    }
    public static void takeOnObject(){

    }
//    "WSADER" change pos
}
