package com.zeddic.domain.strategy.model.entity;

import com.zeddic.types.common.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * @author: zeddic
 * @description:
 * @date: 2024/7/17 下午3:36
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StrategyEntity {
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
     * 根据规则值分割 rule1,rule2,rule3...
     * @return rule值数组
     */
    public String[] ruleModels(){
        if (StringUtils.isBlank(ruleModels)) return null;
        return ruleModels.split(Constants.SPLIT);
    }

    /**
     * 返回当前实体对象中的rule_weight
     * @return rule_weight
     */
    public String getRuleWeight(){
        if (ruleModels == null || ruleModels.isEmpty()) return null;
        String[] ruleModelStrs = this.ruleModels();
        for (String ruleModelStr : ruleModelStrs) {
            if ("rule_weight".equals(ruleModelStr)) return ruleModelStr;
        }
        return null;
    }

}