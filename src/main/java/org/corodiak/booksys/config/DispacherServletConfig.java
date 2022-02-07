package org.corodiak.booksys.config;

import java.util.Properties;

import org.corodiak.booksys.interceptor.AdminCheckInterceptor;
import org.corodiak.booksys.interceptor.LoginCheckInterceptor;
import org.corodiak.booksys.interceptor.LoginInfoInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/*
 * ��Ʈ�ѷ��� ����ϴ� DispacherServlet Ŭ����
 * ViewReslover, Resource Handler ���� ���⼭ �����
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"org.corodiak.booksys.controller"})
public class DispacherServletConfig implements WebMvcConfigurer 
{
	
	@Autowired
	LoginCheckInterceptor loginInterceptor;
	
	@Autowired
	LoginInfoInterceptor loginInfoInterceptor;
	
	@Autowired
	AdminCheckInterceptor adminCheckInterceptor;
	
	/*
	 * jsp view ��θ� ã�� ���� View Resolver �� ���
	 */
	@Bean
	public ViewResolver viewResolver()
	{
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	/*
	 * ��Ʈ�ѷ��� ��ĥ �ʿ䰡 ���� ���� ���ҽ� ������ ����ϴ�
	 * ResourceHandler�� ���
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry)
	{
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	/*
	 * ��Ʈ�ѷ� ó�� �� �α��� ���� ���� ó���ϱ� ���� ���ͼ��� ���
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry)
	{
		registry.addInterceptor(loginInterceptor)
			.addPathPatterns("/**")
			.excludePathPatterns("/error/**")
			.excludePathPatterns("/resources/**")
			.excludePathPatterns("/login")
			.excludePathPatterns("/register")
			.excludePathPatterns("/menu")
			.excludePathPatterns("/")
			.excludePathPatterns("/home");
		
		registry.addInterceptor(loginInfoInterceptor)
			.addPathPatterns("/**")
			.excludePathPatterns("/error/**")
			.excludePathPatterns("/resources/**/");
		
		registry.addInterceptor(adminCheckInterceptor)
			.addPathPatterns("/staff/**")
			.excludePathPatterns("/error/**")
			.excludePathPatterns("/resources/**/");
	}
	
	public SimpleMappingExceptionResolver exceptionResolver()
	{
		SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
		
		Properties statusCodes = new Properties();
		statusCodes.setProperty("/error/400", "400");
		statusCodes.setProperty("/error/403", "403");
		statusCodes.setProperty("/error/404", "404");
		statusCodes.setProperty("/error/500", "500");
		
		exceptionResolver.setStatusCodes(statusCodes);
		
		return exceptionResolver;
	}
	
}
