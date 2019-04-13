package com.zelin.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author: Feng.Wang
 * @Company: Zelin.ShenZhen
 * @Description:
 * @Date: Create in 2019/4/13 09:55
 */
@Configuration   //此注解就是相当于原来的applicationContext.xml文件
@PropertySource("classpath:druid.properties")
public class DruidConfiguration {

    @Bean        //此注解：相当于spring配置文件中的<bean>标签
    @ConfigurationProperties(prefix = "spring.datasource")
    //工作原理：就是将druid.properties文件中的前缀后的字符串取出在DruidDataSource类中找以前面取出的字符串的属性，并为其
    //赋值
    public DruidDataSource druidDataSource(){
        return new DruidDataSource();
    }
}
