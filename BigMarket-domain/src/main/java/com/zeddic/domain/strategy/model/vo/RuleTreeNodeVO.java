package com.zeddic.domain.strategy.model.vo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: zeddic
 * @description:
 * @date: 2024/7/22 下午2:01
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RuleTreeNodeVO {

    /** 规则树ID */
    private String treeId;
    /** 规则Key */
    private String ruleKey;
    /** 规则描述 */
    private String ruleDesc;
    /** 规则比值 */
    private String ruleValue;

    /** 规则连线 */
    private List<RuleTreeNodeLineVO> treeNodeLineVOList;

}

