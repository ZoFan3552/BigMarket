package com.zeddic.test.infrastructure;

import com.zeddic.infrastructure.persistent.dao.IAwardDAO;
import com.zeddic.infrastructure.persistent.po.AwardPO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: zeddic
 * @description: 奖品持久化单元测试
 * @date: 2024/7/16 下午4:05
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class AwardDAOTest {

    @Resource
    private IAwardDAO awardDAO;

    @Test
    public void test_queryAwardList(){
        List<AwardPO> awardPOS = awardDAO.queryAwardList();
        log.info("测试结果：{}", awardPOS.toString());
    }
}
