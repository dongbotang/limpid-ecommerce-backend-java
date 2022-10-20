# Getting Started

### Build & Run
This is a Spring boot application built with Maven, it can be run the main method in ShoppingCartApplication to start the backend server.

The database schema and initial data will be automatically created and populated while the application is starting.
THe data is stored in memory H2 Database for demo purpose or uncomment Mysql configuration and comment H2 configuration in application.properties.

### Backend APIs
* http://localhost:8080/items - Query all items
* http://localhost:8080/item/{productId} - Query item with productId=1
* http://localhost:8080/cart/{orderId} - List items in shopping cart
* http://localhost:8080/cart/add-item - Add an item to shopping cart, needs to set payload with orderId, productId, quantity, example playload `{ orderId: 1, productId: 1, quantity:1, note:"This is note." }` 

### Access H2 Database via browser
* Url: http://localhost:8080/h2-console/
* Username: sa
* Password: password
* Note: THe username and password is configured in application.properties

### Project structure
In package com.square.shoppingcart

* controller - for REST Controller
* model/dto - data models used for transfer data to controllers
* model/entity - JPA standard entities 
* service - for business functionalities

### Library dependencies
The libraries are pre-configured in pom.xml, it does not need to do anything with them.
* Spring boot dev tool
* Spring Web
* REST Repository
* Spring data JPA
* H2 Database