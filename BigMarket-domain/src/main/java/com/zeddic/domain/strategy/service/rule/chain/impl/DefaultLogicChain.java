package com.zeddic.domain.strategy.service.rule.chain.impl;

import com.zeddic.domain.strategy.service.armory.IStrategyDispatch;
import com.zeddic.domain.strategy.service.rule.chain.AbstractLogicChain;
import com.zeddic.domain.strategy.service.rule.chain.factory.DefaultChainFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author: zeddic
 * @description: 责任链——兜底结点，最后执行
 * @date: 2024/7/21 下午8:42
 */
@Slf4j
@Component("rule_default")
public class DefaultLogicChain extends AbstractLogicChain {
    @Resource
    private IStrategyDispatch strategyDispatch;

    @Override
    public DefaultChainFactory.StrategyAwardVO performRaffle(String userId, Long strategyID) {
        Integer awardId = strategyDispatch.getRandomAwardId(strategyID);
        log.info("抽奖责任链-默认处理 userId:{} strategyId:{} ruleModel:{} awardID:{}",
                userId, strategyID, ruleModel() , awardId);
        return DefaultChainFactory.StrategyAwardVO.builder()
                .awardId(awardId)
                .logicModel(ruleModel())
                .build();
    }

    @Override
    protected String ruleModel() {
        return DefaultChainFactory.LogicModel.RULE_DEFAULT.getCode();
    }
}
