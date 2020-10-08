package com.time;

import java.time.temporal.Temporal;

/**
 * @Author yq
 * @Date 2020/8/31 15:43
 */
@FunctionalInterface
public interface TemporalAdjuster {
    Temporal adjustInto(Temporal temporal);
}
