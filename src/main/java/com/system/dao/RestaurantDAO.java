package com.system.dao;

import java.util.List;
import com.system.entity.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RestaurantDAO extends JpaRepository<Restaurant,Integer>{
	/**
	 * @param number
	 * @return
	 */
	/*List<Restaurant> findByName(String Name);

	List<Restaurant> getAllRestaurants();
    Restaurant getRestaurantById(int restaurantId);
    boolean addRestaurant(Restaurant restaurant);
    void updateRestaurant(Restaurant restaurant);
    void deleteRestaurant(int restaurantId);
	/**
	 * @param spec
	 */
	//List<Restaurant> findAll(Specification<?> spec);
}
