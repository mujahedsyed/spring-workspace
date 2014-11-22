# Spring AOP

### AOP Introduction 
- Why AOP?
  - AOP is very important foundation of Spring and also of Java EE.
  - AOP is used to implement enterprise features like transactions, security. 
  - AOP helps to where to start transactions and which methods to secure - therefore configurable middleware.
  - Simplify Code

- Simplify code using AOP
 - In a typical method without AOP we would need lots of boiler code for tracing and transaction handling, for instance in below method. The Business logic is still not present:
![Alt text](images/img-1.bmp?raw=true "Without AOP")
 - So when you write an aspect you remove all the boiler plate code and convert them to aspect; in this case we would have a Tracing Aspect, Exception Aspect, Transaction Aspect.

- How does AOP Works:
 - Suppose you have a system with lots of classes as shown and you are NOT using AOP than your exception handling code and transactions are spread:
![Alt text](images/img-2.bmp?raw=true "Code Tangling")
 - If you don’t use AOP than there are problems like code tangling, code scattering. So what AOP does is that it will take all Tracing code and put it in a Tracing Aspect, and it will take all transactions and put it in Transactions Aspect and finally all exception handling is put in exception Aspect. This way there is a clear separation between business logic and additional aspect.

- Cross-Cutting Concerns:
 - Tracing, Exception Handling and Transactions are cross-cutting concerns.
 - A lot of classes must implement them, and they can't be implemented in one place if you use object oriented programming only.
 - Aspect oriented programming allows centralized implementation of cross-cutting concerns.



