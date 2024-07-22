package com.zeddic.domain.strategy.service.rule.tree;

import com.zeddic.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;

/**
 * @author: zeddic
 * @description: 规则树接口
 * @date: 2024/7/22 下午2:04
 */
public interface ILogicTreeNode {

    DefaultTreeFactory.TreeActionEntity logic(String userId , Long strategyId , Integer awardId);
}
