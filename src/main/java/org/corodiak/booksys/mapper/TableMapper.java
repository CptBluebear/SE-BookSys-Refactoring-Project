package org.corodiak.booksys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.corodiak.booksys.domain.Table;

/*
 * Table Table Ã³¸®
 */
@Mapper
public interface TableMapper
{
	List<Table> selectTableList();
	List<Table> selectTableList(RowBounds rowBounds);
	
	int insertTable(@Param("table")Table table);
	
	int selectTableCount();
}
