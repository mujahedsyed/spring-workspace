# Spring Introduction

## Introduction
### Why Spring?
  - Simplify Code

### Hello World Example:
 
## Bean's Lifecycle
![Alt text](images/lifecycle-1.png?raw=true "Spring Bean Lifecycle")

## Scoping Beans
By default, all beans created in the spring application context are created as singletons. Spring defines several scopes:

*Singleton* - One instance of the bean is created for the entire application
*Prototype* - One instance of the bean is created every time the bean is injected into or retrieved from Spring application context.
*Sessions* - In a web app, one instance per session.
*Request* - In a web app, one instance per request.

### defining scope with annotation
To mark the scope of a bean using annotation, @Scope should be used in conjunction with either @Component or @Bean:

```java
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MyBean() {
 ...
}

### defining scope in xml
```xml
<bean id="myBean" class="com.mycompany.MyBean" scope="prototype"/>

### Request and Session Scopes **TODO**

```
