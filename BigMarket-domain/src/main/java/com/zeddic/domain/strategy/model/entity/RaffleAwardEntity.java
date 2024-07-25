package com.zeddic.domain.strategy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: zeddic
 * @description: 抽奖结果奖品实体
 * @date: 2024/7/17 下午9:11
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RaffleAwardEntity {
    /** 奖品ID*/
    private Integer awardId;
    /** 奖品配置信息*/
    private String awardConfig;
    /**奖品排序*/
    private Integer sort;
}
