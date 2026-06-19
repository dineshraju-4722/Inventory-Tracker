# CODTECH INTERNID - CITS3929

# Inventory Tracker

## Description

Inventory Tracker is a full-stack inventory management application built using Spring Boot, Spring Security, PostgreSQL, and React. The system provides secure authentication and role-based authorization, allowing administrators to manage inventory data while users can browse products and maintain their shopping carts.

The application follows a RESTful architecture with a Spring Boot backend and a React Vite frontend. JWT-based authentication is implemented to secure APIs and control access based on user roles.

## Features

### Authentication & Authorization

* User Registration and Login
* JWT Authentication
* Role-Based Access Control (Admin/User)
* Secure API Endpoints using Spring Security

### Admin Features

* Manage Categories (Create, Update, Delete, View)
* Manage Products (Create, Update, Delete, View)
* Manage Suppliers (Create, Update, Delete, View)
* Assign a Supplier to a Product
* View Inventory Information

### User Features

* Browse Available Products
* View Product Details
* Add Products to Cart
* Update Product Quantity in Cart
* Remove Products from Cart
* View Personal Shopping Cart

### Product Management

* Each Product is associated with exactly one Supplier
* Products belong to Categories
* Inventory information is stored and managed in PostgreSQL

## Tech Stack

### Backend

* Java
* Spring Boot
* Spring Security
* Spring Data JPA
* JWT Authentication
* PostgreSQL

### Frontend

* React
* Vite
* Axios
* React Router

### Database

* PostgreSQL

## Architecture

* Frontend communicates with Backend using REST APIs.
* JWT tokens are used for secure authentication.
* Spring Security handles authorization and endpoint protection.
* PostgreSQL stores users, roles, products, categories, suppliers, and cart information.

## Future Enhancements

* Product Search and Filtering
* Order Management
* Inventory Analytics Dashboard
* Product Image Uploads
* Supplier Performance Reports
* Email Notifications
