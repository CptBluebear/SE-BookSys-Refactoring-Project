package org.corodiak.booksys.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.corodiak.booksys.domain.Customer;
import org.corodiak.booksys.mapper.CustomerMapper;
import org.corodiak.booksys.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService
{
	@Autowired
	CustomerMapper customerMapper;
	
	/*
	 * 회원가입 처리
	 */
	@Override
	public boolean register(String id, String password, String name, String phoneNumber, String birthday)
	{
		Customer customer = new Customer();
		customer.setCustomerId(id);
		customer.setCustomerPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
		customer.setCustomerName(name);
		customer.setCustomerPhoneNumber(phoneNumber);
		customer.setCustomerBirthday(Date.valueOf(birthday));
		try {
			customerMapper.insertCustomer(customer);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/*
	 * 로그인 처리
	 */
	@Override
	public String login(String id, String password)
	{
		try {
			Customer customer = customerMapper.selectCustomerById(id);
			if(customer == null) return null;
			if(!BCrypt.checkpw(password, customer.getCustomerPassword())) return null;
			Map<String, Object> payload = new HashMap<String, Object>();
			payload.put("customerOid", customer.getCustomerOid());
			payload.put("customerIsAdmin", customer.getCustomerIsAdmin());
			return JWTUtil.createToken(payload);
		} catch (Exception e) {
			return null;
		}
	}
	
	/*
	 * CustomerOid를 기반으로 회원정보 가져오기
	 */
	@Override
	public Customer getCustomerInformation(int oid)
	{
		return customerMapper.selectCustomerByOid(oid);
	}
}