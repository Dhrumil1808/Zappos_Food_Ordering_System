package com.system.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name="MenuItem")
@NoArgsConstructor
public class MenuItem {

	
    @Id
    @GeneratedValue
    private int ItemId;

    private String ItemName;

    private Double ItemPrice;
    
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "MenuId")
    private Menu menu;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "RestaurantId")
    private Restaurant restaurant;
    
    private Menu getMenu(){
    	return menu;
    }
    
    public void setMenu(Menu menu) {
		// TODO Auto-generated method stub
		this.menu = menu;
	}
    
    
    private Restaurant getRestaurant(){
    	return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
		// TODO Auto-generated method stub
		this.restaurant=restaurant;
	}
    
    
    
    public MenuItem(@JsonProperty("name") String ItemName, @JsonProperty("price") Double ItemPrice) {
        this.ItemName = ItemName;
        this.ItemPrice = ItemPrice;
    }
    
    public MenuItem(@JsonProperty("name") String ItemName, @JsonProperty("price") Double ItemPrice,@JsonProperty("restaurant") Restaurant restaurant,@JsonProperty("menu") Menu menu) {
        this.ItemName = ItemName;
        this.ItemPrice = ItemPrice;
        this.restaurant=restaurant;
        this.menu=menu;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "id=" + ItemId +
                ", name='" + ItemName + '\'' +
                ", price=" + ItemPrice +
                '}';
    }
	
}