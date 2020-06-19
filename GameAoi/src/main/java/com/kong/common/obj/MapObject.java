package com.kong.common.obj;

import com.kong.aoi.obj.Vector3f;
import lombok.Data;

@Data
public class MapObject{
    long id;
    Vector3f vector3;
    String name;
    int type;
}
