package com.system.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonCreator;

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

    private int ItemPrice;
	
    public int getItemId() {
		return ItemId;
	}

	public void setItemId(int itemId) {
		ItemId = itemId;
	}

	public String getItemName() {
		return ItemName;
	}

	public void setItemName(String itemName) {
		ItemName = itemName;
	}

	public int getItemPrice() {
		return ItemPrice;
	}

	public void setItemPrice(int itemPrice) {
		ItemPrice = itemPrice;
	}


    
    @JsonBackReference(value="menu-menuitem")
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
    
    
   
    @JsonCreator
    public MenuItem(@JsonProperty("ItemId") int id, @JsonProperty("ItemName") String ItemName, @JsonProperty("ItemPrice") int ItemPrice) {
        this.ItemName = ItemName;
        this.ItemPrice = ItemPrice;
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