package com.zeddic.domain.strategy.service;

import com.zeddic.domain.strategy.model.entity.StrategyAwardEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: zeddic
 * @description:
 * @date: 2024/7/24 下午8:33
 */
public interface IRaffleAward {
    List<StrategyAwardEntity> queryRaffleStrategyAwardList(Long strategyId);
}
