package com.zeddic.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * @author: zeddic
 * @description: 抽奖策略规则持久化对象
 * @date: 2024/7/16 下午3:09
 */
@Data
public class StrategyRulePO {

    /**
     *自增ID
     */
    private Long id;
    /**
     * 策略ID
     */
    private Long strategyId;
    /**
     * 奖品ID
     */
    private Integer awardId;
    /**
     * 抽奖规则类型[1.策略规则 2.奖品规则]
     */
    private Integer ruleType;
    /**
     * 抽奖规则模型
     */
    private String ruleModel;
    /**
     * 抽奖规则值
     */
    private String ruleValue;
    /**
     * 抽奖规则描述
     */
    private String ruleDesc;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

}
