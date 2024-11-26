package com.nofirst.zhihu.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置类
 */
@Configuration
@MapperScan({"com.nofirst.zhihu.mbg.mapper", "com.nofirst.zhihu.dao"})
public class MyBatisConfig {
}


