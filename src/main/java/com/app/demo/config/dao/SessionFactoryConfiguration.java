package com.app.demo.config.dao;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class SessionFactoryConfiguration {

    @Value("mybatis.modelPackage")
    private String modelPackage;

    @Value("mybatis.config-file")
    private String mybatisConfigurationPath;

    @Value("mybatis.mapper-path")
    private String mapperPath;

    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean createSqlSessionFactoryBean() throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(mybatisConfigurationPath));
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        String packageSearchPath = PathMatchingResourcePatternResolver.CLASSPATH_URL_PREFIX + mapperPath;
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(packageSearchPath));
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage(modelPackage);
        return sqlSessionFactoryBean;
    }
}
