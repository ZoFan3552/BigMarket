package com.zeddic.domain.strategy.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author: zeddic
 * @description: 抽奖策略规则值对象；值对象没有唯一ID，仅从数据库查询对象
 * @date: 2024/7/19 下午5:08
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StrategyAwardRuleModelVO {

    private String ruleModels;

}
