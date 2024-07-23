package com.zeddic.domain.strategy.service;

import com.zeddic.domain.strategy.model.vo.StrategyAwardStockKeyVO;

/**
 * @author: zeddic
 * @description: 抽奖库存相关任务，获取库存消耗队列
 * @date: 2024/7/23 下午2:38
 */
public interface IRaffleStock {
    /**
     * 获取奖品库存消耗队列
     * @return 奖品库存key信息
     * @throws InterruptedException 异常
     */
    StrategyAwardStockKeyVO takeQueueValue() throws InterruptedException;

    /**
     * 更新奖品库存消耗记录
     * @param strategyId 策略ID
     * @param awardId 奖品ID
     */
    void updateStrategyAwardStock(Long strategyId , Integer awardId);
}
