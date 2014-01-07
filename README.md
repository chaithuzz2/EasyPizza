EasyPizza
=========

A simple app for lazy pizza lovers - powered by paypal

The Basic Idea
---------------
---------------

Everybody Loves Pizza. That's why a lot of us order and get a home Delivery.  The following three things might be the ones which we enjoy the least in the whole experience.
1) Not enough cash in pocket and so I need to run to the nearest ATM to pay the delivery guy.
2) The problem with tendering exact change to the guy.
3) Getting the pizza delivered late and not able to express our dissatisfaction .
With the power of an android smartphone and PayPal's mobile payment API I made a simple app to demonstrate the case of paying for a pizza delivery .

Flow of the App
------------------
------------------

1. The app has two modes , Merchant and customer
2. First the merchant upon recieving an order ,fills out a form on the app with a unique code name and order details including the Delivery Guy's mobile number in it and uploads it to the server.
3. The Delivery guy takes the pizza to the place.
4. The customer takes his smartphone enters in to the customer mode and types the unique code name 
5. The order Details are recieved from the server and shown on the app.
6. Here comes the main features . There will be two fields namely time penalty and tip
7. If you liked the pizza delivery experience you may want to tip the delivery guy for getting it fast. So there is a edit box in which you may write the amount to tip him.
8. If the delivery guy didnt do the service you expected and the time stamp exceeds the allowed duration . The app Automatically cuts your price according to the established rules of late delivery removing the question of arguing about the time taken which makes the pizza delivery system a little efficient and you are happy too.
9. After that total amount is shown and if you press the pay button you will be able to login to paypal and pay the amount.
10. Upon payment , the delivery guy who is with you gets an SMS which says your payment has been accepted and he leaves.
11. You get your pizza and everyone is happy :)
 
Problems solved
---------------
---------------

1) Didn't have to run to ATM
2) NO Change problem
3) You pay according to the delivery time
4) You can tip

Requirements
------------
------------
A Paypal account
A Smart Phone

Technologied used 
------------------
------------------
Android : Minimum - HoneyComb
PayPal-SDK
Sandbox accounts for testing 
Used Device for testing(Works best on Samsung Galaxy Tab 2 GT-P3100)









