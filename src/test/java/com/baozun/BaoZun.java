package com.baozun;

import java.util.*;

public class BaoZun {

    public static void main(String[] args) {
        Scanner x = new Scanner(System.in);
        System.out.println("请输入语句");
        String s = x.nextLine();
        String result = replace(s);
        System.out.println("得到的是："+result);

    }

    public static String replace(String str) {
        List<String> list = Collections.singletonList("nice");
        for (String s : list) {
            if (str.contains(s)) {
                StringBuilder s1 = new StringBuilder();
                for (int i = 0; i < s.length(); i++) {
                    s1.append("*");
                }
                str = str.replace(s, s1.toString());
            }
        }
        return str;
    }


}
