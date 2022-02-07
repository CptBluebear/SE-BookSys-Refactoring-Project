package org.corodiak.booksys.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

/*
 * web.xml을 대체하는 java config 파일
 */
public class WebConfig implements WebApplicationInitializer
{
	/*
	 * 웹 컨테이너 초기 구동시 설정 
	 */
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException
	{
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.scan("org.corodiak.booksys");
		servletContext.addListener(new ContextLoaderListener(context));
		
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
		applicationContext.register(DispacherServletConfig.class);
		ServletRegistration.Dynamic dispacherServlet = servletContext.addServlet("dispacherServlet", new DispatcherServlet(applicationContext));
		dispacherServlet.setLoadOnStartup(1);
		dispacherServlet.addMapping("/");
		
		FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("CHARCTER_ENCODING_FILTER", CharacterEncodingFilter.class);
		encodingFilter.setInitParameter("encoding", "UTF-8");
		encodingFilter.setInitParameter("forceEncoding", "true");
		encodingFilter.addMappingForUrlPatterns(null, false, "/*");
		
		
	}
}
