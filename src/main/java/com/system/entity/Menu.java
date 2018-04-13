package com.system.entity;




import javax.persistence.*;

import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter; 

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;



@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name="Menu")
@NoArgsConstructor
public class Menu implements Serializable {

    /**
	 * 
	 */
	

	/**
	 * 
	 */
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3297908590148515382L;

	/**
	 * 
	 */
	
	@GeneratedValue
    @Id
    private int menuId;

    public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}



	private String menuName;
    
    @JsonBackReference(value="restaurant-menu")
    @ManyToOne
    @JoinColumn(name = "RestaurantId")
    private Restaurant restaurant;

    @JsonManagedReference(value="menu-menuitem")
    @OneToMany(mappedBy = "menu", cascade = CascadeType.REMOVE,fetch=FetchType.EAGER,orphanRemoval=true)
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
	
	public Menu(int id, String name){
		this.menuId = id;
		this.menuName = name;
	}

    @JsonCreator
    public Menu(@JsonProperty("menuName") String MenuName) {
        this.menuName= MenuName;
        this.restaurant=restaurant;
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
