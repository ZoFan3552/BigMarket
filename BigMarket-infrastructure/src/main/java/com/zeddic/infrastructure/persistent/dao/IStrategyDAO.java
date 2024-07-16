package com.zeddic.infrastructure.persistent.dao;

import com.zeddic.infrastructure.persistent.po.StrategyPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: zeddic
 * @description: 抽奖策略的DAO
 * @date: 2024/7/16 下午3:28
 */
@Mapper
public interface IStrategyDAO {
    List<StrategyPO> queryStrategyList();
}
