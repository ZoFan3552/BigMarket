package com.zeddic.domain.strategy.service.rule.chain;

/**
 * @author: zeddic
 * @description:
 * @date: 2024/7/21 下午8:40
 */
public abstract class AbstractLogicChain implements ILogicChain{

    private ILogicChain next;

    @Override
    public ILogicChain next() {
        return next;
    }

    @Override
    public ILogicChain appendNext(ILogicChain next) {
        this.next = next;
        return next;
    }

    //每个类自己去实现，因为在责任链上的每个结点都已明确自己的规则模型
    protected abstract String ruleModel();
}
