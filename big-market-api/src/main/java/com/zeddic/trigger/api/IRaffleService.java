package com.zeddic.trigger.api;

import com.zeddic.trigger.api.dto.RaffleAwardListRequestDTO;
import com.zeddic.trigger.api.dto.RaffleAwardListResponseDTO;
import com.zeddic.trigger.api.dto.RaffleRequestDTO;
import com.zeddic.trigger.api.dto.RaffleResponseDTO;
import com.zeddic.types.model.Response;

import java.util.List;

/**
 * @author: zeddic
 * @description:
 * @date: 2024/7/24 下午7:30
 */
public interface IRaffleService {

    /**
     * 策略装配接口
     *
     * @param strategyId 策略ID
     * @return 装配结果
     */
    Response<Boolean> strategyArmory(Long strategyId);

    /**
     *
     * @param request 抽奖奖品列表的请求参数
     * @return 抽奖的奖品列表数据
     */
    Response<List<RaffleAwardListResponseDTO>> queryRaffleAwardList(RaffleAwardListRequestDTO request);


    /**
     *
     * @param request 随机抽奖的请求参数
     * @return 随机抽奖的结果
     */
    Response<RaffleResponseDTO> randomRaffle(RaffleRequestDTO request);
}
