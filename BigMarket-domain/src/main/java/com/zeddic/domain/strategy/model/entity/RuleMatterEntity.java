package com.zeddic.domain.strategy.model.entity;

import lombok.Data;

/**
 * @author: zeddic
 * @description: 规则物料实体对象，用于作为参数传入规则过滤接口
 * @date: 2024/7/17 下午9:22
 */
@Data
public class RuleMatterEntity {
    /**用户ID */
    private String userId;
    /** 策略ID*/
    private Long strategyId;
    /** 奖品ID*/
    private Integer awardId;
    /** 规则模型*/
    private String ruleModel;
}
