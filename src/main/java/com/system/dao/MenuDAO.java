package com.system.dao;

import java.util.List;
import com.system.entity.Menu;
import com.system.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MenuDAO extends JpaRepository<Menu,Integer>{
	/**
	 * @param number
	 * @return
	 */
	   
	List<Menu> findByRestaurantRestaurantId(int RestaurantId);

    void deleteByRestaurantRestaurantId(int RestaurantId);
    
	/*List<Menu> findByName(String Name);
	List<Menu> getAllMenus();
    boolean addMenu(Restaurant restaurant,Menu menu);
    void updateMenu(Restaurant restaurant,Menu menu);
    void deleteMenu(Restaurant restaurant,int menuId);
    */
	/**
	 * @param restaurantId
	 */
	//List<Menu> findAll(Integer restaurantId);
}
