package com.hengo.dao;

import com.hengo.entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by Hengo.
 * 2018/3/28 17:38
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {

    @Resource
    private SuccessKilledDao successKilledDao;

    @Test
    public void insertSuccessKilled() {
        /**
         * 第一次: i = 1;
         * 第二次: i = 0; (一个用户只能秒杀一次)
         */
        int i = successKilledDao.insertSuccessKilled( 1000L, 13900000000L);
        System.out.println(i);
    }

    @Test
    public void queryByIdWithSeckill() {
        SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(1000L, 13900000000L);
        System.out.println(successKilled);
        System.out.println(successKilled.getSeckill());
    }
}