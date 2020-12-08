package com.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author yq
 * @Date 2020/12/8 17:00
 */
public enum Color {
    /**
     * 红色
     */
    RED("red", "红色"),
    /**
     * 绿色
     */
    GREEN("green", "绿色"),
    /**
     * 黑色
     */
    BLANK("blank", "黑色"),
    /**
     * 黄色
     */
    YELLOW("yellow", "黄色");


    private final String name;
    private final String value;

    public String getName() {
        return name;
    }


    public String getValue() {
        return value;
    }


    Color(String name, String value) {
        this.name = name;
        this.value = value;
    }

    /**
     * 将数据缓存到map中
     */
    private static final Map<String, String> map = new HashMap<>();

    static {
        for (Color color : Color.values()) {
            map.put(color.getName(), color.getValue());
        }
    }

    /**
     * 根据name查询value值
     */
    public static String getValueByName(String name) {
        return map.get(name);
    }

    public static void main(String[] args) {
        String yellow = Color.getValueByName("red");
        Color red = Color.RED;

        System.out.println(red.getName()+red.getValue());
    }
}
