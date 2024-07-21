package com.zeddic.domain.strategy.service.raffle;

import com.zeddic.domain.strategy.model.entity.RaffleFactorEntity;
import com.zeddic.domain.strategy.model.entity.RuleActionEntity;
import com.zeddic.domain.strategy.model.entity.RuleMatterEntity;
import com.zeddic.domain.strategy.model.vo.RuleLogicCheckTypeVO;
import com.zeddic.domain.strategy.repository.IStrategyRepository;
import com.zeddic.domain.strategy.service.AbstractRaffleStrategy;
import com.zeddic.domain.strategy.service.armory.IStrategyDispatch;
import com.zeddic.domain.strategy.service.rule.chain.factory.DefaultChainFactory;
import com.zeddic.domain.strategy.service.rule.filter.ILogicFilter;
import com.zeddic.domain.strategy.service.rule.filter.factory.DefaultLogicFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: zeddic
 * @description:
 * @date: 2024/7/18 下午5:08
 */
@Slf4j
@Service
public class DefaultRaffleStrategy extends AbstractRaffleStrategy {
    @Resource
    private DefaultLogicFactory logicFactory;

    public DefaultRaffleStrategy(IStrategyRepository repository, IStrategyDispatch strategyDispatch, DefaultChainFactory defaultChainFactory) {
        super(repository, strategyDispatch, defaultChainFactory);
    }

    @Override
    protected RuleActionEntity<RuleActionEntity.RaffleDuringEntity> doCheckRaffleDuringLogic(RaffleFactorEntity raffleFactorEntity, String[] ruleModels) {
        if (ruleModels == null || ruleModels.length == 0) return RuleActionEntity.<RuleActionEntity.RaffleDuringEntity>builder()
                .code(RuleLogicCheckTypeVO.ALLOW.getCode())
                .info(RuleLogicCheckTypeVO.TAKE_OVER.getInfo())
                .build();

        Map<String , ILogicFilter<RuleActionEntity.RaffleDuringEntity>> logicFilterGroup = logicFactory.openLogicFilter();

        RuleActionEntity<RuleActionEntity.RaffleDuringEntity> ruleActionEntity = null;

        for (String ruleModel : ruleModels) {
            ILogicFilter<RuleActionEntity.RaffleDuringEntity> logicFilter = logicFilterGroup.get(ruleModel);
            RuleMatterEntity ruleMatterEntity = new RuleMatterEntity();
            ruleMatterEntity.setUserId(raffleFactorEntity.getUserId());
            ruleMatterEntity.setStrategyId(raffleFactorEntity.getStrategyId());
            ruleMatterEntity.setAwardId(raffleFactorEntity.getAwardId());
            ruleMatterEntity.setRuleModel(ruleModel);
            ruleActionEntity = logicFilter.filter(ruleMatterEntity);
            //非放行结果的过滤
            log.info("抽奖中规则过滤 userId: {} ruleModel: {} code: {} info: {}", raffleFactorEntity.getUserId(), ruleModel, ruleActionEntity.getCode(), ruleActionEntity.getInfo());
            if (!RuleLogicCheckTypeVO.ALLOW.getCode().equals(ruleActionEntity.getCode())) return ruleActionEntity;
        }
        return ruleActionEntity;
    }


}
