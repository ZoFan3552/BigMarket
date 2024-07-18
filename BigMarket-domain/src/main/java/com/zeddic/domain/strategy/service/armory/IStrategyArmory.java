package com.zeddic.domain.strategy.service.armory;


/**
 * @author: zeddic
 * @description: 抽奖策略的装配库接口
 * @date: 2024/7/16 下午5:20
 */
public interface IStrategyArmory {
    /**
     * 根据策略ID去进行装配
     * @param strategyId 策略ID
     * @return 抽奖结果
     */
    boolean assembleLotteryStrategy(Long strategyId);


}
