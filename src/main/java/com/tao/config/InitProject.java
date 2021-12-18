package com.tao.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @ClassName InitProject
 * @Author HETAO
 * @Date 2021/3/21 12:53
 */
@Component
public class InitProject implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("项目启动了");
    }
}
