package com.zeddic.trigger.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: zeddic
 * @description: 抽奖奖品列表，请求对象
 * @date: 2024/7/24 下午7:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RaffleAwardListRequestDTO implements Serializable {
    //抽奖策略ID
    private Long strategyId;
}
