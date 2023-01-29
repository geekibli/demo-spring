package com.example.testspring.a05;

//import com.alibaba.druid.pool.DruidDataSource;
//import com.example.testspring.a05.mapper.Mapper1;
//import com.example.testspring.a05.mapper.Mapper2;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.example.testspring.a05.Component")
public class Config {

    @Bean
    public Bean1 bean1() {
        return new Bean1();
    }

    public Bean2 bean2() {
        return new Bean2();
    }

//    @Bean(initMethod = "init")
//    public Bean3 bean3(Bean1 bean1) {
//        return new Bean3();
//    }

//    @Bean
//    public SqlSessionFactoryBean sqlSessionFactoryBean (DataSource dataSource) {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(dataSource);
//        return sqlSessionFactoryBean;
//    }
//
//
//    @Bean
//    public DataSource dataSource(){
//        DruidDataSource dataSource = new DruidDataSource();
//        return dataSource;
//    }

//    @Bean
//    MapperFactoryBean<Mapper1> mapper1 (SqlSessionFactory sqlSessionFactory) {
//        MapperFactoryBean<Mapper1> factoryBean = new MapperFactoryBean<>();
//        factoryBean.setSqlSessionFactory(sqlSessionFactory);
//        return factoryBean;
//    }
//
//    @Bean
//    MapperFactoryBean<Mapper2> mapper2 (SqlSessionFactory sqlSessionFactory) {
//        MapperFactoryBean<Mapper2> factoryBean = new MapperFactoryBean<>();
//        factoryBean.setSqlSessionFactory(sqlSessionFactory);
//        return factoryBean;
//    }


}
