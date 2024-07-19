package com.zeddic.domain.strategy.model.vo;

import com.zeddic.domain.strategy.service.rule.factory.DefaultLogicFactory;
import com.zeddic.types.common.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public String[] raffleDuringRuleModels(){
        String[] ruleModelValues = ruleModels.split(Constants.SPLIT);
        List<String> result = new ArrayList<>();
        for (String ruleModelValue : ruleModelValues) {
            if (DefaultLogicFactory.isDuring(ruleModelValue)){
                result.add(ruleModelValue);
            }
        }
        return result.toArray(new String[0]);
    }
}
