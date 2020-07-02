package com.kong.cd;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CD {
    int type;

    long key;

    long endTime;//cd结束时间

//    public CDBean toCDBean(){
//        return CDBean.newBuilder(type).setType(key).setKey.setEndTime(endTime).build();
//    }
}
