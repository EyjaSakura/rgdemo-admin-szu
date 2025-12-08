package com.szu.admin.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.szu.admin.mapper")
public class MybatisConfig {
    // 目前不需要额外配置
    // 占位以便将来扩展（分页插件、性能监控等）
}