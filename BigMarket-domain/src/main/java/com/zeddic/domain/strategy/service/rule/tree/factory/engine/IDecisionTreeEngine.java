package com.zeddic.domain.strategy.service.rule.tree.factory.engine;

import com.zeddic.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;

/**
 * @author: zeddic
 * @description: 规则树组合接口
 * @date: 2024/7/22 下午2:25
 */
public interface IDecisionTreeEngine {

    DefaultTreeFactory.StrategyAwardVO process(String userId , Long strategyId , Integer awardId);

}
