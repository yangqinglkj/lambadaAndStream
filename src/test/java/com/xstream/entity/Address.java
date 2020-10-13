package com.xstream.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author yq
 * @Date 2020/9/7 14:15
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String add;
    private String zipcode;
}
