package com.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author yq
 * @Date 2020/12/8 17:22
 */

public enum AttractionsEnum {
    /**
     * 崖州古城
     */
    YZ_ZHOU_GU_CHENG("崖州古城", "4069462959793577915"),

    DA_XIAO_DONG_TIAN("大小洞天景区","4069461880929738304"),

    MEI_GUI_GU("海南玫瑰谷","4069283472388272897"),

    LU_HUI_TOU("鹿回头风景区","4069281244018616740"),

    BU_YE_CHENG("海昌梦幻海洋不夜城","4069846805240994142"),

    QIAN_GU_QING("三亚千古情","4069470668819008983"),

    DA_DONG_HAI("大东海景区","4069282708340861593"),

    SEN_LIN_GONG_YUAN("热带天堂森林公园","4069283618460972764"),

    XI_DAO("西岛旅游区","4069280573448888914"),

    NAN_SHAN("海南南山景区","4069462018333514283"),

    WU_ZHI_ZHOU("蜈支洲岛","4069846942670665018"),

    TIAN_YA("天涯海角","4069468482687799490");

    private final String name;
    private final String value;

    AttractionsEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    /**
     * 获取所有枚举值
     * @return
     */
    public static List<String> getAllAttractionsEnumValue(){
        List<String> list = new ArrayList<>();
        for (AttractionsEnum attractionsEnum : AttractionsEnum.values()) {
            list.add(attractionsEnum.getValue());
        }
        return list;
    }

    /**
     * 获取所有枚举
     * @return
     */
    public static List<AttractionsEnum> getAllAttractionsEnum(){
        return new ArrayList<>(Arrays.asList(AttractionsEnum.values()));
    }

    public static void main(String[] args) {
        List<AttractionsEnum> list = AttractionsEnum.getAllAttractionsEnum();
        System.out.println(list);
    }
}
