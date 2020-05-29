package com.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yangqing
 * @since 2020/5/5  15:37
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class User {
    private String name;
    private int age;
    private double salary;
    private Status status;

    public enum Status{
        FREE,
        BUSY,
        VACATION,
    }
}
