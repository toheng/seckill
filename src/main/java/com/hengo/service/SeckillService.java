package com.hengo.service;

import com.hengo.dto.Exposer;
import com.hengo.dto.SeckillExecution;
import com.hengo.entity.Seckill;
import com.hengo.exception.RepeatKillException;
import com.hengo.exception.SeckillCloseException;
import com.hengo.exception.SeckillException;

import java.util.List;

/**
 * 业务接口: 站在"使用者"的角度
 * 三个方面: 方法定义粒度, 参数, 返回值类型(return 类型/异常)
 * Created by Hengo.
 * 2018/3/28 18:16
 */
public interface SeckillService {

    /**
     * 查询所有秒杀记录
     *
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 查询单个秒杀记录
     *
     * @param seckillId
     * @return
     */
    Seckill getById(long seckillId);

    /**
     * 秒杀开启输出秒杀接口地址,
     * 否则输出系统时间和秒杀时间
     *
     * @param seckillId
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀操作
     *
     * @param seckillId
     * @param userPhone
     * @param md5
     */
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException, RepeatKillException, SeckillCloseException;


    /**
     * 执行秒杀操作by 存储过程
     *
     * @param seckillId
     * @param userPhone
     * @param md5
     */
    SeckillExecution executeSeckillProcedure(long seckillId, long userPhone, String md5);

}

