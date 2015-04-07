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
```

### defining scope in xml
```xml
<bean id="myBean" class="com.mycompany.MyBean" scope="prototype"/>
```

### Request and Session Scopes **TODO** 


### Teach your bean new Tricks

BeanPostProcessor is used by @Autowired annotation. At runtime if @Autowired is present the container will look for the appropriate value and it will inject the dependency.

![Alt text](images/teach-tricks.png?raw=true "Spring Bean Tricks")

BeanPostProcessor doesn't have the capability to create new beans dynamically, and it doesn't have ability to change configuration metadata of the object before its created.You can change the metadata after it's created.

BeanFactoryPostProcessor is slightly more powerful.  
![Alt text](images/beanfactorypostprocessor.png?raw=true "BeanFactoryPostProcessor")