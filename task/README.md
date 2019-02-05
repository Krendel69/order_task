Credentials for enter

Login/Password:
Executor/Executor 
Customer/Customer
Customer2/Customer2

Executor has role ROLE_EXECUTOR and can:
 - Get list of services
 - Get list of all orders
 - Add services
 - Remove service
 - Process orders

Customer has role ROLE_CUSTOMER and can:
 - Get list of services
 - Create order by service
 - Get list of his orders
 
 This task realize logic for just one executor and some customers. If we want to add some executor, it's need to think about new OrderStatus of orders such as OrderStatus.IN_PROGRESS. That's need for just one executor can process the order at a time 