package com.entity;

/**
 * @Author yq
 * @Date 2020/11/5 10:02
 */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "area")
@Data
public class Area {
    private Map<String,String> map;
    private String name;
}
