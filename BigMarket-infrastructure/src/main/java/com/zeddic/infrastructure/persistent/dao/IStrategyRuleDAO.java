package com.zeddic.infrastructure.persistent.dao;

import com.zeddic.infrastructure.persistent.po.StrategyRulePO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: zeddic
 * @description: 抽奖策略规则的DAO
 * @date: 2024/7/16 下午3:29
 */
@Mapper
public interface IStrategyRuleDAO {
    List<StrategyRulePO> queryStrategyRuleList();
}
