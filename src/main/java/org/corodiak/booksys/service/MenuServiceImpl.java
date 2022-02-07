package org.corodiak.booksys.service;

import java.util.List;

import org.corodiak.booksys.domain.Menu;
import org.corodiak.booksys.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class MenuServiceImpl implements MenuService
{

	@Autowired
	MenuMapper menuMapper;
	
	/*
	 * �޴� ��� �߰�
	 */
	@Override
	public boolean addMenu(String name, int price, String description, String imagePath)
	{
		try
		{
			Menu menu = new Menu();
			menu.setMenuName(name);
			menu.setMenuPrice(price);
			menu.setMenuDescription(description);
			menu.setMenuImagePath(imagePath);
			
			int result = menuMapper.insertMenu(menu);
			if(result == 0) return false;
			else return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	/*
	 * �޴� ��� ��������
	 */
	@Override
	public List<Menu> getMenuList()
	{
		try
		{
			return menuMapper.selectMenuList();
		}
		catch(Exception e)
		{
			return null;
		}
	}

	/*
	 * �޴� ��� ����
	 */
	@Override
	public boolean removeMenu(int oid)
	{
		try
		{
			menuMapper.deleteMenuByOid(oid);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

}
