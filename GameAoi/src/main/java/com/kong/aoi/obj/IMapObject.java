package com.kong.aoi.obj;

public interface IMapObject {
    long getId();

    void setId(long id);

    int getConfigId();

    void setConfigId(int configId);

    int getType();

    int getMapId();

    void setMapId(int mapId);

    int getLine();

    void setLine(int line);

    boolean isVisible();

    void setVisible(boolean visible);

    Vector3f getVector3();

    void setVector3(Vector3f v3);

    String getName();

    void setName(String name);

    String getShowName();
    boolean penetrate(IMapObject obj, boolean cross);

    boolean overlying(IMapObject obj, boolean cross);

    boolean isEnemy(IMapObject obj, boolean ignoreTargetOnly);

    boolean isFriend(IMapObject obj, boolean ignoreTargetOnly);

    long getRid();

    int getViewRange();

    int getHostId();
}

