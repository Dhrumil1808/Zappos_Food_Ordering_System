package com.system.entity;
import com.fasterxml.jackson.annotation.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

import java.util.List;

@Entity
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name="Menu")
@NoArgsConstructor
public class Menu {

    @GeneratedValue
    @Id
    private int menuId;

    private String menuName;
    
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "RestaurantId")
    private Restaurant restaurant;

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
    public Menu(@JsonProperty("name") String MenuName) {
        this.menuName= MenuName;
    }

    public Menu(@JsonProperty("name") String MenuName,@JsonProperty("restaurant") Restaurant restaurant) {
        this.menuName= MenuName;
        this.restaurant = restaurant;
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
