package com.hengo.entity;

import java.util.Date;

/**
 * Created by Hengo.
 * 2018/3/27 15:24
 */
public class Seckill {
    /**
     * 主键ID
     */
    private long seckillId;
    /**
     * 秒杀商品名字
     */
    private String name;
    /**
     * 秒杀的商品编号
     */
    private int number;
    /**
     * 开始秒杀的时间
     */
    private Date startTime;
    /**
     * 结束秒杀的时间
     */
    private Date endTime;
    /**
     * 创建的时间
     */
    private Date createTime;

    @Override
    public String toString() {
        return "Seckill{" +
                "seckillId=" + seckillId +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", createTime=" + createTime +
                '}';
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
