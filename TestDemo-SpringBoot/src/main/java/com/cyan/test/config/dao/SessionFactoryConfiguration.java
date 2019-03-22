package com.cyan.test.config.dao;

import lombok.Data;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @auther BlueThink Cyan
 * @create 2019/3/13
 */
@Configuration
@Data
@Component
@ConfigurationProperties(prefix = "mybatis")
public class SessionFactoryConfiguration {
//    @Value("${mybatis.configPath}")
    private String configPath;
//    @Value("${mybatis.entityPackage}")
    private String entityPackage;
//    @Value("${mybatis.mapperPath}")
    private String mapperPath;

    @Autowired
    private DataSource dataSource;

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean createSqlSessionFactoryBean() throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(configPath));
        PathMatchingResourcePatternResolver resolver =new PathMatchingResourcePatternResolver();
        String packageSearchPath =PathMatchingResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + mapperPath;
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(packageSearchPath));
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage(entityPackage);
        return sqlSessionFactoryBean;
    }

}
