package org.corodiak.booksys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.corodiak.booksys.domain.Waiting;

/*
 * Waiting Table Ã³¸®
 */
@Mapper
public interface WaitingMapper
{
	int insertWaiting(@Param("waiting")Waiting waiting, @Param("customerOid")int customerOid);

	List<Waiting> selectWaitingByCustomerOid(@Param("customerOid")int customerOid);
	
	int deleteWaitingByOid(@Param("oid")int oid);
	
	Waiting selectWaitingByOid(@Param("oid")int oid);
	
	List<Waiting> selectWaitingList();
	List<Waiting> selectWaitingList(RowBounds rowBounds);
}
