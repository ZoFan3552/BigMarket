package com.zeddic.domain.strategy.service;

import com.zeddic.domain.strategy.model.entity.RaffleAwardEntity;
import com.zeddic.domain.strategy.model.entity.RaffleFactorEntity;

/**
 * @author: zeddic
 * @description: 抽奖策略接口
 * @date: 2024/7/17 下午9:09
 */
public interface IRaffleStrategy {

    RaffleAwardEntity performRaffle(RaffleFactorEntity raffleFactorEntity);
}
