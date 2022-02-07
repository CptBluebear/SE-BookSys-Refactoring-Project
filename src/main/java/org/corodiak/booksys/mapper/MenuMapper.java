package org.corodiak.booksys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.corodiak.booksys.domain.Menu;

/*
 * Menu Table Ã³¸®
 */
@Mapper
public interface MenuMapper
{
	List<Menu> selectMenuList();
	List<Menu> selectMenuList(RowBounds rowBounds);
	
	Menu selectMenuByOid(@Param("oid")int oid);
	
	int insertMenu(@Param("menu")Menu menu);
	
	int deleteMenuByOid(@Param("oid")int oid);
	
}
