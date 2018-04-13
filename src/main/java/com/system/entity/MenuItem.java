package com.system.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter; 
import org.codehaus.jackson.annotate.JsonCreator;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name="MenuItem")
@NoArgsConstructor
public class MenuItem implements Serializable
{
	/**
	 * 
	 */
	
	/**
	 * 
	 */
	private static final long serialVersionUID =  -3535420909435558948L;

	/**
	 * 
	 */
	

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
    
    
    public MenuItem(int id, String name,int price){
		this.ItemId = id;
		this.ItemName = name;
		this.ItemPrice = price;
	} 
   
    @JsonCreator
    public MenuItem(@JsonProperty("ItemName") String ItemName, @JsonProperty("ItemPrice") int ItemPrice) {
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