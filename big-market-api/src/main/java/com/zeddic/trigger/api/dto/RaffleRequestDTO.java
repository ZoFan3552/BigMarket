package com.zeddic.trigger.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: zeddic
 * @description: 抽奖请求对象
 * @date: 2024/7/24 下午7:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RaffleRequestDTO implements Serializable {

    // 使用RPC等微服务组件时在使用
//    private String userId;

    //策略ID
    private Long strategyId;
}
