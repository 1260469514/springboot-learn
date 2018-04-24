package com.hackdog.springbootlearn.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author wangzeying
 * @version 1.0.0
 * @desc druid数据源配置类
 * @date 2018/4/24 10:56:00
 */

@Configuration
@MapperScan(basePackages = {DruidDataSourceConfiguration.MAPPER_PACKAGE}, sqlSessionFactoryRef = DruidDataSourceConfiguration.SESSIONFACTORY_NAME)
public class DruidDataSourceConfiguration {
    /**
     * SqlSessionFactory名称.
     */
    protected final static String SESSIONFACTORY_NAME = "sqlSessionFactory";
    /**
     * mapper包路径，必须与其他SqlSessionFactory-mapper路径区分.
     */
    protected final static String MAPPER_PACKAGE = "com.hackdog.springbootlearn.mapper";
    /**
     * mapper.xml文件路径，必须与其他SqlSessionFactory-mapper路径区分.
     */
    private final static String MAPPER_XML_PATH = "classpath:mapper/*.xml";
    /**
     * 实体类包
     */
    private final static String POJO_BASE_PATH = "com.hackdog.springbootlearn.pojo";

    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        return druidDataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(druidDataSource());
        sqlSessionFactoryBean.setTypeAliasesPackage(POJO_BASE_PATH);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_XML_PATH));
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
        return sqlSessionFactory;
    }
}
