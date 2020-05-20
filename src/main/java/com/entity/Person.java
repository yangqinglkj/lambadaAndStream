package com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yangqing
 * @since 2020/5/16  17:07
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String city;
    private String surname;
    private Integer sum;
}
