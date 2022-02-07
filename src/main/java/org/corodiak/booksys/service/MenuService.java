package org.corodiak.booksys.service;

import java.util.List;

import org.corodiak.booksys.domain.Menu;

public interface MenuService
{
	public boolean addMenu(String name, int price, String description, String imagePath);
	public List<Menu> getMenuList();
	public boolean removeMenu(int oid);
}
