package com.zeddic.trigger.api.dto;

import lombok.Data;

/**
 * @author: zeddic
 * @description: 抽奖奖品列表，请求对象
 * @date: 2024/7/24 下午7:34
 */
@Data
public class RaffleAwardListRequestDTO {
    //抽奖策略ID
    private Long strategyId;
}
