package com.system.entity;


import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Data
@Table(name="Restaurant")
@NoArgsConstructor
public class Restaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int restaurantId;

    private String restaurantName;
 
    @JsonManagedReference
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Menu> menus;  
   
    
    public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	
    @JsonCreator
    public Restaurant(@JsonProperty("id") int id,@JsonProperty("name") String RestaurantName, @JsonProperty("menus") List<Menu> menus) {
        this.restaurantName = RestaurantName;
        if (menus.size() > 0 ) {
            this.menus = menus;
            for (Menu menu : menus)
                menu.setRestaurant(this);
        }
        
    }
    
	public int getRestaurantId() {
		// TODO Auto-generated method stub
		return restaurantId;
	}

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + restaurantId +
                ", name='" + restaurantName + '\'' + menus.toString() +  '}';
    }

	
}
