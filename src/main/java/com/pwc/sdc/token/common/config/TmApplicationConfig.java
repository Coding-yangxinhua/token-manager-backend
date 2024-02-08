package com.pwc.sdc.token.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.pwc.sdc.token.mapper")
@EnableCaching
public class TmApplicationConfig {

}