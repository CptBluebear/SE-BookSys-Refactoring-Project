package org.corodiak.booksys.mapper;

import org.apache.ibatis.annotations.Select;

/*
 * ���� �׽�Ʈ DAO
 */
public interface TestDao
{
	@Select("Select 1")
	int test();
}
