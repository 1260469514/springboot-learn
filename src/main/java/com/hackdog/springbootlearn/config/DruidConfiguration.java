package com.hackdog.springbootlearn.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DruidConfiguration {
    @Bean
    public ServletRegistrationBean druidStatViewServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
        servletRegistrationBean.addInitParameter("deny", "192.168.12.23");
        servletRegistrationBean.addInitParameter("loginUsername", "druid");
        servletRegistrationBean.addInitParameter("loginPassword", "admin");
        servletRegistrationBean.addInitParameter("restEnable", "false");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean druidSataFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.jpg,*.png,*.gif,*.css,*.html,*.ico,/druid/2");
        return filterRegistrationBean;
    }

    @Bean
    public DruidStatInterceptor druid(){
        return  new DruidStatInterceptor();
    }

    @Bean
    public JdkRegexpMethodPointcut setJdkRegexpMethodPointcut(){
        JdkRegexpMethodPointcut jdkRegexpMethodPointcut = new JdkRegexpMethodPointcut();
        jdkRegexpMethodPointcut.setPatterns("com.hackdog.springbootlearn.service.*");
        return jdkRegexpMethodPointcut;
    }

}
