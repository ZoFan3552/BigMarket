package com.zeddic.test.domain;

import com.alibaba.fastjson.JSON;
import com.zeddic.domain.strategy.service.armory.IStrategyArmory;
import com.zeddic.domain.strategy.service.rule.chain.ILogicChain;
import com.zeddic.domain.strategy.service.rule.chain.impl.RuleWeightLogicChain;
import com.zeddic.domain.strategy.service.rule.chain.factory.DefaultChainFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import javax.annotation.Resource;


/**
 * @author: zeddic
 * @description: 抽奖责任链测试，验证不同的规则走不同的责任链
 * @date: 2024/7/21 下午9:33
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class LogicChainTest {

    @Resource
    private IStrategyArmory strategyArmory;
    @Resource
    private RuleWeightLogicChain ruleWeightLogicChain;
    @Resource
    private DefaultChainFactory defaultChainFactory;

    @Before
    public void setUp() {
        // 策略装配 100001、100002、100003
        log.info("测试结果：{}", strategyArmory.assembleLotteryStrategy(100001L));
        log.info("测试结果：{}", strategyArmory.assembleLotteryStrategy(100002L));
        log.info("测试结果：{}", strategyArmory.assembleLotteryStrategy(100003L));
    }

    @Test
    public void test_LogicChain_rule_blacklist() {
        ILogicChain logicChain = defaultChainFactory.openLogicChain(100001L);
        DefaultChainFactory.StrategyAwardVO user001 = logicChain.performRaffle("user001", 100001L);
        log.info("测试结果：{}", JSON.toJSONString(user001));
    }

    @Test
    public void test_LogicChain_rule_weight() {
        // 通过反射 mock 规则中的值
        ReflectionTestUtils.setField(ruleWeightLogicChain, "userScore", 4900L);

        ILogicChain logicChain = defaultChainFactory.openLogicChain(100001L);
        DefaultChainFactory.StrategyAwardVO user001 = logicChain.performRaffle("xiaofuge", 100001L);
        log.info("测试结果：{}", JSON.toJSONString(user001));
    }

    @Test
    public void test_LogicChain_rule_default() {
        ILogicChain logicChain = defaultChainFactory.openLogicChain(100001L);
        DefaultChainFactory.StrategyAwardVO user001 = logicChain.performRaffle("xiaofuge", 100001L);
        log.info("测试结果：{}", JSON.toJSONString(user001));
    }

}
