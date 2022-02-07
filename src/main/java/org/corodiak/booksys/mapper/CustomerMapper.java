package org.corodiak.booksys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.corodiak.booksys.domain.Customer;

/*
 * Customer Table Ã³¸®
 */
@Mapper
public interface CustomerMapper
{
	Customer selectCustomerByIdandPassword(@Param("id")String id, @Param("password")String password);
	List<Customer> selectCustomerList();
	List<Customer> selectCustomerList(RowBounds rowBounds);
	
	Customer selectCustomerById(@Param("id")String id);
	
	int insertCustomer(@Param("customer")Customer customer);
	
	Customer selectCustomerByOid(@Param("oid")int oid);
}