package org.corodiak.booksys.domain;

/*
 * 메뉴 정보
 */
public class Menu
{
	protected int menuOid;
	protected String menuName;
	protected int menuPrice;
	protected String menuDescription;
	protected String menuImagePath;
	
	public Menu() {}
	
	public int getMenuOid() { return menuOid; }
	public String getMenuName() { return menuName; }
	public int getMenuPrice() { return menuPrice; }
	public String getMenuDescription() { return menuDescription; }
	public String getMenuImagePath() { return menuImagePath; }
	
	public void setMenuOid(int menuOid) { this.menuOid = menuOid; }
	public void setMenuName(String menuName) { this.menuName = menuName; }
	public void setMenuPrice(int menuPrice) { this.menuPrice = menuPrice; }
	public void setMenuDescription(String menuDescription) { this.menuDescription = menuDescription; }
	public void setMenuImagePath(String menuImagePath) { this.menuImagePath = menuImagePath; }
}
