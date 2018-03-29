package com.hengo.dto;

import com.hengo.entity.SuccessKilled;
import com.hengo.enums.SeckillStateEnum;

/**
 * 封装执行秒杀的结果
 * Created by Hengo.
 * 2018/3/28 21:53
 */
public class SeckillExecution {

    private long SeckillId;

    /**
     * 秒杀执行结果状态
     */
    private int state;

    /**
     * 状态表示
     */
    private String stateInfo;

    /**
     * 秒杀成功的对象
     */
    private SuccessKilled successKilled;

    @Override
    public String toString() {
        return "SeckillExecution{" +
                "SeckillId=" + SeckillId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", successKilled=" + successKilled +
                '}';
    }

    public SeckillExecution(long seckillId, SeckillStateEnum stateEnum, SuccessKilled successKilled) {
        SeckillId = seckillId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.successKilled = successKilled;
    }

    public SeckillExecution(long seckillId, SeckillStateEnum stateEnum) {
        SeckillId = seckillId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    public long getSeckillId() {
        return SeckillId;
    }

    public void setSeckillId(long seckillId) {
        SeckillId = seckillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessKilled getSuccessKilled() {
        return successKilled;
    }

    public void setSuccessKilled(SuccessKilled successKilled) {
        this.successKilled = successKilled;
    }
}
