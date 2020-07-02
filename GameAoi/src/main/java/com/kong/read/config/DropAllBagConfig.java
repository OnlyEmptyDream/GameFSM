package com.kong.read.config;

import com.sh.common.config.AbstractConfigData;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class DropAllBagConfig extends AbstractConfigData {
    private int id;
    private int bagId;
    private int desc;
    private int type;
    private int itemId;
    private int itemNum;
    private int isShow;
}

