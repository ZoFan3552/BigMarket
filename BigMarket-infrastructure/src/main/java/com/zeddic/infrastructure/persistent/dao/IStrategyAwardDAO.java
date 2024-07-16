package com.zeddic.infrastructure.persistent.dao;

import com.zeddic.infrastructure.persistent.po.StrategyAwardPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: zeddic
 * @description: 抽奖策略奖品的DAO
 * @date: 2024/7/16 下午3:28
 */
@Mapper
public interface IStrategyAwardDAO {
    List<StrategyAwardPO> queryStrategyAwardList();
}
