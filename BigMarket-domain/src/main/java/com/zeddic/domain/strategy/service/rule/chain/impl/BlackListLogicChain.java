package com.zeddic.domain.strategy.service.rule.chain.impl;

import com.zeddic.domain.strategy.repository.IStrategyRepository;
import com.zeddic.domain.strategy.service.rule.chain.AbstractLogicChain;
import com.zeddic.types.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author: zeddic
 * @description: 责任链——黑名单结点
 * @date: 2024/7/21 下午8:41
 */
@Slf4j
@Component("rule_blacklist")
public class BlackListLogicChain extends AbstractLogicChain {

    @Resource
    private IStrategyRepository strategyRepository;

    @Override
    public Integer performRaffle(String userId, Long strategyID) {
        log.info("抽奖责任链-黑名单开始 userId:{} strategyId:{} ruleModel:{}",
                userId, strategyID, ruleModel());
        String ruleValue = strategyRepository.queryStrategyRuleValue(strategyID, ruleModel());
        String [] splitRuleValues = ruleValue.split(Constants.COLON);
        Integer awardId = Integer.parseInt(splitRuleValues[0]);

        //过滤其他规则
        String[] userBlackIds = splitRuleValues[1].split(Constants.SPLIT);
        for (String userBlackId : userBlackIds) {
            if (userId.equals(userBlackId)) {
                log.info("抽奖责任链-黑名单接管 userId:{} strategyId:{} ruleModel:{} awardID{}",
                        userId, strategyID, ruleModel() , awardId);
                return awardId;
            }
        }
        log.info("抽奖责任链-黑名单放行 userId:{} strategyId:{} ruleModel:{}",
                userId, strategyID, ruleModel());
        return next().performRaffle(userId, strategyID);
    }

    @Override
    protected String ruleModel() {
        return "rule_blacklist";
    }
}
