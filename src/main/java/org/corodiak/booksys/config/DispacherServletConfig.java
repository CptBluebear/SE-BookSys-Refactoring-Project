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
 * 컨트롤러를 등록하는 DispacherServlet 클래스
 * ViewReslover, Resource Handler 등을 여기서 등록함
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
	 * jsp view 경로를 찾기 위한 View Resolver 빈 등록
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
	 * 컨트롤러를 거칠 필요가 없는 정적 리소스 폴더를 등록하는
	 * ResourceHandler를 등록
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry)
	{
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	/*
	 * 컨트롤러 처리 전 로그인 여부 등을 처리하기 위한 인터셉터 등록
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
