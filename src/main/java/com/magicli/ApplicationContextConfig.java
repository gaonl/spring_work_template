package com.magicli;

import com.alibaba.druid.pool.DruidDataSource;
import com.magicli.aop.DaoInvokeCounter;
import com.magicli.beans.LiveCycleBean;
import com.magicli.ioc.dao.UserDao;
import com.magicli.ioc.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by gaonl on 2018/9/29.
 */
@Configuration //说明此类是java配置类
@ComponentScan(basePackages = "com.magicli")//设置扫描的基础包
//@ComponentScan("com.magicli") //设置扫描的基础包
//@ComponentScan(basePackages = {"com.magicli.aop","com.magicli.ioc"})//设置多个扫描的基础包
//@ComponentScan(basePackageClasses = {UserDao.class,User.class})//设置类所在的包及其子包的扫描路径，可以用标记接口来代替
@PropertySource("classpath:application.properties")
public class ApplicationContextConfig {
    @Value("${datasource.name}")
    private String name;
    @Value("${datasource.driverClassName}")
    private String driverClassName;
    @Value("${datasource.url}")
    private String url;
    @Value("${datasource.username}")
    private String username;
    @Value("${datasource.password}")
    private String password;


    /**
     * 要配置这本bean,才能使用上述的占位符
     *
     * @return
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public DataSource dataSource() throws Exception {
        DruidDataSource dataSource = new DruidDataSource();

        dataSource.setName(name);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        dataSource.setFilters("stat");
        dataSource.setMaxActive(20);
        dataSource.setInitialSize(2);
        dataSource.setMinIdle(1);
        dataSource.setMaxWait(60000);
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        dataSource.setMinEvictableIdleTimeMillis(300000);
        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        dataSource.setPoolPreparedStatements(true);
        dataSource.setMaxOpenPreparedStatements(20);


        return dataSource;
    }

    @Bean
    public NamedParameterJdbcTemplate jdbcTemplate() throws Exception {
        return new NamedParameterJdbcTemplate(dataSource());
    }

    @Bean
    public LiveCycleBean liveCycleBean() {
        return new LiveCycleBean();
    }

    @Bean
    public DaoInvokeCounter daoInvokerCounter() {
        return new DaoInvokeCounter();
    }
}
