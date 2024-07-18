package com.zeddic.domain.strategy.service.rule;

import com.zeddic.domain.strategy.model.entity.RuleActionEntity;
import com.zeddic.domain.strategy.model.entity.RuleMatterEntity;

/**
 * @author: zeddic
 * @description: 抽奖过滤规则接口
 * @date: 2024/7/17 下午9:17
 */
public interface ILogicFilter<T extends RuleActionEntity.RaffleEntity> {

    RuleActionEntity<T> filter(RuleMatterEntity ruleMatterEntity);
}
