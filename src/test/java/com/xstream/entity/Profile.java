package com.xstream.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author yq
 * @Date 2020/9/7 14:16
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profile {
    private String job;
    private String tel;
    private String remark;
}
