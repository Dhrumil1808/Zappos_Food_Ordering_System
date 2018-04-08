package com.system.dao;

import java.util.List;
import com.system.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MenuItemDAO extends JpaRepository<MenuItem,Integer>{
	/**
	 * @param number
	 * @return
	 */
	List<MenuItem> findByMenuMenuId(int Menu_Id);
	/*
	List<MenuItem> findByName(Menu menu,String Name);
	
	List<MenuItem> findByPrice(Menu menu,int Price);
	List<MenuItem> getAllMenuItemsByMenu(Menu menu,int menuId);
    boolean addMenuItem(Menu menu,MenuItem menuitem);
    void updateMenuItem(Menu menu,MenuItem menuitem);
    void deleteMenuItem(Menu menu,int menuitemid);

	/**
	 * @param spec
	 */
	//List<MenuItem> findAll(Specification spec);
}
