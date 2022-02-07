package org.corodiak.booksys.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.corodiak.booksys.interceptor.AdminCheckInterceptor;
import org.corodiak.booksys.interceptor.LoginCheckInterceptor;
import org.corodiak.booksys.interceptor.LoginInfoInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/*
 * 주요 빈 등록 및 Datasource 빈 등록
 */
@Configuration
@EnableTransactionManagement
public class RootConfig
{
	
	/*
	 * Datasource를 생성후 빈 등록
	 */
	@Bean
	public DataSource dataSource()
	{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("***********************");
		dataSource.setUrl("***********************");
		dataSource.setUsername("***********************");
		dataSource.setPassword("***********************");
		
		return dataSource;
	}
	
	/*
	 * 로그인 체크 인터셉터 빈 등록
	 */
	@Bean
	public LoginCheckInterceptor loginInterceptor()
	{
		return new LoginCheckInterceptor();
	}
	
	/*
	 * 로그인 인포 인터셉터 빈 등록
	 */
	@Bean
	public LoginInfoInterceptor loginInfoInterceptor()
	{
		return new LoginInfoInterceptor();
	}
	
	/*
	 * 어드민 체크 인터셉트 빈 등록
	 */
	@Bean
	public AdminCheckInterceptor adminCheckInterceptor()
	{
		return new AdminCheckInterceptor();
	}
	
	/*
	 * 첨부파일 처리를 위한 MultipartResolver 빈 등록
	 */
	@Bean
	public MultipartResolver multipartResolver() throws IOException
	{
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
		commonsMultipartResolver.setUploadTempDir(new FileSystemResource("C:\\attach_file"));
		return commonsMultipartResolver;
	}
	
	/*
	 * JNDI를 이용한 Datasource를 웹 컨테이너에서 가져온 후 빈 등록
	 * 주석 해제 후 서버에 설정 시 사용 가능
	 */
	/*
	@Bean
	@Resource(name="jdbc/mariadb")
	public DataSource dataSource()
	{
		JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
		dataSourceLookup.setResourceRef(true);
		DataSource dataSource = dataSourceLookup.getDataSource("java:comp/env/jdbc/mariadb");
		return dataSource;
	}
	*/
}