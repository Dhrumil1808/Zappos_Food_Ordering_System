package com.system.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Restaurant")
public class Restaurant implements Serializable { 
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="Restaurant_Id")
    private int restaurantId;  
	@Column(name="Restaurant_Name")
    private String restaurantName;
	
	
	public int getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName=restaurantName;
	}
} 