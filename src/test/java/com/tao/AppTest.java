package com.tao;

import static org.junit.Assert.assertTrue;

import com.tao.util.YouDaoUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Unit test for simple App.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AppTest 
{
    @Resource
    private YouDaoUtil youDaoYun;
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }


    @Test
    public void test() throws IOException {
        youDaoYun.createWord("insist",1,"sist");
        //youDaoYun.requestForHttp();
    }
}
