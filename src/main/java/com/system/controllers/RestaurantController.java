package com.system.controllers;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.system.dao.MenuDAO;
import com.system.dao.RestaurantDAO;
import com.system.entity.Menu;
import com.system.entity.Restaurant;


@SpringBootApplication
@RestController
public class RestaurantController {

    @Autowired
    private RestaurantDAO restaurantDAO;

    @Autowired
    private MenuDAO menuDAO;
    
    
 
    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurants() {
        List<Restaurant> restaurants = (List<Restaurant>) restaurantDAO.findAll();
        return restaurants;
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant findRestaurantById(@PathVariable("id") int id) {
        Restaurant rest = restaurantDAO.findOne(id);
        System.out.println("Restaurant Object:" + rest);
        
        return rest;
    }

    @PostMapping("/restaurants")
    @ResponseBody 
    public void addRestaurants(@RequestBody  Restaurant restaurant) {
    	//System.out.println(restaurant.getRestaurantName());
	 		 restaurantDAO.save(restaurant);
    }
    
 
    @DeleteMapping("/restaurants")
    public void deleteAll() {
        restaurantDAO.deleteAll();
    }

    @DeleteMapping("/restaurants/{id}")
    public void deleteById(@PathVariable("id") int id) {
        restaurantDAO.delete(id);
    }
    
    @GetMapping("/restaurants/{id}/menus")
    public List<Menu> getMenus(@PathVariable("id") int id) {
        Restaurant rest = restaurantDAO.findOne(id);
        System.out.println(rest);
        if (rest != null)
            return menuDAO.findByRestaurantRestaurantId(id);
        
        return new LinkedList<Menu>();
    }

    @PostMapping("/restaurants/{id}/menus")
    public void addMenus(@PathVariable("id") int id, @RequestBody Menu menus) {
        Restaurant rest = restaurantDAO.findOne(id);
        if (rest == null)
            return ;

        List<Menu> menu = menuDAO.findByRestaurantRestaurantId(id);
        Menu nmenu = new Menu(String.valueOf(menus.getMenuId()),menus.getMenuName());
        nmenu.setRestaurant(rest);
        menu.add(nmenu);
        menuDAO.save(menu);
    }

    @DeleteMapping("/restaurants/{id}/menus")
    @Transactional
    public void deleteMenusFromRestaurant(@PathVariable("id") int id) {
        Restaurant rest = restaurantDAO.findOne(id);
        if (rest == null)
            return ;
        menuDAO.deleteByRestaurantRestaurantId(id);
    }

    @DeleteMapping("/restaurants/{id}/menus/{menuid}")
    public void deleteMenus(@PathVariable("id") int id, @PathVariable("menuid") int menuid) {
    	 Restaurant rest = restaurantDAO.findOne(id);
    	 if(rest==null)
    		 return;
    	 List<Menu> menus = menuDAO.findByRestaurantRestaurantId(id);
       menuDAO.delete(id);
    }
}