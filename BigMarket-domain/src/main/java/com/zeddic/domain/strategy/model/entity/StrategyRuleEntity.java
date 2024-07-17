package com.zeddic.domain.strategy.model.entity;

import com.zeddic.types.common.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zeddic
 * @description:
 * @date: 2024/7/17 下午4:19
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StrategyRuleEntity {
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

    public Map<String, List<Integer>> getRuleWeightValues() {
        if (!"rule_weight".equals(ruleModel)) return null;
        String[] ruleValueGroups = ruleValue.split(Constants.SPACE);
        Map<String, List<Integer>> resultMap = new HashMap<>();
        for (String ruleValueGroup : ruleValueGroups) {
            // 检查输入是否为空
            if (ruleValueGroup == null || ruleValueGroup.isEmpty()) {
                return resultMap;
            }
            // 分割字符串以获取键和值
            String[] parts = ruleValueGroup.split(Constants.COLON);
            if (parts.length != 2) {
                throw new IllegalArgumentException("rule_weight rule_rule invalid input format" + ruleValueGroup);
            }
            // 解析值
            String[] valueStrings = parts[1].split(Constants.SPLIT);
            List<Integer> values = new ArrayList<>();
            for (String valueString : valueStrings) {
                values.add(Integer.parseInt(valueString));
            }
            // 将键和值放入Map中
            resultMap.put(ruleValueGroup, values);
        }

        return resultMap;
    }

}
