package com.zeddic.domain.strategy.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: zeddic
 * @description:
 * @date: 2024/7/23 下午2:13
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StrategyAwardStockKeyVO {
    //策略ID
    private Long strategyId;
    //奖品ID
    private Integer awardId;
}
