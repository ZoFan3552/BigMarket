package com.zeddic.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * @author: zeddic
 * @description: 抽奖策略的持久化对象
 * @date: 2024/7/16 下午2:58
 */
@Data
public class StrategyPO {
    /**
     * 自增ID
     */
    private Long id;
    /**
     * 抽奖的策略ID
     */
    private String strategyId;
    /**
     * 抽奖策略描述
     */
    private String strategyDesc;
    /**
     * 策略模型
     */
    private String ruleModels;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}
