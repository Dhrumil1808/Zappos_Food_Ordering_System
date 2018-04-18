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
 **Mockito**


The whole application is **dockerized** using three dockers:
**Spring Boot Docker**, 
**MySQL Docker**, 
**Redis Docker**

**pom.xml** contains all the dependancies for maven, redis and the docker.

**Dockerfile** is used for dockerizing the spring boot application while the redis and mysql dockers are  directly downloaded from docker hub.

**docker-compose"** is used for running the whole application.

**src/main/resources/application.properties** define parameters for runnning the application in **localhost** as well as in the **docker** .

I have used **Ubuntu 14.04** for running the application on localhost as well as for the docker.

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
 
 ### Add a menu to a particular restaurant
 
 API: http://localhost:8080/restaurants/{restaurantid}/menus
 
 Request Body
{
    "menuName": <menu_name>
}


### Add MenuItems to menu in a Restaurant

API: http://localhost:8080/restaurants/{restaurantid}/menus/{menuid}/items

Request Body
{
	“itemName": <item_name>,
    “itemPrice”:  <item_Price>
}


## Delete API

### Delete all the restaurants

API: http://localhost:8080/restaurants

Return HttpStatus.OK

### Delete restaurant with a particular id

API: http://localhost:8080/restaurants/{restaurantid}

Return HttpStatus.OK

### Delete all the menu and menuitems of a particular Restaurant

API: http://localhost:8080/restaurants/{restaurantid}/menus
    
Return HttpStatus.OK

### Delete particular Menu of a Restaurant

API: http://localhost:8080/restaurants/{restaurantid}/menus/{menuid}

Return HttpStatus.OK

### Delete a particular MenuItem in the menu of a Restaurant

API: http://localhost:8080/restaurants/{restaurantid}/menus/{menuid}/items/{itemid}

Return HttpStatus.OK

## PUT API

### Edit the name of the Restaurant

API: http://localhost:8080/restaurants/{restaurantid}

Request Body
{
    "restaurantName": <restaurant_name>
}
Return HttpStatus.Ok

### Edit the Menu Name in a Restaurant

API: http://localhost:8080/restaurants/{restaurantid}/menus/{menuid}

Request Body
{
    "menuName": <menu_name>
}
Return HttpStatus.Ok

###  Edit the MenuItem in a Menu of the Restaurant

API: http://localhost:8080/restaurants/{restaurantid}/menus/{menuid}/items/{itemid}

Request Body
{
    "itemName": <item_name>,
    “itemPrice”: <item_price>
}

Return HttpStatus.Ok
