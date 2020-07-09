package com.kong.fight;

import com.sh.common.config.AbstractConfigData;

import java.util.List;

/**
 * 技能配置
 *
 * @author qq
 */

@lombok.Getter
@lombok.Setter
public class SkillConfig extends AbstractConfigData {

    private int id;

    private String name;

    private String icon;

    private String icon2;

    private int cls;

    private int isShow;

    private int index;

    private List<int[]> condition;

    private String conditionText;

    private int handlerType;

    private int career;

    private int maxTarget;

    private int canUp;

    private int targetType;

    private int action;

    private int hurtDelay;

    private int hateP;

    private int releaseDis;

    private int costMp;

    private int commonCd;

    private int releaseType;

    private int areaDis;

    private int areaType;

    private int[] params;

    private String tips1;

    private int hit;

    private int put;

    private String effects;

    private int skillBook;

    /**
     * 技能元素属性
     */
    private int[] element;

    /**
     * 秘籍分类
     */
    private int secretSkillType;

    /**
     * 技能是否以自身为中心点1是；0不是
     */
    private int centralAreaDis;
}