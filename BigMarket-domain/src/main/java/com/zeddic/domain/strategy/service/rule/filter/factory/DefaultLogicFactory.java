package com.zeddic.domain.strategy.service.rule.filter.factory;

import com.zeddic.domain.strategy.model.entity.RuleActionEntity;
import com.zeddic.domain.strategy.service.annotation.LogicStrategy;
import com.zeddic.domain.strategy.service.rule.filter.ILogicFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: zeddic
 * @description:
 * @date: 2024/7/17 下午9:46
 */
@Component
public class DefaultLogicFactory {

    public Map<String , ILogicFilter<?>> logicFilterMap = new ConcurrentHashMap<>();

    public DefaultLogicFactory(List<ILogicFilter<?>> logicFilters) {
        for (ILogicFilter<?> logicFilter : logicFilters) {
            LogicStrategy strategy = AnnotationUtils.findAnnotation(logicFilter.getClass(), LogicStrategy.class);
            if (strategy != null) {
                logicFilterMap.put(strategy.logicModel().getCode() , logicFilter);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public <T extends RuleActionEntity.RaffleEntity> Map<String, ILogicFilter<T>> openLogicFilter() {
        return (Map<String, ILogicFilter<T>>) (Map<?, ?>) logicFilterMap;
    }

    @Getter
    @AllArgsConstructor
    public enum LogicModel {

        RULE_WIGHT("rule_weight","【抽奖前规则】根据抽奖权重返回可抽奖范围KEY" , "before"),
        RULE_BLACKLIST("rule_blacklist","【抽奖前规则】黑名单规则过滤，命中黑名单则直接返回","before"),
        RULE_LOCK("rule_lock","【抽奖中规则】抽奖 n 次后对应奖品可解锁" , "during"),
        RULE_LUCK_AWARD("rule_luck_award","【抽奖后规则】幸运奖兜底：1.不满足解锁条件 2.奖品库存不足" , "after"),
        ;

        private final String code;
        private final String info;
        private final String type;

        public static boolean isDuring(String code){
            return "during".equals(LogicModel.valueOf(code.toUpperCase()).type);
        }

        public static boolean isAfter(String code){
            return "after".equals(LogicModel.valueOf(code.toUpperCase()).type);
        }
    }


}
