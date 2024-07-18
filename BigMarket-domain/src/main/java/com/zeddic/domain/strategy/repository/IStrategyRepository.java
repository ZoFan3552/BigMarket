package com.zeddic.domain.strategy.repository;

import com.zeddic.domain.strategy.model.entity.StrategyAwardEntity;
import com.zeddic.domain.strategy.model.entity.StrategyEntity;
import com.zeddic.domain.strategy.model.entity.StrategyRuleEntity;

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

    void storeStrategyAwardSearchRateTable(String key, Integer rateRange, Map<Integer, Integer> strategyAwardSearchRateTable);

    Integer getStrategyAwardAssemble(String key, Integer rateKey);

    int getRateRange(String key);

    int getRateRange(Long strategyId);


    StrategyEntity queryStrategyEntityByStrategyId(Long strategyId);

    StrategyRuleEntity queryStrategyRule(Long strategyId, String ruleModel);

    String queryStrategyRuleValue(Long strategyId, Integer awardId, String ruleModel);
}
