package com.kong.read.config;

import com.sh.common.config.ConfigDataManager;

public class TestReadConfig {
    public static void main(String[] args) {
//        ConfigDataManager.getInstance().init(args[0]);
        String filePath = "E:\\project\\svn1\\ServerData\\trunk\\data\\";
        ConfigDataManager.getInstance().init(filePath);
        DropAllBagConfig config = ConfigDataManager.getInstance().getById(DropAllBagConfig.class, 1);
        System.out.println(config.toString());
    }
}
