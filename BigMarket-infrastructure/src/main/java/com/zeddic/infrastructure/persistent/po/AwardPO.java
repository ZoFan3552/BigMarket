package com.zeddic.infrastructure.persistent.po;

import lombok.Data;

/**
 * @author: zeddic
 * @description: 奖品持久化对象
 * @date: 2024/7/16 下午3:24
 */
@Data
public class AwardPO {
    /**
     * 自增ID
     */
    private String id;
    /**
     * 抽奖奖品ID,内部流转
     */
    private String awardId;
    /**
     * 奖品对接标识
     */
    private String awardKey;
    /**
     * 奖品配置信息
     */
    private String awardConfig;
    /**
     * 奖品内容描述
     */
    private String awardDesc;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 更新时间
     */
    private String updateTime;

}
