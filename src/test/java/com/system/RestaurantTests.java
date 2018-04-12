package com.system;

import org.junit.runner.RunWith;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.controllers.RestaurantController;
import com.system.dao.MenuDAO;
import com.system.dao.MenuItemDAO;
import com.system.dao.RestaurantDAO;
import com.system.entity.Menu;
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

    /*	mockMvc.perform(MockMvcRequestBuilders.get("/restaurants").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$", hasSize(9))).andDo(print());*/
    	
    	List<Restaurant> restaurant = new ArrayList<Restaurant>();
		restaurant.add(new Restaurant(30,"Subway"));
		restaurant.add(new Restaurant(31,"La Victoria"));
		restaurant.add(new Restaurant(32,"Old Wagon"));
		Mockito.when(
				restaurantcontroller.getRestaurants()).thenReturn(restaurant);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/restaurants").accept(
				MediaType.APPLICATION_JSON);

		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println("####################################");
		System.out.println("result " + result.getResponse().getContentAsString());
		System.out.println("####################################");
		
		String expected = "[{\"restaurantId\":30,\"restaurantName\":\"Subway\"},{\"restaurantId\":31,\"restaurantName\":\"La Victoria\"},{\"restaurantId\":32,\"restaurantName\":\"Old Wagon\"}]";
		 
		String actual = result.getResponse().getContentAsString();

		
		assertEquals(expected, actual);        

        
    }

    @Test
    public void getRestaurantById() throws Exception {

    	Restaurant restaurant = new Restaurant(21,"McDonald");
    	Mockito.when(
				restaurantcontroller.findRestaurantById(21)).thenReturn(restaurant);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/restaurants/21").accept(
				MediaType.APPLICATION_JSON);

		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println("####################################");
		System.out.println("result " + result.getResponse().getContentAsString());
		System.out.println("####################################");
		
		String expected = "{\"restaurantId\":21,\"restaurantName\":\"McDonald\"}";
		 
		String actual = result.getResponse().getContentAsString();

		assertEquals(expected, actual);        
    }
    
	 
  
	@Test
	public void addRestaurants() throws Exception {
		Restaurant restaurant = new Restaurant(28,"Subway");
		
		Mockito.mock(RestaurantController.class).addRestaurants(restaurant);
		String restaurantdetails="{\"restaurantId\": 28, \"restaurantName\":\"Subway\"}";

		// Send course as body to /students/Student1/courses
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
		
		//System.out.println(response.getHeader(HttpHeaders.LOCATION));
	}
	
	@Test
    public void getRestaurantMenuById() throws Exception {

    	Restaurant restaurant = new Restaurant(21,"McDonald");
    	Menu menu = new Menu(22,"Breakfast");
    	
    	Mockito.when(
				restaurantcontroller.findRestaurantById(21)).thenReturn(restaurant);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/restaurants/21").accept(
				MediaType.APPLICATION_JSON);

		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println("####################################");
		System.out.println("result " + result.getResponse().getContentAsString());
		System.out.println("####################################");
		
		String expected = "{\"restaurantId\":21,\"restaurantName\":\"McDonald\"}";
		 
		String actual = result.getResponse().getContentAsString();

		assertEquals(expected, actual);        
    }
    

  

}
