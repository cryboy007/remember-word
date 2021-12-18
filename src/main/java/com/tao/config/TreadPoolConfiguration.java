package com.tao.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * @ClassName TreadPoolConfiguration
 * @Author HETAO
 * @Date 2021/3/19 16:36
 */
@Configuration
public class TreadPoolConfiguration {
    @Value("${corePoolSize}")
    private Integer corePoolSize;

    @Value("${maximumPoolSize}")
    private Integer maximumPoolSize;

    @Value("${maximumPoolSize}")
    private Long keepAliveTime;


    //@Bean
    public ThreadPoolExecutor threadPoolExecutor () {
        RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
        return new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(50),
                handler
        );
    }
}
