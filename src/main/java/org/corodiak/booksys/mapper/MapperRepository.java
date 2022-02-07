package org.corodiak.booksys.mapper;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

/*
 * Mapper 빈 등록 클래스
 */
@Repository
public class MapperRepository
{
	@Autowired
	SqlSession sqlSession;
	
	@Bean
	public BookingMapper bookingMapper() { return sqlSession.getMapper(BookingMapper.class); }
	
	@Bean
	public CustomerMapper customerMapper() { return sqlSession.getMapper(CustomerMapper.class); }
	
	@Bean
	public MenuMapper menuMapper() { return sqlSession.getMapper(MenuMapper.class); }
	
	@Bean
	public OrderMapper orderMapper() { return sqlSession.getMapper(OrderMapper.class); }
	
	@Bean
	public ReservationMapper reservationMapper() { return sqlSession.getMapper(ReservationMapper.class); }
	
	@Bean
	public TableMapper tableMapper() { return sqlSession.getMapper(TableMapper.class); }
	
	@Bean
	public WaitingMapper waitingMapper() { return sqlSession.getMapper(WaitingMapper.class); }
	
	@Bean
	public WalkInMapper walkInMapper() { return sqlSession.getMapper(WalkInMapper.class); }
}