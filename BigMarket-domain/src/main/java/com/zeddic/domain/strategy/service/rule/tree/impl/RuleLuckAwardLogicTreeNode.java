package com.zeddic.domain.strategy.service.rule.tree.impl;

import com.zeddic.domain.strategy.model.vo.RuleLogicCheckTypeVO;
import com.zeddic.domain.strategy.service.rule.tree.ILogicTreeNode;
import com.zeddic.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;
import com.zeddic.types.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author: zeddic
 * @description: 兜底奖励节点
 * @date: 2024/7/22 下午2:22
 */
@Slf4j
@Component("rule_luck_award")
public class RuleLuckAwardLogicTreeNode implements ILogicTreeNode {
    @Override
    public DefaultTreeFactory.TreeActionEntity logic(String userId, Long strategyId, Integer awardId , String ruleValue) {
        log.info("规则过滤-兜底奖品 userId:{} strategyId:{} awardId:{} ruleValue:{}", userId, strategyId, awardId , ruleValue);
        String[] splitRuleValues = ruleValue.split(Constants.COLON);
        if (splitRuleValues.length == 0){
            log.error("规则过滤告警-兜底奖品未配置 userId:{} strategyId:{} awardId:{}", userId, strategyId, awardId);
        }

        //兜底奖励配置
        Integer luckAwardId = Integer.valueOf(splitRuleValues[0]);
        String awardRuleValue = splitRuleValues.length > 1 ? splitRuleValues[1] : "";
        log.info("规则过滤-兜底奖品 userId:{} strategyId:{} awardId:{} awardRuleValue:{}", userId, strategyId, awardId , awardRuleValue);
        return DefaultTreeFactory.TreeActionEntity.builder()
                .ruleLogicCheckType(RuleLogicCheckTypeVO.TAKE_OVER)
                .strategyAwardVO(DefaultTreeFactory.StrategyAwardVO.builder()
                        .awardId(luckAwardId)
                        .awardRuleValue(awardRuleValue)
                        .build())
                .build();
    }
}
