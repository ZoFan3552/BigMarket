package com.zeddic.infrastructure.persistent.dao;

import com.zeddic.infrastructure.persistent.po.RuleTreeNodeLinePO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: zeddic
 * @description:
 * @date: 2024/7/22 下午5:39
 */
@Mapper
public interface IRuleTreeNodeLineDAO {

    List<RuleTreeNodeLinePO> queryRuleTreeNodeLineListByTreeId(String treeId);
}
