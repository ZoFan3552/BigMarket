package com.zeddic.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * @author: zeddic
 * @description: 规则树节点
 * @date: 2024/7/22 下午5:30
 */
@Data
public class RuleTreeNodePO {

    /** 自增ID */
    private Long id;
    /** 规则树ID */
    private String treeId;
    /** 规则Key */
    private String ruleKey;
    /** 规则描述 */
    private String ruleDesc;
    /** 规则比值 */
    private String ruleValue;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;

}
