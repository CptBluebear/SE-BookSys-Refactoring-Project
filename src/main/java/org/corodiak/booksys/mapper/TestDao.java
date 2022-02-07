package org.corodiak.booksys.mapper;

import org.apache.ibatis.annotations.Select;

/*
 * 연동 테스트 DAO
 */
public interface TestDao
{
	@Select("Select 1")
	int test();
}
