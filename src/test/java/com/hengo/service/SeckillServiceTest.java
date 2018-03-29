package com.hengo.service;

import com.hengo.dto.Exposer;
import com.hengo.dto.SeckillExecution;
import com.hengo.entity.Seckill;
import com.hengo.exception.RepeatKillException;
import com.hengo.exception.SeckillCloseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Hengo.
 * 2018/3/29 1:11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void getSeckillList() {
        List<Seckill> seckillList = seckillService.getSeckillList();
        logger.info("seckillList = {}", seckillList);
    }

    @Test
    public void getById() {
        long id = 1004;
        Seckill seckill = seckillService.getById(id);
        logger.info("seckill = {}", seckill);
    }

    /*@Test
    public void exportSeckillUrl() {
        long id = 1004;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        logger.info("exposer = {}", exposer);
    }*/

    /*@Test
    public void executeSeckill() {
        long id = 1004;
        long userPhone = 13900876456L;
        String md5 = "81c5738465cfcb646668b142a1b185a6";
        try {
            SeckillExecution execution = seckillService.executeSeckill(id, userPhone, md5);
            logger.info("result = {}", execution);
        } catch (RepeatKillException e) {
            logger.error(e.getMessage());
        } catch (SeckillCloseException e) {
            logger.error(e.getMessage());
        }
    }*/

    //测试代码完整逻辑, 可以重复执行
    @Test
    public void seckillLogic() {
        long id = 1003;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        if (exposer.isExposed()) {
            logger.info("exposer = {}", exposer);
            long userPhone = 13900876456L;
            String md5 = exposer.getMd5();
            try {
                SeckillExecution execution = seckillService.executeSeckill(id, userPhone, md5);
                logger.info("result = {}", execution);
            } catch (RepeatKillException e) {
                logger.error(e.getMessage());
            } catch (SeckillCloseException e) {
                logger.error(e.getMessage());
            }
        } else {
            // 秒杀未开启
            logger.warn("exposer = {}", exposer);
        }


    }
}