package com.hackdog.springbootlearn.util;

/**
 * 定时任务状态
 */
public enum ScheduleStatus {
    /**
     * 正常
     */
    NORMAL(1),
    /**
     * 暂停
     */
    PAUSE(0);

    private int value;

    ScheduleStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
