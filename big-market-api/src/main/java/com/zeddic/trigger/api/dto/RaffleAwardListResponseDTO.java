package com.zeddic.trigger.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: zeddic
 * @description: 抽奖请求奖品列表响应对象
 * @date: 2024/7/24 下午7:37
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RaffleAwardListResponseDTO implements Serializable {

    //奖品ID
    private Integer awardId;

    //奖品标题
    private String awardTitle;

    //奖品副标题
    private String awardSubtitle;

    //奖品排序
    private Integer sort;
}
