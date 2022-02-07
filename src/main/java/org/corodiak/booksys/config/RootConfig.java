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
 * �ֿ� �� ��� �� Datasource �� ���
 */
@Configuration
@EnableTransactionManagement
public class RootConfig
{
	
	/*
	 * Datasource�� ������ �� ���
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
	 * �α��� üũ ���ͼ��� �� ���
	 */
	@Bean
	public LoginCheckInterceptor loginInterceptor()
	{
		return new LoginCheckInterceptor();
	}
	
	/*
	 * �α��� ���� ���ͼ��� �� ���
	 */
	@Bean
	public LoginInfoInterceptor loginInfoInterceptor()
	{
		return new LoginInfoInterceptor();
	}
	
	/*
	 * ���� üũ ���ͼ�Ʈ �� ���
	 */
	@Bean
	public AdminCheckInterceptor adminCheckInterceptor()
	{
		return new AdminCheckInterceptor();
	}
	
	/*
	 * ÷������ ó���� ���� MultipartResolver �� ���
	 */
	@Bean
	public MultipartResolver multipartResolver() throws IOException
	{
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
		commonsMultipartResolver.setUploadTempDir(new FileSystemResource("C:\\attach_file"));
		return commonsMultipartResolver;
	}
	
	/*
	 * JNDI�� �̿��� Datasource�� �� �����̳ʿ��� ������ �� �� ���
	 * �ּ� ���� �� ������ ���� �� ��� ����
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