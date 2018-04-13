package com.system.controllers;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.system.dao.MenuDAO;
import com.system.dao.MenuItemDAO;
import com.system.dao.RestaurantDAO;
import com.system.entity.Menu;
import com.system.entity.MenuItem;
import com.system.entity.Restaurant;


@SpringBootApplication
@RestController
public class RestaurantController {

    @Autowired
    private RestaurantDAO restaurantDAO;

    @Autowired
    private MenuDAO menuDAO;
    
    @Autowired
    private MenuItemDAO menuitemDAO;
 
  
    //@Cacheable(value="rest",key="#p0")
    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurants() {
        List<Restaurant> restaurants = (List<Restaurant>) restaurantDAO.findAll();
        return restaurants;
    }

    
    
    @GetMapping("/restaurants/{id}")
    public Restaurant findRestaurantById(@PathVariable("id") int id) {
        Restaurant rest = restaurantDAO.findOne(id);
       System.out.println(rest.getMenus().size());
 
        return rest;
    }

    @PostMapping("/restaurants")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody 
    public void addRestaurants(@RequestBody  Restaurant restaurant) {
    		 restaurantDAO.save(restaurant);
    }
    
    @CachePut(value = "resto", key = "#p0")
    @PutMapping("/restaurants/{id}")
    @ResponseBody 
    public void editRestaurants(@PathVariable("id") int id,@RequestBody  Restaurant restaurant) {
    	//System.out.println(restaurant.getRestaurantName());
    		Restaurant rest = restaurantDAO.findOne(id);
    		if(rest!=null){
    			rest.setRestaurantName(restaurant.getRestaurantName());
    		}
    		restaurantDAO.save(rest);
	 		 //restaurantDAO.save(restaurant);
    }
    
    @CacheEvict(value = "rest0", allEntries=true)
    @DeleteMapping("/restaurants")
    public void deleteAll() {
        restaurantDAO.deleteAll();
    }

    @DeleteMapping("/restaurants/{id}")
    public void deleteById(@PathVariable("id") int id) {
        restaurantDAO.delete(id);
    }
    
    //@Cacheable(value = "restaurantmenu", key = "#p0")
    @GetMapping("/restaurants/{id}/menus")
    public List<Menu> getMenus(@PathVariable("id") int id) {
        Restaurant rest = restaurantDAO.findOne(id);
        System.out.println(rest);
        if (rest != null)
            return menuDAO.findByRestaurantRestaurantId(id);
        
        return new LinkedList<Menu>();
    }
    
    @Cacheable(value = "menuitems", key = "#p0")
    @GetMapping("/restaurants/{id}/menus/{menuid}")
    public List<MenuItem> getMenuItemsFromMenu(@PathVariable("id") int id,@PathVariable("menuid") int menuid) {
        Restaurant rest = restaurantDAO.findOne(id);
        System.out.println(rest);
        List<Menu> menu = new ArrayList<>();
        if (rest != null)
             menu = menuDAO.findByRestaurantRestaurantId(id);
        
        
        if(menu.size()!=0)
        	return menuitemDAO.findByMenuMenuId(menuid);
        
        
        return new ArrayList<MenuItem>();
        
    }

    @PostMapping("/restaurants/{id}/menus")
    @ResponseStatus(HttpStatus.CREATED)
    public void addMenus(@PathVariable("id") int id, @RequestBody Menu menus) {
        Restaurant rest = restaurantDAO.findOne(id);
        if (rest == null)
            return ;

        List<Menu> menu = menuDAO.findByRestaurantRestaurantId(id);
        Menu nmenu = new Menu(menus.getMenuName());
        nmenu.setRestaurant(rest);
        menu.add(nmenu);
        menuDAO.save(menu);
    }
    
    @CachePut(value = "restaurant", key = "#p0")
    @PutMapping("/restaurants/{id}/menus/{menuid}")
    public void editMenus(@PathVariable("id") int id, @RequestBody Menu menus,@PathVariable("menuid") int menuid) {
        Restaurant rest = restaurantDAO.findOne(id);
        if (rest == null)
            return ;

        List<Menu> menu = menuDAO.findByRestaurantRestaurantId(id);
        for(Menu m:menu){
        	if(menuid==m.getMenuId()){
        		m.setMenuName(menus.getMenuName());
        	}
        }
        
        menuDAO.save(menu);
    }

    
    @PostMapping("/restaurants/{id}/menus/{menuid}/items")
    @ResponseStatus(HttpStatus.CREATED)
    public void addMenuItems(@PathVariable("id") int id,@PathVariable("menuid") int menuid, @RequestBody MenuItem items) {
        Restaurant rest = restaurantDAO.findOne(id);
        if (rest == null)
            return ;
        
       System.out.println("restaurant " + rest);
        
       List<Menu> menu = menuDAO.findByRestaurantRestaurantId(id);
        //System.out.println(menu.size());
         
       List<MenuItem> menuitem = menuitemDAO.findByMenuMenuId(menuid);
       MenuItem menuitems =  new MenuItem(items.getItemId(),items.getItemName(),items.getItemPrice());
       menuitems.setRestaurant(rest);
       int i=0;
       for(Menu m:menu){
       	if(menuid==m.getMenuId()){
       	 menuitems.setMenu(menu.get(i));
       	 break;
       	}
       	i++;
       }
     
       menuitem.add(menuitems);
       menuitemDAO.save(menuitems);
    }

    @CachePut(value = "menuitem", key = "#p0")
    @PutMapping("/restaurants/{id}/menus/{menuid}/items/{itemid}")
    public void editMenuItems(@PathVariable("id") int id, Menu menus,@PathVariable("menuid") int menuid, @RequestBody MenuItem items,@PathVariable("itemid") int itemid) {
        Restaurant rest = restaurantDAO.findOne(id);
        if (rest == null)
            return ;
        
       System.out.println("restaurant " + rest);
        
       List<Menu> menu = menuDAO.findByRestaurantRestaurantId(id);
       List<MenuItem> menuitem = new ArrayList<>();
       
       boolean put = false;
       
        for(Menu m:menu){
        	System.out.println(m.getMenuId());
       	if(menuid==m.getMenuId()){
       	 menuitem = menuitemDAO.findByMenuMenuId(menuid);
       	for(MenuItem mi:menuitem){
       		if(mi.getItemId()==itemid){
       			if(mi.getItemName()!="")
       			mi.setItemName(items.getItemName());
       			if(items.getItemPrice()!=0)
       			mi.setItemPrice(items.getItemPrice());
       			menuitemDAO.save(menuitem);
           		menuDAO.save(menu);
       			break;
       		}
       	}
       	if(put){
       		break;
       	}
       }
        }
       

    }
    
    @DeleteMapping("/restaurants/{id}/menus")
    public void deleteMenusFromRestaurant(@PathVariable("id") int id) {
        Restaurant rest = restaurantDAO.findOne(id);
        if (rest == null)
            return ;
        List<Menu> menus = menuDAO.findByRestaurantRestaurantId(id);
        menuDAO.delete(menus);
    }

    @DeleteMapping("/restaurants/{id}/menus/{menuid}")
    public void deleteMenus(@PathVariable("id") int id, @PathVariable("menuid") int menuid) {
    	 Restaurant rest = restaurantDAO.findOne(id);
    	 if(rest==null)
    		 return;
    	 List<Menu> menus = menuDAO.findByRestaurantRestaurantId(id);
    	
    	 int i=0;
  
    	 for(Menu m:menus){
    		 if(menuid==m.getMenuId()){
    			 menuDAO.delete(menus.remove(i));
    			 break;
    		 }
    		 i++;
    	 }
    	
    }
    
    @DeleteMapping("/restaurants/{id}/menus/{menuid}/items")
    public void deleteallMenuItems(@PathVariable("id") int id, @PathVariable("menuid") int menuid) {
    	 Restaurant rest = restaurantDAO.findOne(id);
    	 if(rest==null)
    		 return;
    	 List<Menu> menus = menuDAO.findByRestaurantRestaurantId(id);
    	 List<MenuItem> menuitem = new ArrayList<>();
    	 for(Menu m:menus){
    		 if(m.getMenuId()==menuid){
    			 menuitem  = menuitemDAO.findByMenuMenuId(menuid);
    		 	 break;
    		 }
    	 }
    	 
    	 menuitemDAO.delete(menuitem);
    	
    }
    
    @DeleteMapping("/restaurants/{id}/menus/{menuid}/items/{itemid}")
    public void deleteMenuItems(@PathVariable("id") int id, @PathVariable("menuid") int menuid,@PathVariable("itemid") int itemid) {
    	 Restaurant rest = restaurantDAO.findOne(id);
    	 if(rest==null)
    		 return;
    	 List<Menu> menus = menuDAO.findByRestaurantRestaurantId(id);
    	 List<MenuItem> menuitem = new ArrayList<>();
    	 for(Menu m:menus){
    		 if(m.getMenuId()==menuid)
    			 menuitem  = menuitemDAO.findByMenuMenuId(menuid);
    	 }
    	
        int index = 0;
    	for(MenuItem mi:menuitem){
    		if(itemid==mi.getItemId()){
    			menuitemDAO.delete(menuitem.remove(index));
    			break;
    		}
    		index++;
    	}
         //menuitem.add(menuitems);
         //menuitemDAO.save(menuitems);
    	
    }
}