package com.system;


import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.controllers.RestaurantController;
import com.system.dao.MenuDAO;
import com.system.dao.MenuItemDAO;
import com.system.dao.RestaurantDAO;
import com.system.entity.Menu;
import com.system.entity.MenuItem;
import com.system.entity.Restaurant;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RestaurantTests {
	
	 @Autowired
	 private MockMvc mockMvc;

	 public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	 
	 @MockBean
	 private RestaurantController restaurantcontroller;
	 
	 @Autowired
	 private RestaurantDAO restaurantDAO;
	 
	 @Autowired
	 private MenuDAO menuDAO;
	
	 @Autowired
	 private MenuItemDAO menuitemDAO;
	 

    @Test
    public void getAllRestaurants() throws Exception {

    	List<Restaurant> restaurant = restaurantDAO.findAll();
    	List<Restaurant> rest =new ArrayList<Restaurant>(restaurant);
    	
    	
    	for(Restaurant r: rest){
    		System.out.println(r.getRestaurantId() + " "+ r.getRestaurantName());
    	}    	
       	
    	Mockito.when(restaurantcontroller.getRestaurants()).thenReturn(rest);
	
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/restaurants").accept(MediaType.APPLICATION_JSON);

		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println("####################################");
		System.out.println("result " + result.getResponse().getContentAsString());
		System.out.println("####################################");
		
		String expected ="[]";
		 
		String actual = result.getResponse().getContentAsString().replaceAll("\"","");
		System.out.println(actual);
		
		assertNotNull(actual);

		assertEquals(expected,actual);        

		 
    }

    @Test
    public void getRestaurantById() throws Exception {

    	 Restaurant rest = restaurantDAO.findOne(21);
    	 
    	 if(rest==null)
    		 return;
    	 
        Mockito.when(
				restaurantcontroller.findRestaurantById(21)).thenReturn(new Restaurant(rest.getRestaurantId(),rest.getRestaurantName()));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/restaurants/21").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println("####################################");
		System.out.println("result " + result.getResponse().getContentAsString());
		System.out.println("####################################");
		
		String expected = "";
		 
		String actual = result.getResponse().getContentAsString();

		assertNotNull(actual);
		
		assertEquals(expected, actual);        

		
		
		    }
    
	 
  
	@Test
	public void addRestaurants() throws Exception {
		Restaurant restaurant = new Restaurant("Subway");
		
		Mockito.mock(RestaurantController.class).addRestaurants(restaurant);
		String restaurantdetails="{\"restaurantName\":\"Subway\"}";

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/restaurants")
				.accept(MediaType.APPLICATION_JSON).content(restaurantdetails)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		//System.out.println(response.getContentAsString());
		System.out.println("###################");
		System.out.println(response.getStatus());
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		//restaurantDAO.save(restaurant);
		
		//System.out.println(response.getHeader(HttpHeaders.LOCATION));
	}
	
	@Test
    public void getAllRestaurantMenuItems() throws Exception {

    	int variable = 21;
    	List<Menu> menus = new ArrayList<Menu>(); 
	    menus =  menuDAO.findByRestaurantRestaurantId(variable);
    	
	    Mockito.when(
				restaurantcontroller.getMenus(variable)).thenReturn(menus);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/restaurants/21/menus").accept(MediaType.APPLICATION_JSON);

		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println("####################################");
		System.out.println("result " + result.getResponse().getContentAsString());
		System.out.println("####################################");
		
		String expected ="[]";
		 
		String actual = result.getResponse().getContentAsString().replaceAll("\"","");
		System.out.println(actual);
		
		assertNotNull(actual);
		assertEquals(expected, actual);        
    }
	
	@Test
    public void getMenuItemsFromMenu() throws Exception {
		
		int restid= 21;
		int menuid = 7;
        
        List<MenuItem> menuitem = menuitemDAO.findByMenuMenuId(menuid);
      
   		Mockito.when(
			restaurantcontroller.getMenuItemsFromMenu(restid, menuid)).thenReturn(menuitem);

   		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
			"/restaurants/21/menus/7").accept(MediaType.APPLICATION_JSON);

	
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println("####################################");
		System.out.println("result " + result.getResponse().getContentAsString());
		System.out.println("####################################");
		
		String expected = "[]";
		 
		String actual = result.getResponse().getContentAsString().replaceAll("\"","");
		//System.out.println(actual);
		assertNotNull(actual);
		assertEquals(expected,actual);        
        
    }
	
	@Test
    public void addMenustoRestaurant() throws Exception {
		int restid=21;
        Restaurant rest = restaurantDAO.findOne(restid);
        if (rest == null)
            return ;

        List<Menu> menu = menuDAO.findByRestaurantRestaurantId(restid);
        Menu menus = new Menu("Drinks");
        Menu nmenu = new Menu(menus.getMenuName());
        nmenu.setRestaurant(rest);
        menu.add(nmenu);
        
        Mockito.mock(RestaurantController.class).addMenus(restid, menus);
		String restaurantdetails="{\"restaurantName\":\"Subway\"}";

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/restaurants/21/menus")
				.accept(MediaType.APPLICATION_JSON).content(restaurantdetails)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		//System.out.println(response.getContentAsString());
		System.out.println("###################");
		System.out.println(response.getStatus());
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		//restaurantDAO.save(restaurant);
		
		//System.out.println(response.getHeader(HttpHeaders.LOCATION));
        //menuDAO.save(menu);
    }
		
	@Test
	public void addMenuItemstoRestaurant() throws Exception {
		        
		int restid=15;
		int menuid = 6;
		Restaurant rest = restaurantDAO.findOne(restid);
   
		if(rest==null)
			return;
	   List<Menu> menu = menuDAO.findByRestaurantRestaurantId(restid);
	     
	   List<MenuItem> menuitem = menuitemDAO.findByMenuMenuId(menuid);
	   MenuItem menuitems =  new MenuItem("Grill Sandwhich",10);
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
       Mockito.mock(RestaurantController.class).addMenuItems(restid, menuid, menuitems);
	
   		String restaurantdetails="{\"itemName\":\"Grill Sandwhich\",\"itemPrice\": 10}";
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/restaurants/15/menus/6/items")
				.accept(MediaType.APPLICATION_JSON).content(restaurantdetails)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		//System.out.println(response.getContentAsString());
		System.out.println("###################");
		System.out.println(response.getStatus());
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		menuitem.add(menuitems);
	    menuitemDAO.save(menuitems);
    }
		    

	    @Test
	    public void deleteRestaurantById() throws Exception {
	    	int restid=43;
	        
             Mockito.mock(RestaurantController.class).deleteById(restid);
			
		   	String restaurantdetails="[]";
			RequestBuilder requestBuilder = MockMvcRequestBuilders
						.delete("/restaurants/"+restid)
						.accept(MediaType.APPLICATION_JSON).content(restaurantdetails)
						.contentType(MediaType.APPLICATION_JSON);

				MvcResult result = mockMvc.perform(requestBuilder).andReturn();

				String expected="";
				MockHttpServletResponse response = result.getResponse();
				//System.out.println(response.getContentAsString());
				System.out.println("###################");
				System.out.println(response.getStatus());
			assertNotNull(response.getContentAsString());
			String actual = response.getContentAsString();
			System.out.println(actual);
			assertEquals(expected,actual);
			//restaurantDAO.delete(restid);
	    }

    @Test
    public void deleteMenusFromRestaurant() throws Exception {
    	
	    	int restid = 44;
	        Restaurant rest = restaurantDAO.findOne(restid);
	        if (rest == null)
	            return ;
	        List<Menu> menus = menuDAO.findByRestaurantRestaurantId(restid);
	        
	        if(menus==null)
	        	return;
	        Mockito.mock(RestaurantController.class).deleteMenusFromRestaurant(restid);
			
		   	String restaurantdetails="[]";
			RequestBuilder requestBuilder = MockMvcRequestBuilders
						.delete("/restaurants/"+restid+"/menus")
						.accept(MediaType.APPLICATION_JSON).content(restaurantdetails)
						.contentType(MediaType.APPLICATION_JSON);

			MvcResult result = mockMvc.perform(requestBuilder).andReturn();

			String expected="";
			MockHttpServletResponse response = result.getResponse();
			//System.out.println(response.getContentAsString());
			System.out.println("###################");
			System.out.println(response.getStatus());
			assertNotNull(response.getContentAsString());
			String actual = response.getContentAsString();
			System.out.println(actual);
			assertEquals(expected,actual);
			//restaurantDAO.delete(restid);
	        menuDAO.delete(menus);
	    }

    @Test
    public void deleteMenuByIdFromRestaurant() throws Exception {
    	
    	int restid=44;
    	int menuid = 19;
    	 Restaurant rest = restaurantDAO.findOne(restid);
    	 if(rest==null)
    		 return;
    	 List<Menu> menus = menuDAO.findByRestaurantRestaurantId(restid);
    	
    	
    	 int i=0;
  
    	 Mockito.mock(RestaurantController.class).deleteMenusFromRestaurant(restid);
			
    	 String restaurantdetails="[]";
		 RequestBuilder requestBuilder = MockMvcRequestBuilders
						.delete("/restaurants/"+restid+"/menus"+menuid)
						.accept(MediaType.APPLICATION_JSON).content(restaurantdetails)
						.contentType(MediaType.APPLICATION_JSON);
	
		 MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	
		 String expected="";
		 MockHttpServletResponse response = result.getResponse();
		//System.out.println(response.getContentAsString());
		 System.out.println("###################");
		 System.out.println(response.getStatus());
		 assertNotNull(response.getContentAsString());
		 String actual = response.getContentAsString();
		 System.out.println(actual);
		 assertEquals(expected,actual);
			
    	 for(Menu m:menus){
    		 if(menuid==m.getMenuId()){
    			 menuDAO.delete(menus.remove(i));
    			 break;
    		 }
    		 i++;
    	 }
    	
    }
	    
    @Test
    public void deleteallMenuItemsFromRestaurant() throws Exception {
    	int restid=44;
    	int menuid=18;
    	Restaurant rest = restaurantDAO.findOne(restid);
    	 if(rest==null)
    		 return;
    	 List<Menu> menus = menuDAO.findByRestaurantRestaurantId(restid);
    	 List<MenuItem> menuitem = new ArrayList<>();
    	 for(Menu m:menus){
    		 if(m.getMenuId()==menuid){
    			 menuitem  = menuitemDAO.findByMenuMenuId(menuid);
    		 	 break;
    		 }
    	 }
    	 Mockito.mock(RestaurantController.class).deleteallMenuItems(restid, menuid);
			
	   	String restaurantdetails="[]";
		RequestBuilder requestBuilder = MockMvcRequestBuilders
					.delete("/restaurants/"+restid+"/menus"+menuid+"/items")
					.accept(MediaType.APPLICATION_JSON).content(restaurantdetails)
					.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expected="";
		MockHttpServletResponse response = result.getResponse();
		//System.out.println(response.getContentAsString());
		System.out.println("###################");
		System.out.println(response.getStatus());
		assertNotNull(response.getContentAsString());
		String actual = response.getContentAsString();
		System.out.println(actual);
		assertEquals(expected,actual);	 
    	 
    	 menuitemDAO.delete(menuitem);
    	
    }
	    
    @Test
    public void deleteMenuItems() throws Exception {
    	int restid=44;
    	int menuid=21;
    	int itemid=21;
    	 Restaurant rest = restaurantDAO.findOne(restid);
    	 if(rest==null)
    		 return;
    	 List<Menu> menus = menuDAO.findByRestaurantRestaurantId(restid);
    	 List<MenuItem> menuitem = new ArrayList<>();
    	 for(Menu m:menus){
    		 if(m.getMenuId()==menuid)
    			 menuitem  = menuitemDAO.findByMenuMenuId(menuid);
    	 }
    	
	    int index = 0;
	    Mockito.mock(RestaurantController.class).deleteMenuItems(restid, menuid, itemid);
		
	   	String restaurantdetails="[]";
		RequestBuilder requestBuilder = MockMvcRequestBuilders
					.delete("/restaurants/"+restid+"/menus"+menuid+"/items"+itemid)
					.accept(MediaType.APPLICATION_JSON).content(restaurantdetails)
					.contentType(MediaType.APPLICATION_JSON);
	
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	
		String expected="";
		MockHttpServletResponse response = result.getResponse();
		//System.out.println(response.getContentAsString());
		System.out.println("###################");
		System.out.println(response.getStatus());
		assertNotNull(response.getContentAsString());
		String actual = response.getContentAsString();
		System.out.println(actual);
		assertEquals(expected,actual);	 
	 
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

    @Test
    public void editRestaurantName() throws Exception {
	
    	int restid=44;
    	
    	Restaurant res =new Restaurant("Punjabi restaurant");
		Restaurant rest = restaurantDAO.findOne(restid);
		if(rest==null){
			return;
		}
		Mockito.mock(RestaurantController.class).editRestaurants(restid, res);
		String restaurantdetails="{\"restaurantName\":\"Punjabi restaurant\"}";

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/restaurants/"+restid)
				.accept(MediaType.APPLICATION_JSON).content(restaurantdetails)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		//System.out.println(response.getContentAsString());
		System.out.println("###################");
		//System.out.println(response.getStatus());
		
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		restaurantDAO.save(rest);
    }

    @Test
    public void editMenus() throws Exception {
    	int restid=44;
    	int menuid =23;
        Restaurant rest = restaurantDAO.findOne(restid);
        if (rest == null)
            return ;
        Menu menus = new Menu("Lunch");
        List<Menu> menu = menuDAO.findByRestaurantRestaurantId(restid);
        for(Menu m:menu){
        	if(menuid==m.getMenuId()){
        		m.setMenuName(menus.getMenuName());
        	}
        }
        
        Mockito.mock(RestaurantController.class).editMenus(restid, menus, menuid);
		String restaurantdetails="{\"menuName\":\"Lunch\"}";

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/restaurants/"+restid+"/menus/"+menuid)
				.accept(MediaType.APPLICATION_JSON).content(restaurantdetails)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println("###################");
		System.out.println(response.getStatus());
		
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	    menuDAO.save(menu);
	    }
	    
    @Test
    public void editMenuItems() throws Exception {
	
    	int restid=44;
    	int menuid=24;
    	int itemid=26;
        Restaurant rest = restaurantDAO.findOne(restid);
        if (rest == null)
            return ;
        
        Menu menus = new Menu(24,"Dinner");
        List<Menu> menu = menuDAO.findByRestaurantRestaurantId(restid);
        List<MenuItem> menuitem = new ArrayList<>();
        MenuItem items=new MenuItem("Double layered burrito", 30);
       
       	boolean put = false;
       
       	Mockito.mock(RestaurantController.class).editMenus(restid, menus, menuid);
       	String restaurantdetails="{\"itemName\":\"Double layered burrito\",\"itemPrice\": 30}";

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/restaurants/"+restid+"/menus/"+menuid+"/items/" + itemid)
				.accept(MediaType.APPLICATION_JSON).content(restaurantdetails)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	
		MockHttpServletResponse response = result.getResponse();
		
		System.out.println("###################");
		System.out.println(response.getStatus());
		
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	
		for(Menu m:menu){
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
}
