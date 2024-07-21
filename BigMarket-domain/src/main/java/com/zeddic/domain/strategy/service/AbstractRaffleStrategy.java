package com.zeddic.domain.strategy.service;

import com.zeddic.domain.strategy.model.entity.RaffleAwardEntity;
import com.zeddic.domain.strategy.model.entity.RaffleFactorEntity;
import com.zeddic.domain.strategy.model.entity.RuleActionEntity;
import com.zeddic.domain.strategy.model.vo.RuleLogicCheckTypeVO;
import com.zeddic.domain.strategy.model.vo.StrategyAwardRuleModelVO;
import com.zeddic.domain.strategy.repository.IStrategyRepository;
import com.zeddic.domain.strategy.service.armory.IStrategyDispatch;
import com.zeddic.domain.strategy.service.rule.chain.ILogicChain;
import com.zeddic.domain.strategy.service.rule.chain.factory.DefaultChainFactory;
import com.zeddic.types.enums.ResponseCode;
import com.zeddic.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @author: zeddic
 * @description: 抽奖策略抽象类
 * @date: 2024/7/17 下午9:15
 */
@Slf4j
public abstract class AbstractRaffleStrategy implements IRaffleStrategy {

    // 策略仓储服务 -> domain层像一个大厨，仓储层提供米面粮油
    protected IStrategyRepository strategyRepository;
    // 策略调度服务 -> 只负责抽奖处理，通过新增接口的方式，隔离职责，不需要使用方关心或者调用抽奖的初始化
    protected IStrategyDispatch strategyDispatch;

    private final DefaultChainFactory chainFactory;

    public AbstractRaffleStrategy(IStrategyRepository strategyRepository, IStrategyDispatch strategyDispatch,DefaultChainFactory chainFactory) {
        this.chainFactory = chainFactory;
        this.strategyRepository = strategyRepository;
        this.strategyDispatch = strategyDispatch;
    }


    @Override
    public RaffleAwardEntity performRaffle(RaffleFactorEntity raffleFactorEntity) {
        // 1. 参数校验
        String userId = raffleFactorEntity.getUserId();
        Long strategyId = raffleFactorEntity.getStrategyId();
        if (null == strategyId || StringUtils.isBlank(userId)) {
            throw new AppException(ResponseCode.ILLEGAL_PARAMETER.getCode(), ResponseCode.ILLEGAL_PARAMETER.getInfo());
        }

       //2.开启抽奖责任链
        ILogicChain logicChain = chainFactory.openLogicChain(strategyId);
        Integer awardId = logicChain.performRaffle(userId, strategyId);
        //3.查询奖品规则
        StrategyAwardRuleModelVO strategyAwardRuleModelVO = strategyRepository.queryStrategyAwardRuleModelVO(strategyId , awardId);

        //4.抽奖中，规则过滤
        RuleActionEntity<RuleActionEntity.RaffleDuringEntity> ruleActionDuringEntity = this.doCheckRaffleDuringLogic(
                RaffleFactorEntity.builder().userId(userId).strategyId(strategyId).awardId(awardId).build(),
                strategyAwardRuleModelVO.raffleDuringRuleModelList());

        if (RuleLogicCheckTypeVO.TAKE_OVER.getCode().equals(ruleActionDuringEntity.getCode())){
            log.info("[临时日志] 抽奖中规则拦截，通过 “抽奖后规则 rule_luck_award” 走兜底奖励");
            return RaffleAwardEntity.builder()
                    .awardDesc("中奖规则拦截，通过“抽奖后规则——rule_luck_award”拿到兜底奖励")
                    .build();
        }

        return RaffleAwardEntity.builder()
                .awardId(awardId)
                .build();
    }

    protected abstract RuleActionEntity<RuleActionEntity.RaffleDuringEntity>
    doCheckRaffleDuringLogic(RaffleFactorEntity raffleFactorEntity , String[] ruleModels);
}
