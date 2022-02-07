package org.corodiak.booksys.service;

import java.util.List;

import org.corodiak.booksys.domain.Table;
import org.corodiak.booksys.mapper.TableMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class TableServiceImpl implements TableService
{

	@Autowired
	TableMapper tableMapper;
	
	/*
	 * 테이블 목록 가져오기
	 */
	@Override
	public List<Table> getTableList()
	{
		return tableMapper.selectTableList();
	}

}
