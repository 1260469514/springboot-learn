package com.hackdog.springbootlearn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

@Configuration
@WebFilter(filterName = "encodeFilter",initParams = {@WebInitParam(name = "encoding",value = "UTF-8")},urlPatterns = "*")
public class EncodeFilter extends CharacterEncodingFilter {
}
