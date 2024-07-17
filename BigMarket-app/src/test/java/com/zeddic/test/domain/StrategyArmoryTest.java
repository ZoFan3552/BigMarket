package com.zeddic.test.domain;

import com.zeddic.domain.strategy.service.IStrategyArmory;
import lombok.extern.slf4j.Slf4j;
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

    @Test
    public void test_strategyArmory(){
        strategyArmory.assembleLotteryStrategy(100001L);
    }

    @Test
    public void test_getAssembleRandomVal(){
        log.info("测试结果：{} - 奖品 ID 值" , strategyArmory.getRandomAwardId(100001L));
        log.info("测试结果：{} - 奖品 ID 值" , strategyArmory.getRandomAwardId(100001L));
        log.info("测试结果：{} - 奖品 ID 值" , strategyArmory.getRandomAwardId(100001L));
        log.info("测试结果：{} - 奖品 ID 值" , strategyArmory.getRandomAwardId(100001L));
        log.info("测试结果：{} - 奖品 ID 值" , strategyArmory.getRandomAwardId(100001L));
    }
}
