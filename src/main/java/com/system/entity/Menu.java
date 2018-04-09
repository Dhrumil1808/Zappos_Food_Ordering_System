package com.system.entity;



import javax.persistence.*;

import com.fasterxml.jackson.annotation.*;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;



@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name="Menu")
@NoArgsConstructor
public class Menu {

    @GeneratedValue
    @Id
    private int menuId;

    private String menuName;
    
    @JsonBackReference(value="restaurant-menu")
    @ManyToOne
    @JoinColumn(name = "RestaurantId")
    private Restaurant restaurant;

    @JsonManagedReference(value="menu-menuitem")
    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL,orphanRemoval=true)
    private List<MenuItem> items;

    
    public List<MenuItem> getItems() {
		return items;
	}

	public void setItems(List<MenuItem> items) {
		this.items = items;
	}
	
   
    public Restaurant getRestaurant() {
        return restaurant;
    }
 
   

	public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @JsonCreator
    public Menu(@JsonProperty("menuId") String MenuId, @JsonProperty("menuName") String MenuName) {
        this.menuName= MenuName;
       
    }

    

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + menuId +
                ", name='" + menuName + '\'' +
                ", items=" + items.toString() +
                '}';
    }
    
}
