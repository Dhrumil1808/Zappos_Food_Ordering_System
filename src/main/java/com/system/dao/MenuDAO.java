package com.system.dao;

import java.util.List;
import com.system.entity.Menu;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MenuDAO extends CrudRepository<Menu,Integer>{
	/**
	 * @param number
	 * @return
	 */
	List<Menu> findByName(String Name);
	List<Menu> getAllMenus();
    Menu getMenuById(long menuId);
    boolean addMenu(Menu menu);
    void updateMenu(Menu menu);
    void deleteMenu(int menuId);
	/**
	 * @param spec
	 */
	List<Menu> findAll(Specification spec);
}
