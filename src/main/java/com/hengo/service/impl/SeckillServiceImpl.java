package com.hengo.service.impl;

import com.hengo.dao.SeckillDao;
import com.hengo.dao.SuccessKilledDao;
import com.hengo.dto.Exposer;
import com.hengo.dto.SeckillExecution;
import com.hengo.entity.Seckill;
import com.hengo.entity.SuccessKilled;
import com.hengo.enums.SeckillStateEnum;
import com.hengo.exception.RepeatKillException;
import com.hengo.exception.SeckillCloseException;
import com.hengo.exception.SeckillException;
import com.hengo.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by Hengo.
 * 2018/3/28 22:20
 */
@Service
public class SeckillServiceImpl implements SeckillService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillDao seckillDao;

    @Autowired
    private SuccessKilledDao successKilledDao;

    // md5盐值字符串, 用于混淆md5
    private final String salt = "aswrdsf46%#$2[]ERT3e";

    @Override
    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0, 4);
    }

    @Override
    public Seckill getById(long SeckillId) {
        return seckillDao.queryById(SeckillId);
    }

    @Override
    public Exposer exportSeckillUrl(long seckillId) {
        Seckill seckill = seckillDao.queryById(seckillId);
        if (seckill == null) {
            return new Exposer(false, seckillId);
        }
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        Date nowTime = new Date();
        if (nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()) {
            return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
        }
        String md5 = getMD5(seckillId);
        return new Exposer(true, md5, seckillId);
    }

    /**
     * 生成md5, 采用apache提供的开源项目 commons-codec.jar 自带的MD5生成
     *
     * @param seckillId
     * @return
     */
    private String getMD5(long seckillId) {
        String base = seckillId + "/" + salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    @Override
    @Transactional
    // 使用注解控制事务方法的优点: 开发团队明确标注风格
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException, SeckillCloseException, RepeatKillException {
        if (md5 == null || (!md5.equals(getMD5(seckillId)))) {
            throw new SeckillException("seckill data overwritten");
        }
        // 执行秒杀逻辑: 减库存 + 记录购买行为
        Date nowTime = new Date();

        try {
            // 减库存
            int updateCount = seckillDao.reduceNumber(seckillId, nowTime);
            if (updateCount <= 0) {
                // 没有更新到记录,秒杀结束
                throw new SeckillCloseException("seckill is closed");
            } else {
                // 记录购买行为
                int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
                // 唯一: seckillId, userPhone
                if (insertCount <= 0) {
                    // 重复秒杀
                    throw new RepeatKillException("seckill repeated");
                } else {
                    // 秒杀成功
                    SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
                    return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS, successKilled);
                }
            }
        } catch (SeckillCloseException e1) {
            throw e1;
        } catch (RepeatKillException e2) {
            throw e2;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            // 所有编译时异常转化为运行时异常
            throw new SeckillException("seckill inner error" + e.getMessage());
        }
    }
}
