package com.zeddic.trigger.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: zeddic
 * @description: 抽奖结果响应对象
 * @date: 2024/7/24 下午7:44
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RaffleResponseDTO {
    //奖品ID
    private Integer awardId;
    //奖品排序编号
    private Integer awardIndex;

}
