package com.zeddic.domain.strategy.service.rule.filter.impl;

import com.zeddic.domain.strategy.model.entity.RuleActionEntity;
import com.zeddic.domain.strategy.model.entity.RuleMatterEntity;
import com.zeddic.domain.strategy.model.vo.RuleLogicCheckTypeVO;
import com.zeddic.domain.strategy.repository.IStrategyRepository;
import com.zeddic.domain.strategy.service.annotation.LogicStrategy;
import com.zeddic.domain.strategy.service.rule.filter.ILogicFilter;
import com.zeddic.domain.strategy.service.rule.filter.factory.DefaultLogicFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author: zeddic
 * @description: 抽奖中置规则过滤，抽奖 n 次后解锁奖品
 * @date: 2024/7/19 下午4:51
 */
@Slf4j
@Component
@LogicStrategy(logicModel = DefaultLogicFactory.LogicModel.RULE_LOCK)
public class RuleLockLogicFilter implements ILogicFilter<RuleActionEntity.RaffleDuringEntity> {

    @Resource
    private IStrategyRepository strategyRepository;

    private Long userRaffleCount = 0L;

    @Override
    public RuleActionEntity<RuleActionEntity.RaffleDuringEntity> filter(RuleMatterEntity ruleMatterEntity) {
        log.info("规则过滤-次数解锁 userId:{} strategyId:{} ruleModel:{}",
                ruleMatterEntity.getUserId(), ruleMatterEntity.getStrategyId(), ruleMatterEntity.getRuleModel());
        String ruleValue = strategyRepository.queryStrategyRuleValue(ruleMatterEntity.getStrategyId()
                , ruleMatterEntity.getAwardId(), ruleMatterEntity.getRuleModel());
        Long raffleCount = Long.parseLong(ruleValue);
        if (userRaffleCount >= raffleCount) {//次数达到要求，直接放行
            return RuleActionEntity.<RuleActionEntity.RaffleDuringEntity>builder()
                    .code(RuleLogicCheckTypeVO.ALLOW.getCode())
                    .code(RuleLogicCheckTypeVO.ALLOW.getInfo())
                    .build();
        }
        //没达到，拦截，让规则引擎接管
        return RuleActionEntity.<RuleActionEntity.RaffleDuringEntity>builder()
                .code(RuleLogicCheckTypeVO.TAKE_OVER.getCode())
                .code(RuleLogicCheckTypeVO.TAKE_OVER.getInfo())
                .build();
    }
}
