package com.kong.cd;

public class CDUtil {
    public static boolean checkCD(CDObject object, int cdType, long key){
        String cdKey = getCDKey(cdType, key);
        CD cd = object.getCdMap().get(cdKey);
        if (cd != null) {
            long nowTime = System.currentTimeMillis();
            if(cd.endTime > nowTime){
                return false;
            }
        }
        return true;
    }

    public static long getCDtime(CDObject object, int cdType, long key){
        String cdKey = getCDKey(cdType, key);
        CD cd = object.getCdMap().get(cdKey);
        if(cd != null){
            return cd.endTime;
        }
        return 0;
    }

    public static boolean addCD(CDObject object, int cdType, long key, long endTime, long roleId){
        String cdKey = getCDKey(cdType, key);
        CD cd = new CD(cdType, key, endTime);
        object.getCdMap().put(cdKey, cd);
        if(roleId != 0){
            // sendCDChangeMessageToclient();
        }
        return true;
    }

    public static String getCDKey(int cdType, long key){
        return cdType + "_" + key;
    }
}
