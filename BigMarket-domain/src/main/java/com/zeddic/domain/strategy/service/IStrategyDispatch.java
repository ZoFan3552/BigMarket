package com.zeddic.domain.strategy.service;

/**
 * @author: zeddic
 * @description: 策略抽奖的调度
 * @date: 2024/7/17 下午3:25
 */
public interface IStrategyDispatch {
    /**
     * 根据策略ID，在概率范围内使用一个随机数进行抽奖
     * @param strategyId 策略ID
     * @return 奖品ID
     */
    Integer getRandomAwardId(Long strategyId);

    Integer getRandomAwardId(Long strategyId,String ruleWeightValue);
}
