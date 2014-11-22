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
 
- Use Spring AOP or AspectJ to weave aspects into the applications.

- First Aspect Example:
 - What is an Aspect?
   - Aspect implements cross-cutting concern in one centralized space of the code base, otherwise cross-cutting concerns are shattered throughout the code.
   - Ultimate goal is to get rid of boiler plate code and focus on business logic.
   - Aspect = Pointcut + Advice
![Alt text](images/img-3.bmp?raw=true "Aspect")

 - Example Tracing:
  - Mark the class as @Component to indicate that it is a spring bean and @Aspect to indicate it is an Aspect. @Before is used to indicate that the method should be executed before the actual method, and the pointcut expression tells which method to apply for: 
    
	![Alt text](images/img-4.bmp?raw=true "Aspect Example")
  - In the above code the aspect is called only for <span style="color:blue; font-family:Consolas; font-size:2em;">void doSomething()</span> method; which isn't very helpful. To call it for all methods use replace them with wild card characters: 
![Alt text](images/img-5.bmp?raw=true "WildCard")
