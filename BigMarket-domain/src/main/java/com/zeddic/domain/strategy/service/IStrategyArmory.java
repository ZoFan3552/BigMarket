package com.zeddic.domain.strategy.service;


/**
 * @author: zeddic
 * @description: 抽奖策略的装配库接口
 * @date: 2024/7/16 下午5:20
 */
public interface IStrategyArmory {
    boolean assembleLotteryStrategy(Long strategyId);

    Integer getRandomAwardId(Long strategyId);
}
