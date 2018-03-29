package com.hengo.entity;

import java.util.Date;

/**
 * Created by Hengo.
 * 2018/3/27 15:29
 */
public class SuccessKilled {
    /**
     * 秒杀商品ID
     */
    private long seckillId;
    /**
     * 用户手机号
     */
    private long userPhone;
    /**
     * 状态标示
     */
    private short state;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 多对一
     */
    private Seckill seckill;

    public Seckill getSeckill() {
        return seckill;
    }

    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(long userPhone) {
        this.userPhone = userPhone;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SuccessKilled{" +
                "seckillId=" + seckillId +
                ", userPhone=" + userPhone +
                ", state=" + state +
                ", createTime=" + createTime +
                ", seckill=" + seckill +
                '}';
    }
}
