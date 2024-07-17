package com.zeddic.infrastructure.persistent.repository;

import com.zeddic.domain.strategy.model.entity.StrategyAwardEntity;
import com.zeddic.domain.strategy.repository.IStrategyRepository;
import com.zeddic.infrastructure.persistent.dao.IStrategyAwardDAO;
import com.zeddic.infrastructure.persistent.po.StrategyAwardPO;
import com.zeddic.infrastructure.persistent.redis.IRedisService;
import com.zeddic.types.common.Constants;
import org.redisson.api.RMap;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author: zeddic
 * @description: 抽奖策略仓储实现
 * @date: 2024/7/16 下午5:28
 */
@Repository
public class StrategyRepository implements IStrategyRepository {

    @Resource
    private IStrategyAwardDAO strategyAwardDAO;

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
    public void storeStrategyAwardSearchRateTable(Long strategyId, Integer rateRange, Map<Integer, Integer> strategyAwardSearchRateTable) {
        // 1. 存储抽奖策略范围值，如10000，用于生成1000以内的随机数
        redisService.setValue(Constants.RedisKey.STRATEGY_RATE_RANGE_KEY + strategyId, rateRange);
        // 2. 存储概率查找表
        Map<Integer, Integer> cacheRateTable = redisService.getMap(Constants.RedisKey.STRATEGY_RATE_TABLE_KEY + strategyId);
        cacheRateTable.putAll(strategyAwardSearchRateTable);
    }

    @Override
    public Integer getStrategyAwardAssemble(Long strategyId, Integer rateKey) {
        return redisService.getFromMap(Constants.RedisKey.STRATEGY_RATE_TABLE_KEY + strategyId, rateKey);
    }

    @Override
    public int getRateRange(Long strategyId) {
        return redisService.getValue(Constants.RedisKey.STRATEGY_RATE_RANGE_KEY + strategyId);
    }

}
