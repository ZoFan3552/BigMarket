package com.zeddic.infrastructure.persistent.dao;

import com.zeddic.infrastructure.persistent.po.RuleTreePO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: zeddic
 * @description: 规则树表DAO
 * @date: 2024/7/22 下午5:38
 */
@Mapper
public interface IRuleTreeDAO {

    RuleTreePO queryRuleTreeByTreeId(String treeId);
}
