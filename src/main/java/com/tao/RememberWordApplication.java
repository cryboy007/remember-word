package com.tao;

import com.tao.util.YouDaoUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @ClassName RememberWordApplication
 * @Author HETAO
 * @Date 2021/3/19 15:41
 */
@SpringBootApplication
@EnableConfigurationProperties({YouDaoUtil.class})
@MapperScan("com.tao.dao")
public class RememberWordApplication {
    public static void main(String[] args) {
        SpringApplication.run(RememberWordApplication.class,args);
    }
}
