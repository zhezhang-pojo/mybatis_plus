package com.gk.mybatisplus.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Administrator
 * @create 2019-12-18 18:40
 */
@EnableTransactionManagement
@Configuration//声明此类是配置类
@MapperScan("com.gk.mybatisplus.mapper")
public class config {
    //乐观锁插件
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }
    //分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
    @Bean//逻辑删除需要注册
    public ISqlInjector sqlInjector(){
        return new LogicSqlInjector();
    }

    /**
     * sql执行分析插件(建议仅在开发环境使用)
     * @return
     */
    @Bean
    @Profile({"dev","test"})//设置dev test环境开启
    public PerformanceInterceptor performanceInterceptor(){
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        performanceInterceptor.setMaxTime(200);//设置超时时间
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }
}
