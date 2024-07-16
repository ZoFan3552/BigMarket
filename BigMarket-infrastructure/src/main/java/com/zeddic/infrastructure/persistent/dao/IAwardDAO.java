package com.zeddic.infrastructure.persistent.dao;

import com.zeddic.infrastructure.persistent.po.AwardPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: zeddic
 * @description: 奖品表的DAO
 * @date: 2024/7/16 下午3:27
 */
@Mapper
public interface IAwardDAO {
    List<AwardPO> queryAwardList();
}
