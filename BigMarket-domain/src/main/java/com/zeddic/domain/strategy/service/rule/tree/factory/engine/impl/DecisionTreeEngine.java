package com.zeddic.domain.strategy.service.rule.tree.factory.engine.impl;

import com.zeddic.domain.strategy.model.vo.RuleLogicCheckTypeVO;
import com.zeddic.domain.strategy.model.vo.RuleTreeNodeLineVO;
import com.zeddic.domain.strategy.model.vo.RuleTreeNodeVO;
import com.zeddic.domain.strategy.model.vo.RuleTreeVO;
import com.zeddic.domain.strategy.service.rule.tree.ILogicTreeNode;
import com.zeddic.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;
import com.zeddic.domain.strategy.service.rule.tree.factory.engine.IDecisionTreeEngine;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * @author: zeddic
 * @description: 决策树引擎
 * @date: 2024/7/22 下午2:26
 */
@Slf4j
public class DecisionTreeEngine implements IDecisionTreeEngine {

    private final Map<String  , ILogicTreeNode> logicTreeGroup;

    private final RuleTreeVO ruleTreeVO;

    public DecisionTreeEngine(Map<String, ILogicTreeNode> logicTreeGroup, RuleTreeVO ruleTreeVO) {
        this.logicTreeGroup = logicTreeGroup;
        this.ruleTreeVO = ruleTreeVO;
    }

    @Override
    public DefaultTreeFactory.StrategyAwardVO process(String userId, Long strategyId, Integer awardId) {
        DefaultTreeFactory.StrategyAwardVO strategyAwardVO = null;
        //获取基础信息
        String nextNode = ruleTreeVO.getTreeRootRuleNode();
        Map<String, RuleTreeNodeVO> treeNodeMap = ruleTreeVO.getTreeNodeMap();
        RuleTreeNodeVO ruleTreeNode = treeNodeMap.get(nextNode);
        while (null != nextNode){
            ILogicTreeNode logicTreeNode = logicTreeGroup.get(ruleTreeNode.getRuleKey());

            DefaultTreeFactory.TreeActionEntity logicEntity = logicTreeNode.logic(userId, strategyId, awardId);
            RuleLogicCheckTypeVO ruleLogicCheckType = logicEntity.getRuleLogicCheckType();
            strategyAwardVO = logicEntity.getStrategyAwardVO();
            log.info("决策树引擎【{}】treeId:{} node:{} code:{}", ruleTreeVO.getTreeName(), ruleTreeVO.getTreeId(), nextNode, ruleLogicCheckType.getCode());
            nextNode = nextNode(ruleLogicCheckType.getCode() , ruleTreeNode.getTreeNodeLineVOList());
            ruleTreeNode = treeNodeMap.get(nextNode);
        }
        //返回最终结果
        return strategyAwardVO;
    }

    private String nextNode(String matterValue , List<RuleTreeNodeLineVO> ruleTreeNodeLineVOList){
        if (null == ruleTreeNodeLineVOList || ruleTreeNodeLineVOList.isEmpty()) return null;
        for (RuleTreeNodeLineVO nodeLine : ruleTreeNodeLineVOList) {
            if (decisionLogic(matterValue , nodeLine)){
                return nodeLine.getRuleNodeTo();
            }
        }
        throw new RuntimeException("决策树引擎配置配置错误，未找到可执行节点");
    }

    public boolean decisionLogic(String matterValue, RuleTreeNodeLineVO nodeLine){
        switch (nodeLine.getRuleLimitType()){
            case EQUAL:
                return matterValue.equals(nodeLine.getRuleLimitValue().getCode());
            //以下规则暂时不用实现
            case GT:
            case LT:
            case GE:
            case LE:
            default:
                return false;
        }
    }
}
