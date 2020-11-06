package com.xstream;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * @Author yq
 * @Date 2020/9/7 14:13
 */

public class XStream {
    @Test
    public void test(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key","key");
        String key = jsonObject.getString("key1");
        System.out.println(key);
    }
}
