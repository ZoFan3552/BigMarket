package com.zeddic.infrastructure.persistent.po;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: zeddic
 * @description: 抽奖策略奖品明细配置的持久化对象
 * @date: 2024/7/16 下午3:03
 */
@Data
public class StrategyAwardPO {
    /**
     * 自增ID
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
     * 奖品标题
     */
    private String awardTitle;
    /**
     * 奖品副标题
     */
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
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 规则模型
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
