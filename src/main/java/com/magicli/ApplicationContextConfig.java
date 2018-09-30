package com.magicli;

import com.alibaba.druid.pool.DruidDataSource;
import com.magicli.aop.DaoInvokeCounter;
import com.magicli.beans.LiveCycleBean;
import com.magicli.ioc.dao.UserDao;
import com.magicli.ioc.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
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
//@PropertySource("classpath:application.properties")
//@ImportResource({"path-to-application-context.xml"})//导入其他给予xml的配置文件
@Import({ApplicationDataSourceConfig.class})//导入其他基于java配置的class文件
public class ApplicationContextConfig {

    @Bean(name = "jdbcTemplate")
    public NamedParameterJdbcTemplate jdbcTemplate(DataSource dataSource) throws Exception {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean(name = "liveCycleBean", initMethod = "myInit", destroyMethod = "myDestroy")
    public LiveCycleBean liveCycleBean() {
        return new LiveCycleBean();
    }

    @Bean(name = "daoInvokerCounter")
    public DaoInvokeCounter daoInvokerCounter() {
        return new DaoInvokeCounter();
    }
}
