package com.hengo.enums;

/**
 * Created by Hengo.
 * 2018/3/28 23:50
 */
public enum SeckillStatEnum {
    SUCCESS(1, "秒杀成功"),
    END(0, "秒杀结束"),
    REPEAT_KILL(-1, "重复秒杀"),
    INNER_ERROR(-2, "系统异常"),
    DATA_REWRITE(-3, "数据篡改");

    private int state;

    private String stateInfo;

    SeckillStatEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static <T extends SeckillStatEnum> T getSeckillStatEnumByState(int state, Class<T> tClass) {
        for (T each : tClass.getEnumConstants()) {
            if (each.getState() == state) {
                return each;
            }
        }
        return null;
    }
}
