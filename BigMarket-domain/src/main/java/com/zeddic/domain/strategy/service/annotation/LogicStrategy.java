package com.zeddic.domain.strategy.service.annotation;

import com.zeddic.domain.strategy.service.rule.filter.factory.DefaultLogicFactory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: zeddic
 * @description: 标记注解，方便注入 LogicFilter 对象
 * @date: 2024/7/17 下午9:47
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogicStrategy {

    DefaultLogicFactory.LogicModel logicModel();
}
