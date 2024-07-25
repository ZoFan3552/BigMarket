package com.zeddic.domain.strategy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author: zeddic
 * @description: domain层的抽奖策略奖品的实体对象（与基础层的PO对象的部分字段一一对应）
 * @date: 2024/7/16 下午5:30
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StrategyAwardEntity {
    /**
     * 策略ID
     */
    private Long strategyId;
    /**
     * 奖品ID
     */
    private Integer awardId;
    /**奖品标题*/
    private String awardTitle;
    /**奖品副标题*/
    private String awardSubtitle;
    /**
     * 奖品总数量
     */
    private Integer awardCount;
    /**
     * 奖品剩余数量
     */
    private Integer awardCountSurplus;
    /**
     * 奖品中奖概率
     */
    private BigDecimal awardRate;
    /** 奖品排序*/
    private Integer sort;

}
