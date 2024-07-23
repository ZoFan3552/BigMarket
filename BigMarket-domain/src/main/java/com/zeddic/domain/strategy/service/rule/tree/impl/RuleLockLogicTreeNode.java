package com.zeddic.domain.strategy.service.rule.tree.impl;

import com.zeddic.domain.strategy.model.vo.RuleLogicCheckTypeVO;
import com.zeddic.domain.strategy.service.rule.tree.ILogicTreeNode;
import com.zeddic.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author: zeddic
 * @description: 次数锁节点
 * @date: 2024/7/22 下午2:21
 */
@Slf4j
@Component("rule_lock")
public class RuleLockLogicTreeNode implements ILogicTreeNode {

    public Long userRaffleCount = 10L;
    @Override
    public DefaultTreeFactory.TreeActionEntity logic(String userId, Long strategyId, Integer awardId , String ruleValue) {
        log.info("规则过滤-次数锁 userId:{} strategyId:{} awardId:{}", userId, strategyId, awardId);

        long raffleCount = 0L;
        try {
            raffleCount = Long.parseLong(ruleValue);
        }catch (Exception e){
            throw  new RuntimeException("规则过滤-次数锁异常 ruleValue="+ruleValue + " 配置不正确");
        }

        if (userRaffleCount >= raffleCount){
            log.info("规则过滤-次数锁-次数达标，放行 userId:{} strategyId:{} awardId:{}", userId, strategyId, awardId);
            return DefaultTreeFactory.TreeActionEntity.builder()
                    .ruleLogicCheckType(RuleLogicCheckTypeVO.ALLOW)
                    .build();
        }

        log.info("规则过滤-次数锁-次数不达标，拦截 userId:{} strategyId:{} awardId:{}", userId, strategyId, awardId);
        return DefaultTreeFactory.TreeActionEntity.builder()
                .ruleLogicCheckType(RuleLogicCheckTypeVO.TAKE_OVER)
                .build();
    }
}
