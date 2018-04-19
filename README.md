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



## Steps for running it on localhost

1) git clone https://github.com/Dhrumil1808/Zappos_Food_Ordering_System.git

2) Import it as an "Existing Maven Project"  in Eclipse or any other IDE.

3)  From the terminal, enter into the repository and enter the command ## "mvn clean install -DskipTests" (for skipping the JUnit Test cases right now)"

4) Create the database in localhost (on mysql workbench) as suggested in the documentation. 

5) Run the Application by running the "Application class" in (src/main/java/com/system/Application.java) as it contains the main method.

6) REST APIs can be tested on Postman and Unit test cases can be runned by running the RestaurantTests.java file which is located in src/test/java/com/system/RestaurantTests.java


## Steps for running it as docker containers
1) git clone https://github.com/Dhrumil1808/Zappos_Food_Ordering_System.git

2) Import it as an "Existing Maven Project"  in Eclipse or any other IDE.

3) Install docker and docker-compose.

4) Install the MySQL docker container
 docker run -d -p 3307:3306 --name zappos  -e MYSQL_ROOT_PASSWORD=root  -e MYSQL_DATABASE=Zappos -e MYSQL_USER=root -MYSQL_PASSWORD=root  mysql:5.6
 
5) Install the redis container
  docker run --name redis -d redis redis-server --appendonly yes
 
 
 6) Create docker network by this command "docker network create my-net"

7) Go to src/main/resources/application.properties
Comment the sql server url for localhost and uncomment the one for the docker. Similarly, comment the spring.redis.host and spring.redis.port for the localhost and uncomment the same for the docker container.

 7)  Run the command from the terminal after entering in the root directory of the project ## "mvn clean install -DskipTests"

8) docker-compose build and then docker-compose up
 
These steps are performed in ## Ubuntu 14.04 in which my docker had a static IP of "172.17.0.1".
