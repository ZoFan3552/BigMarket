package com.zeddic.domain.strategy.repository;

import com.zeddic.domain.strategy.model.entity.StrategyAwardEntity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zeddic
 * @description: 抽奖策略的仓储服务
 * @date: 2024/7/16 下午5:23
 */
public interface IStrategyRepository {
    List<StrategyAwardEntity> queryStrategyAwardList(Long strategyId);

    void storeStrategyAwardSearchRateTable(Long strategyId, Integer rateRange, Map<Integer, Integer> strategyAwardSearchRateTable);

    Integer getStrategyAwardAssemble(Long strategyId, Integer rateKey);

    int getRateRange(Long strategyId);

}
