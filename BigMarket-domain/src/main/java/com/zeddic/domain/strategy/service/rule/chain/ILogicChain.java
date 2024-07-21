package com.zeddic.domain.strategy.service.rule.chain;

/**
 * @author: zeddic
 * @description: 责任链接口
 * @date: 2024/7/21 下午8:38
 */
public interface ILogicChain extends ILogicArmory{

    /**
     *
     * @param userId 用户ID
     * @param strategyID 策略ID
     * @return 奖品 ID
     */
    Integer performRaffle(String userId , Long strategyID);

}
