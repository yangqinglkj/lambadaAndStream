package com.xstream.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author yq
 * @Date 2020/9/7 14:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String name;
    private String age;
    private Profile profile;
    private List<Address> addlist;
}
