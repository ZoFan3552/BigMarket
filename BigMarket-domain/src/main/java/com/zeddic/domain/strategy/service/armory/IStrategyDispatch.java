package com.zeddic.domain.strategy.service.armory;

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

    /**
     * 获取抽奖策略装配的随机结果
     *
     * @param strategyId 权重ID
     * @return 抽奖结果
     */

    Integer getRandomAwardId(Long strategyId,String ruleWeightValue);

    /**
     * 获取抽奖策略装配的随机结果
     *
     * @param key = strategyId + _ + ruleWeightValue；
     * @return 抽奖结果
     */
    Integer getRandomAwardId(String key);

    Boolean reduceAwardStock(Long strategyId,Integer awardId);

}
