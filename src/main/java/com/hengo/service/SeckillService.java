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
     * @param SeckillId
     * @return
     */
    Seckill getById(long SeckillId);

    /**
     * 秒杀开启是否输出秒杀地址, 否则显示系统时间和秒杀时间
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀操作
     *
     * @param seckillId
     * @param userPhone
     * @param md5
     * @return
     */
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException, SeckillCloseException, RepeatKillException;

}
