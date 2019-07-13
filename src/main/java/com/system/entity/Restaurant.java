package com.system.entity;


import java.io.Serializable;

import java.util.List;
import java.util.Set;

import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter; 

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name="Restaurant")
@NoArgsConstructor
public class Restaurant implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6997499182545244592L;

	/**
	 * 
	 */
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int restaurantId;
	
    private String restaurantName;
 
    @JsonManagedReference(value="restaurant-menu")
    @OneToMany(mappedBy = "restaurant",fetch=FetchType.EAGER,orphanRemoval=true)
    private List<Menu> menus;  

    public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	
	public int getRestaurantId(){
		return restaurantId;
	}	
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	

	public Restaurant(int id, String name){
		this.restaurantId = id;
		this.restaurantName = name;
	}
	
	
	
    @JsonCreator
    public Restaurant(@JsonProperty("name") String RestaurantName) {
      this.restaurantName = RestaurantName;
  
        //System.out.println(this.restaurantName);
       /* if (menus.size() > 0 ) {
            this.menus = menus;
            for (Menu menu : menus)
                menu.setRestaurant(this);
        }*/
        
    } 
    
    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + restaurantId +
                ", name='" + restaurantName + '\'' + menus.toString() +  '}';
    }



	
}
