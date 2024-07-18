package com.zeddic.infrastructure.persistent.repository;

import com.zeddic.domain.strategy.model.entity.StrategyAwardEntity;
import com.zeddic.domain.strategy.model.entity.StrategyEntity;
import com.zeddic.domain.strategy.model.entity.StrategyRuleEntity;
import com.zeddic.domain.strategy.repository.IStrategyRepository;
import com.zeddic.infrastructure.persistent.dao.IStrategyAwardDAO;
import com.zeddic.infrastructure.persistent.dao.IStrategyDAO;
import com.zeddic.infrastructure.persistent.dao.IStrategyRuleDAO;
import com.zeddic.infrastructure.persistent.po.StrategyAwardPO;
import com.zeddic.infrastructure.persistent.po.StrategyPO;
import com.zeddic.infrastructure.persistent.po.StrategyRulePO;
import com.zeddic.infrastructure.persistent.redis.IRedisService;
import com.zeddic.types.common.Constants;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author: zeddic
 * @description: 抽奖策略仓储实现
 * @date: 2024/7/16 下午5:28
 */
@Repository
public class StrategyRepository implements IStrategyRepository {
    @Resource
    private IStrategyDAO strategyDAO;

    @Resource
    private IStrategyAwardDAO strategyAwardDAO;

    @Resource
    private IStrategyRuleDAO strategyRuleDAO;

    @Resource
    private IRedisService redisService;

    @Override
    public List<StrategyAwardEntity> queryStrategyAwardList(Long strategyId) {
        if(strategyId != null){
            //先从 Redis 缓存中获取
            String cacheKey = Constants.RedisKey.STRATEGY_AWARD_KEY + strategyId;
            List<StrategyAwardEntity> strategyAwardEntities = redisService.getValue(cacheKey);
            if (strategyAwardEntities != null && !strategyAwardEntities.isEmpty()) {
                return strategyAwardEntities;
            }
            //如果缓存中没有，则从数据库中获取
            List<StrategyAwardPO> strategyAwardPOS = strategyAwardDAO.queryStrategyAwardListByStrategyId(strategyId);
            List<StrategyAwardEntity> newStrategyAwardEntities = new ArrayList<>();
            for (StrategyAwardPO strategyAwardPO : strategyAwardPOS) {
                StrategyAwardEntity strategyAwardEntity = StrategyAwardEntity.builder()
                                .strategyId(strategyAwardPO.getStrategyId())
                                .awardId(strategyAwardPO.getAwardId())
                                .awardCount(strategyAwardPO.getAwardCount())
                                .awardCountSurplus(strategyAwardPO.getAwardCountSurplus())
                                .awardRate(strategyAwardPO.getAwardRate())
                                .build();
                newStrategyAwardEntities.add(strategyAwardEntity);
            }
            //再次到Redis中设置value,防止重复操作
            redisService.setValue(cacheKey, newStrategyAwardEntities);
            return newStrategyAwardEntities;
        }
        return Collections.emptyList();
    }

    //装配抽奖概率查找表
    @Override
    public void storeStrategyAwardSearchRateTable(String key, Integer rateRange, Map<Integer, Integer> strategyAwardSearchRateTable) {
        // 1. 存储抽奖策略范围值，如10000，用于生成1000以内的随机数
        redisService.setValue(Constants.RedisKey.STRATEGY_RATE_RANGE_KEY + key, rateRange);
        // 2. 存储概率查找表
        Map<Integer, Integer> cacheRateTable = redisService.getMap(Constants.RedisKey.STRATEGY_RATE_TABLE_KEY + key);
        cacheRateTable.putAll(strategyAwardSearchRateTable);
    }

    @Override
    public Integer getStrategyAwardAssemble(String key, Integer rateKey) {
        return redisService.getFromMap(Constants.RedisKey.STRATEGY_RATE_TABLE_KEY + key, rateKey);
    }

    @Override
    public int getRateRange(Long strategyId) {
        return getRateRange(String.valueOf(strategyId));
    }

    @Override
    public int getRateRange(String key) {
        return redisService.getValue(Constants.RedisKey.STRATEGY_RATE_RANGE_KEY + key);
    }

    @Override
    public StrategyEntity queryStrategyEntityByStrategyId(Long strategyId) {
        if (strategyId != null){
            StrategyEntity strategyEntity = redisService.getValue(Constants.RedisKey.STRATEGY_KEY + strategyId);
            if (strategyEntity != null){
                return strategyEntity;
            }
            StrategyPO strategyPO = strategyDAO.queryStrategyByStrategyId(strategyId);
            strategyEntity = StrategyEntity.builder()
                    .strategyId(strategyPO.getStrategyId())
                    .strategyDesc(strategyPO.getStrategyDesc())
                    .ruleModels(strategyPO.getRuleModels())
                    .build();
            redisService.setValue(Constants.RedisKey.STRATEGY_KEY + strategyId, strategyEntity);
            return strategyEntity;
        }
        return null;
    }

    @Override
    public StrategyRuleEntity queryStrategyRule(Long strategyId, String ruleModel) {
        StrategyRulePO strategyRuleReq = new StrategyRulePO();
        strategyRuleReq.setStrategyId(strategyId);
        strategyRuleReq.setRuleModel(ruleModel);
        StrategyRulePO strategyRuleRes = strategyRuleDAO.queryStrategyRule(strategyRuleReq);
        return StrategyRuleEntity.builder()
                .strategyId(strategyRuleRes.getStrategyId())
                .awardId(strategyRuleRes.getAwardId())
                .ruleType(strategyRuleRes.getRuleType())
                .ruleModel(strategyRuleRes.getRuleModel())
                .ruleValue(strategyRuleRes.getRuleValue())
                .ruleDesc(strategyRuleRes.getRuleDesc())
                .build();

    }

    @Override
    public String queryStrategyRuleValue(Long strategyId, Integer awardId, String ruleModel) {
        StrategyRulePO strategyRule = new StrategyRulePO();
        strategyRule.setStrategyId(strategyId);
        strategyRule.setAwardId(awardId);
        strategyRule.setRuleModel(ruleModel);
        return strategyRuleDAO.queryStrategyRuleValue(strategyRule);
    }

}
