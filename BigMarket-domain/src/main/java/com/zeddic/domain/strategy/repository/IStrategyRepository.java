package com.zeddic.domain.strategy.repository;

import com.zeddic.domain.strategy.model.entity.StrategyAwardEntity;
import com.zeddic.domain.strategy.model.entity.StrategyEntity;
import com.zeddic.domain.strategy.model.entity.StrategyRuleEntity;
import com.zeddic.domain.strategy.model.vo.RuleTreeVO;
import com.zeddic.domain.strategy.model.vo.StrategyAwardRuleModelVO;
import com.zeddic.domain.strategy.model.vo.StrategyAwardStockKeyVO;

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

    String queryStrategyRuleValue(Long strategyId, String ruleModel);

    String queryStrategyRuleValue(Long strategyId, Integer awardId, String ruleModel);

    StrategyAwardRuleModelVO queryStrategyAwardRuleModelVO(Long strategyId, Integer awardId);

    RuleTreeVO queryRuleTreeVOByTreeId(String treeId);

    /**
     * 缓存奖品库存
     * @param cacheKey key
     * @param awardCount 库存值
     */
    void cacheStrategyAwardCount(String cacheKey, Integer awardCount);

    Boolean subtractionAwardStock(String cacheKey);

    void awardStockConsumeSendQueue(StrategyAwardStockKeyVO strategyAwardStockKeyVO);

    StrategyAwardStockKeyVO takeQueueValue();

    void updateStrategyAwardStock(Long strategyId, Integer awardId);

    /**
     * 根据策略ID + 奖品ID的唯一值组合查询奖品信息
     * @param strategyId 策略ID
     * @param awardId 奖品ID
     * @return 奖品实体对象
     */
    StrategyAwardEntity queryStrategyAwardEntity(Long strategyId, Integer awardId);
}
