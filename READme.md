Description
Set up the backend for an online store to buy items. Users can register and login to add items to their cart. Then they can place orders. Registered users are are stored in the Users table. Items that users can buy are stored in the Items table. User's carts are stored in the Cart table. Users order's are stored in the Orders table.

Features Implemented
Register - register new users
Login/Logout - login and logout users
Get Users - get all users and their cart
Get Items - gets all items that are available in store
Add Item to Cart - adds selected item to cart
Technologies Used
Spring Framework: Spring Boot, Spring Data

Register: POST http://localhost:8084/p1/register { "email": "user email", "password": "password" }

View all Products :
http://ec2-52-73-113-113.compute-1.amazonaws.com:8084/product
Add a product :
POST - ec2-52-73-113-113.compute-1.amazonaws.com:8084/product
BODY :  {
"productId": 12,
"productName": "NewLakme",
"qoh": 123,
"price": 1999
}
AWS

