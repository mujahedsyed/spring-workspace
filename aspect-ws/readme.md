# Spring AOP

## AOP Introduction 
### Why AOP?
  - AOP is very important foundation of Spring and also of Java EE.
  - AOP is used to implement enterprise features like transactions, security. 
  - AOP helps to where to start transactions and which methods to secure - therefore configurable middleware.
  - Simplify Code

#### Simplify code using AOP
 - In a typical method without AOP we would need lots of boiler code for tracing and transaction handling, for instance in below method. The Business logic is still not present:
![Alt text](images/img-1.bmp?raw=true "Without AOP")
 - So when you write an aspect you remove all the boiler plate code and convert them to aspect; in this case we would have a Tracing Aspect, Exception Aspect, Transaction Aspect.

#### How does AOP Works:
 - Suppose you have a system with lots of classes as shown and you are NOT using AOP than your exception handling code and transactions are spread:
![Alt text](images/img-2.bmp?raw=true "Code Tangling")
 - If you don’t use AOP than there are problems like code tangling, code scattering. So what AOP does is that it will take all Tracing code and put it in a Tracing Aspect, and it will take all transactions and put it in Transactions Aspect and finally all exception handling is put in exception Aspect. This way there is a clear separation between business logic and additional aspect.

#### Cross-Cutting Concerns:
 - Tracing, Exception Handling and Transactions are cross-cutting concerns.
 - A lot of classes must implement them, and they can't be implemented in one place if you use object oriented programming only.
 - Aspect oriented programming allows centralized implementation of cross-cutting concerns.
 - Use Spring AOP or AspectJ to weave aspects into the applications.

### First Aspect Example:
 - What is an Aspect?
   - Aspect implements cross-cutting concern in one centralized space of the code base, otherwise cross-cutting concerns are shattered throughout the code.
   - Ultimate goal is to get rid of boiler plate code and focus on business logic.
   - Aspect = Pointcut + Advice
![Alt text](images/img-3.bmp?raw=true "Aspect")

##### Example Tracing:
   - Mark the class as @Component to indicate that it is a spring bean and @Aspect to indicate it is an Aspect. @Before is used to indicate that the method should be executed before the actual method, and the pointcut expression tells which method to apply for:
```java
@Component
@Aspect
public class TracingAspect {

	boolean enteringCalled = false;
	Logger logger = LoggerFactory.getLogger(TracingAspect.class);

	public boolean isEnteringCalled() {
		return enteringCalled;
	}

	@Before("execution(void doSomething())")
	public void entering(JoinPoint joinPoint) {
		enteringCalled = true;
		logger.info("entering ");
	}
}
```

   - In the above code the aspect is called only for void doSomething() method; which isn't very helpful. To call it for all methods use replace them with wild card characters:

```java
   @Before("execution(* *(..))")
	public void entering(JoinPoint joinPoint) {
		enteringCalled = true;
		logger.info("entering ");
	}
```

#### JoinPoint
  - JoinPoints are use to findout which method was called, in the above implementation tracing indicates only that a method was called and doesn’t tell which method was invoked to do this use JoinPoint. Join Point are Point in the control flow of a program.
  - Advices can be presented with information about the join point. Here we adding JoinPoint to the entering method, this parameter will be filled by spring automatically for us:
```java
  @Before("execution(* *(..))")
	public void entering(JoinPoint joinPoint) {
		enteringCalled = true;
		logger.info("entering "
				+ joinPoint.getStaticPart().getSignature().toString());
	}
```

### Enable Aspects in Spring XML Configuration
   - To enable aspects in spring configuration xml file use &lt;context:component-scan base-package=""/&gt; this will turn any class that has spring component annotation marked into a spring bean. Our Aspects must be Spring beans. To enable @AspectJ support with XML based configuration use the aop:aspectj-autoproxy element:

```xml
<aop:aspectj-autoproxy />
<context:component-scan base-package="com.mujahed.aop.simpleaspect" />
```

```java
@Component
@Aspect
public class TracingAspect {
```
   - The annotation will change the @Aspect marked class to Aspect.

### Enable Aspects in Java Configuration
  - To use Spring Java Configuration use should use @ComponentScan(basePackages="") this will scan for spring beans, you also need to add @EnableAspectJAutoProxy this will enable @Aspect annotation.
```java
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.mujahed.aop.simpleaspect")
public class SimpleAspectConfiguration {

}
```

## Introduction and Before Advice

### Advice Deep Dive
* **Types of advices:**
 1. **Before Advice:**  Advice that executes before a join point, but which does not have the ability to prevent execution flow proceeding to the join point (unless it throws an exception).
 2. **After Advice:** Advice to be executed regardless of the means by which a join point exits (normal or exceptional return).
 3. **After Throwing:** Advice to be executed if a method exits by throwing an exception.
 4. **After Returning:** Advice to be executed after a join point completes normally: for example, if a method returns without throwing an exception.
 5. **Around Advice:** Advice that surrounds a join point such as a method invocation. This is the most powerful kind of advice. Around advice can perform custom behavior before and after the method invocation. It is also responsible for choosing whether to proceed to the join point or to shortcut the advised method execution by returning its own return value or throwing an exception.

![Alt text](images/img-7.png?raw=true "Types of advices")

#### Before Advice
 * Executed before the method.
 * Exception prevents method to be executed.
 * Exception is propogated to the caller.
 
```java
	@Before("execution(* *(..))")
	public void entering(JoinPoint joinPoint) {
		enteringCalled = true;
		logger.info("entering "
				+ joinPoint.getStaticPart().getSignature().toString());
	}
```

#### After Advice
 * Executed after the method is executed.
 * Exception could have been thrown...
 * ...or method could have been executed successfully.

 ```java
 	@After("execution(* *(..))")
	public void exiting(JoinPoint joinPoint) {
		afterCalled = true;
		logger.info("exiting called " + joinPoint.getSignature());
		for (Object arg : joinPoint.getArgs()) {
			logger.info("Arg : " + arg);
		}
	}
```

#### After Throwing Advice
 * Executed if a method threw an exception
 * Exception will be propagated to the caller
 * Thrown exception can be accessed in a type safe manner i.e. method only executed if a RuntimeException is thrown and will not be executed if different type of exception is thrown.
 
```java
	@AfterThrowing(pointcut = "execution(* *(..))", throwing = "ex")
	public void logException(RuntimeException ex) {
		afterThrowingCalled = true;
		logger.error("Exception :" + ex);
	}
```

#### After Returning Advice
 * Executed if the method returned successfully, can't change the result, can't change the exception.
 * It will not be called if return type doesn't match.
 * Will not execute if although the return type matches but exception is thrown.
 * The pointcut below indicates the wildcard that any return is accepted but actually the returning will override it to indicate that only string return type is accepted, this is done to access it in type safe manner.

```java
    @AfterReturning(pointcut = "execution(* *(..))", returning = "string")
	public void logResult(String string) {
		afterReturnCalled = true;
		logger.info("result :" + string);
	}
```

#### Around Advice
 * Wraps around the method.
 * Can prevent the original method from being called
 * ... without throwing an exception like the before advice
 * only advice that allows to catch exceptions.
 * only advice that can modify the return value.
 * this is possible as around advice is given current method call.
 * ProceedingJoinPoint is extension of JoinPoint allows you to proceed with the method call, method call proceed() is available. If you dont call proceed on the ProceedJoinPoint than the orginal call won't be executed.
 * Around advice is the most powerful advice, you can use it instead of Before and After but it is complex than BeforeAdvice and After Advice. Therefore it should be used appropriatly.

 ```java
	@Around(value = "execution(* *(..))")
	public Object trace(ProceedingJoinPoint proceedingJoinPoint)
			throws Throwable {
		String methodInformation = proceedingJoinPoint.getStaticPart()
				.getSignature().toString();
		logger.info("Entering :" + methodInformation);
		called = true;

		try {
			return proceedingJoinPoint.proceed();
		} catch (Throwable ex) {
			logger.error("exception in " + methodInformation, ex);
			throw ex;
		} finally {
			logger.info("Exiting :" + methodInformation);
		}
	}
```

## Pointcuts Deep Dive

### Method Execution Pointcuts

 * The code - execution(* hello(*)) indicates the execution of method hello, one parameter of any type, any return type.
 * The code - execution(* hello(..)) indicates the execution of method hello, any number of parameters of any type, any return type.
 * Pointcuts can be applied to packages and classes, example:
 
 ```java
 
 // indicates execution of metod hello in service class and in package com.mujahed with one ints as parameters and one int as return type.
 execution(int com.mujahed.Service.hello(int))
 
 ```
 
 * Wildcards can be used in package and classes as well, for example above code can be written with wildcards as:
 
 ```java
 
 /**
 * Below code indicates:
 * Execution of any method where
 *   - class name ends with Service
 *   - in package com.mujahed or subpackage
 *   - any return type
 *   - any parameters
 **/
 execution(* com.mujahed..*Service.*(..))
 
 ```
 
 * The code - execution(* *.*(..)) indicates execution of any method with any parameters in any class in the default package. Whereas the code - execution(* *..*.*(..)) indicates execution of any method with any parameters in any class but in any package or subpackage.
 
 ![Alt text](images/img-9.png?raw=true "Pointcut Expressions") 
 
 ![Alt text](images/img-8.png?raw=true "Pointcut")
 
 
### Pointcut Annotations

 * Annotations can also be used for defining pointcuts. Example in the code:- 
 
```java
execution(@com.mujahed.Annotation * *(..)) 
```
 This code indicates that the method to qualify must be annotated with com.mujahed.Annotation annotation, note that you need to specify fully qualified class name i.e. incl the package.

 * You can also use pointcut expression that match classes that are annotated with specific annotation, example execution(* (@com.mujahed.Annotation *).*(..))
 
```java
// Only those methods that are annotated with Trace are called.
@Around("execution (@annotation.Trace * *(..))")
public void trace(ProceedingJoinPoint proceedingJP) throws Throwable {

```

### Spring bean names as pointcuts
 * You can also use spring bean names to mention pointcuts. 
 
![Alt text](images/img-10.png?raw=true "bean names Pointcut")

### Pointcuts and Boolean operators
 * you can also use boolean operators to combine pointcuts. Below examples matches any methods in any class in package service or repository.
 
![Alt text](images/img-11.png?raw=true "boolean operators on Pointcut")

 * If you are using same pointcut expression over and over again than you can reuse a pointcut expression. This can be done through @Pointcut annotation
 
```java
 package com.mujahed.pointcuts;

import org.aspectj.lang.annotation.Pointcut;

public class MyPointcuts {

	// traceAnnotated method is always empty, the only purpose of the method is to be annotated.
	@Pointcut("execution(@annotation.Trace * * (..))")
	public void traceAnnotated(){
	}
}
```
 * once the above method is defined you can use this method as a pointcut expression, example:
 
```java

@Around("com.mujahed.pointcuts.MyPointcuts.traceAnnotated()")
public void trace(ProceedingJoinPoint proceedingJP) throws Throwable {

```

### Summary
 ![Alt text](images/img-12.png?raw=true "Summary")
 
### Expressing Architecuture using Spring AOP

#### Architecture problems
 * Problems with current Architecture process is that it is often in documents and most of the time documents are not read, if they are read they are not followed. And if they are followed then there is lot of boilerplate code.
 * AOP can help with such problems, in AOP architecture is defined in the code and developers have to follow it.
 * As an example we can cosider two areas in the code Service and Repository; so lets suppose that we like to add specific behaviours to specific parts of the system like calls to Service should be:
  * traced 
  * and exceptions logged

 and lets suppose that calls to Repository should be:
  * traced
  * performance logged
  * and exceptions logged

 ![Alt text](images/img-13.png?raw=true "Using AOP Properly")
 
 #### Using Annotations to define architecture
 * You can use annotations to define architecture of a system, Spring framework already has annotations for Service, Repository (to indicate that they are service and repository class). Spring dependency Injection will be executed on the classes that are annotated with @Component, @Service, @Repository.
 * In order to define architecture using annotations follow following steps:
 
  * Step 1: Define architecture as pointcuts
```java
  
  public class SystemArchitecture {

	// any method, in any class, any kind of parameters and any return type but the class is maked with @Repository annotation
	@Pointcut("execution(* (@org.springframework.stereotype.Repository *).*(..))")
	public void Repository(){
		
	}
	
	// any method, in any class, any kind of parameters and any return type but the class is maked with @Service annotation
	@Pointcut("execution(* (@org.springframework.stereotype.Service *).*(..))")
	public void Service(){
		
	}
}
```
  * Step 2: Define behavior using Advices, for instance here we are saying exceptions should be logged.
  
  * Step 3: Add advices to correct pointcuts, in below case services and repository i.e. for all classes that are marked with @Repository and @Service annotation.
```java
@Component
@Aspect
public class ExceptionLoggerAspect extends CallTracker {

	Logger LOGGER = LoggerFactory.getLogger(ExceptionLoggerAspect.class);

	@AfterThrowing(pointcut = "SystemArchitecture.Repository() || SystemArchitecture.Service()", throwing = "ex")
	public void logException(Exception ex) {
		trackCall();
		LOGGER.error("Exception ", ex);
	}
}
```
  
#### Using Packages to define architecture
 * You can use packages to define architecture of a system.
 * In order to define architecture using packages in the SystemArchitecture code change the pointcuts to use packages:
 
```java
  
  public class SystemArchitecture {

	// any class in subpackage repository of com.mujahed
	@Pointcut("execution(* com.mujahed..repository.*.*.(..))")
	public void Repository(){
		
	}
	
	// any class in subpackage Service of com.mujahed
	@Pointcut("execution(* com.mujahed..service.*.*.(..))")
	public void Repository(){
		
	}
}
```

### How Aspects are Added to Objects
 * Springframework creates a proxy around original object, and the proxy is passed to any classes invoking the original class. Proxy looks like original objects, the proxy is injected through dependency injection.
 * There are two ways how proxy's are implemented either dynamic proxies (JDK Feature) are used or CGLIB is used.
 * when the other spring beans calls to thinks that it has original object (and not a proxy) so the call is forward to the original object and also to advice.
 
![Alt text](images/img-14.png?raw=true "How aspects are added")

 * when you do a local method call proxies are not called, proxies never called so advice never called.. Original object just calls the method in it. Call never reaches the proxy. Below code is not in transaction:
 
![Alt text](images/img-15.png?raw=true "Local Method call - Proxy not called")

 * If you are dealing with a bean that is implemented through an interface than for proxies to work you should either:
  * all methods in the class implementing the interface should be present in the interface 
  * in @EnableAsjectJAutoProxy(proxyTargetClass=true)
 * CGLIB allows to create dynamic subclass. Subclass implements the proxy. CGLIB is used if no interfaces are implemented, or set proxy-target-class to true. 
 * Another way to implement AOP is Dynamic Proxy, this feature is available in JDK. This is for interfaces only. Dynamic proxy is always implementation of interface.
 
#### Limitations of the proxy-based AOP Model
 * Work only on public methods.
 * Works only on methods  from outside, no internal method call.
 * Spring Depdency injection makes AOP Transparent.
 
### Spring AOP vs AspectJ
 * AspectJ is faster and powerful than Spring AOP.
 * Spring AOP uses the same syntax as Aspectj. All the annotations like @Aspect, @Before, @After, @Pointcut, pointcut expression will work in aspectj except bean pointcut expression. Bean pointcut expression will not work in aspectj is because it is depedent on spring depedency injection.
 * Aspects are applied differently in Aspectj. Aspectj uses bytecode weaving i.e. classes and aspect are both woven into bytecode. Weaving might be done when the classes are loaded or when compiled.

![Alt text](images/img-16.png?raw=true "Byte code weaving")

#### Load time weaving

