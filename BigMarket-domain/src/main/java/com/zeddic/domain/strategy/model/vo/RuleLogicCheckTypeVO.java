package com.zeddic.domain.strategy.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: zeddic
 * @description:
 * @date: 2024/7/17 下午9:38
 */
@Getter
@AllArgsConstructor
public enum RuleLogicCheckTypeVO {

    ALLOW("0000" , "放行，后续的流程不受规则引擎的执行结果影响"),
    TAKE_OVER("0001", "接管，后续的流程受规则引擎的执行结果影响"),
    ;

    private final String code;
    private final String info;
}
