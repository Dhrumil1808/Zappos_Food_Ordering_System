# Zappos_Food_Ordering_API

This is the API for ordering food from various restaurants. This API mainly concentrates on listing all the restaurants with their menus and their menu items.

There are three objects/entities in this API:
 **Restaurant**,
 **Menu**,
 **Menu Items**

Thus, a person can basically lookup for mainly three things in this API-
List of all the restaurants
List of all the menus of a particular restaurant
List of all the menu items in  menu of the restaurant.

All the four APIs namely Get,Post,Put and Delete  are implemented.

Tools and Technologies Used:

The technologies used for developing this API are:
 **Spring Boot Framework**,
 **Maven**,
 **Apache Tomcat**,
 **MySQL**,
 **Redis**

For creating test cases :
 **JUnit**,
 **Mochito**



## REST APIs

### Get all Restaurants

API: http://localhost:8080/restaurants

[
{
"restaurantId": "<restaurant_id>",
"restaurantName": "<restaurant_name>",
menus:[]
},
.......
]


### Get Restaurant by Id

API: http://localhost:8080/restaurants/{restaurantid}

{
"restaurantId": "<restaurant_id>",
"restaurantName": "<restaurant_name>",
menus:[]
}

### Get Menus of a restaurant

API: http://localhost:8080/restaurants/{restaurantid}/menus

[
    {
    "menuName": "<menu_name">",
        "menuId": <menu_id>",
        "items": [
            {
                "itemId": <item_id>,
                "itemName": "<item_name>",
                "itemPrice": <item_price>
            },
           .......
        ]
    }
    ]
 
 
 ### get menuitems of a particular menu of a restaurant
 API: http://localhost:8080/restaurants/{restaurantid}/menus/{menuid}
 
 [
            {
                "itemId": <item_id>,
                "itemName": "<item_name>",
                "itemPrice": <item_price>
            },
           .......
        ]
  

## POST API

### Add a restaurant

API: http://localhost:8080/restaurants

Request Body
{
    "restaurantName": <restaurant_name>
}
    
    
    
