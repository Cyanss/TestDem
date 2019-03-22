package com.cyan.test.config.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import lombok.Data;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.naming.Name;
import javax.validation.Valid;
import java.beans.PropertyVetoException;

/**
 * @auther BlueThink Cyan
 * @create 2019/3/13
 */
@Configuration
@Data
@Component
@ConfigurationProperties(prefix = "jdbc")
@MapperScan("com.cyan.test.dao")
public class DataSourceConfiguration {
//    @Value("${jdbc.driver}")
    private String driver;
//    @Value("${jdbc.url}")
    private String url;
//    @Value("${jdbc.username}")
    private String username;
//    @Value("${jdbc.password}")
    private String password;

    @Bean(name = "dataSource")
    public ComboPooledDataSource createDataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(driver);
        dataSource.setJdbcUrl(url);
        dataSource.setUser(username);
        dataSource.setPassword(password);
        /** 关闭连接后不自动commit */
        dataSource.setAutoCommitOnClose(false);
        return dataSource;
    }


}
