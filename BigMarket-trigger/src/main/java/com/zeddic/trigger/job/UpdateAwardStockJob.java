package com.zeddic.trigger.job;

import com.zeddic.domain.strategy.model.vo.StrategyAwardStockKeyVO;
import com.zeddic.domain.strategy.service.IRaffleStock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author: zeddic
 * @description: 更新奖品库存任务，使用Redis的异步队列更新数据库，减少数据库压力
 * @date: 2024/7/23 下午2:37
 */
@Slf4j
@Component
public class UpdateAwardStockJob {

    @Resource
    private IRaffleStock raffleStock;

    @Scheduled(cron = "0/5 * * * * ?")
    public void execTask(){
        try {
            log.info("定时任务，更新奖品库存开始");
            StrategyAwardStockKeyVO strategyAwardStockKeyVO = raffleStock.takeQueueValue();
            if (null == strategyAwardStockKeyVO) {
                log.info("定时任务，无需更新");
                return;
            }
            raffleStock.updateStrategyAwardStock(strategyAwardStockKeyVO.getStrategyId(),  strategyAwardStockKeyVO.getAwardId());
            log.info("定时任务，更新奖品库存成功");
        }catch (Exception e){
            log.error("定时任务：更新奖品库存失败" , e);
        }
    }
}
