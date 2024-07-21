package com.zeddic.domain.strategy.service.rule.chain;

/**
 * @author: zeddic
 * @description: 标记接口，用于规范
 * @date: 2024/7/21 下午9:21
 */
public interface ILogicArmory {

    ILogicChain appendNext(ILogicChain next);

    ILogicChain next();
}
