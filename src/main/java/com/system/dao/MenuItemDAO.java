package com.system.dao;

import java.util.List;

import com.system.entity.*;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MenuItemDAO extends CrudRepository<MenuItem,Integer>{
	/**
	 * @param number
	 * @return
	 */
	List<MenuItem> findByName(String Name);
	
	List<MenuItem> findByPrice(int Price);
	List<MenuItem> getAllMenuItemsByMenu(int menuId);
    Restaurant getMenuById(long menuId);
    boolean addMenuItem(MenuItem menuitem);
    void updateMenuItem(MenuItem menuitem);
    void deleteMenuItem(int menuid);

	/**
	 * @param spec
	 */
	List<MenuItem> findAll(Specification spec);
}
