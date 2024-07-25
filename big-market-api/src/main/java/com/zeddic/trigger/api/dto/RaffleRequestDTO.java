package com.zeddic.trigger.api.dto;

import lombok.Data;

/**
 * @author: zeddic
 * @description: 抽奖请求对象
 * @date: 2024/7/24 下午7:41
 */
@Data
public class RaffleRequestDTO {

    // 使用RPC等微服务组件时在使用
//    private String userId;

    //策略ID
    private Long strategyId;
}
