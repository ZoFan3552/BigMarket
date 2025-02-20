package com.zeddic.test.domain;

import com.zeddic.domain.strategy.service.armory.IStrategyArmory;
import com.zeddic.domain.strategy.service.armory.IStrategyDispatch;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author: zeddic
 * @description:
 * @date: 2024/7/16 下午6:25
 */
@SpringBootTest
@Slf4j
@RunWith(SpringRunner.class)
public class StrategyArmoryTest {

    @Resource
    private IStrategyArmory strategyArmory;

    @Resource
    private IStrategyDispatch strategyDispatch;

    @Test
    public void test_strategyArmory(){
        log.info("测试结果：{}" , strategyArmory.assembleLotteryStrategy(100001L));
        log.info("测试结果：{}" , strategyArmory.assembleLotteryStrategy(100002L));
        log.info("测试结果：{}" , strategyArmory.assembleLotteryStrategy(100003L));
    }

    @Test
    public void test_getAssembleRandomVal(){
        log.info("测试结果：{} - 奖品 ID 值" , strategyDispatch.getRandomAwardId(100001L));
        log.info("测试结果：{} - 奖品 ID 值" , strategyDispatch.getRandomAwardId(100001L));
        log.info("测试结果：{} - 奖品 ID 值" , strategyDispatch.getRandomAwardId(100001L));
        log.info("测试结果：{} - 奖品 ID 值" , strategyDispatch.getRandomAwardId(100001L));
        log.info("测试结果：{} - 奖品 ID 值" , strategyDispatch.getRandomAwardId(100001L));
    }

    @Test
    public void test_getAssembleRandomVal_ruleWeightValue(){
        log.info("测试结果：{} - 4000 策略配置" , strategyDispatch.getRandomAwardId(100001L , "4000:102,103,104,105"));
        log.info("测试结果：{} - 5000 策略配置" , strategyDispatch.getRandomAwardId(100001L , "5000:102,103,104,105,106,107"));
        log.info("测试结果：{} - 6000 策略配置" , strategyDispatch.getRandomAwardId(100001L , "6000:102,103,104,105,106,107,108,109"));
    }
}
