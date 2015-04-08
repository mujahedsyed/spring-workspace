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


## Teach your bean new Tricks

A **BeanPostProcessor** gives you a chance to process an instance of a bean created by the IoC container after it's instantiation and then again after the initialization lifecycle event has occurred on the instance. You could use this to process fields that were set, perform validation on a bean, or even look up values from a remote resource to set on the bean as defaults.

BeanPostProcessors and any beans they depend on are instantiated before any other beans in the container. After they are instantiated and ordered, they are used to process all the other beans as they are instantiated by the IoC container. Spring's different AOP proxies for caching, transactions, etc. are all applied by BeanPostProcessors. So, any BeanPostProcessor you create isn't eligible for AOP proxies. Since AOP proxies are applied this way, it's possible an AOP proxy may not yet have been applied to the instance so care should be taken if this will affect any post processing being done.

**BeanPostProcessor** is used by @Autowired annotation. At runtime if @Autowired is present the container will look for the appropriate value and it will inject the dependency.

![Alt text](images/teach-tricks.png?raw=true "Spring Bean Tricks")

BeanPostProcessor doesn't have the capability to create new beans dynamically, and it doesn't have ability to change configuration metadata of the object before its created.You can change the metadata after it's created.

- BeanFactoryPostProcessor is slightly more powerful. This can be useful if you want to make sure certain frameworks are available, check certain constraints are available like auditing data etc.

 - A BeanFactoryPostProcessor lets you modify the actual bean definition instead of the instance as it's created. The PropertyResourceConfigurer and PropertyPlaceholderConfigurer are two very useful BeanFactoryPostProcessors. They let you put placeholders as property values that are then replaced with values from property file. This is very useful for configuring a database connection pool, mail server, etc.

![Alt text](images/beanfactorypostprocessor.png?raw=true "BeanFactoryPostProcessor")


**PropertyPlaceholderConfigurer**
```xml
<bean class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
    <property name="locations" value="person.properties" />
</bean>
```                    
                
**Context Namespace Property Placeholder**
```xml                    
<context:property-placeholder location="person.properties"/>

<bean id="person"
      class="org.springbyexample.springindepth.bean.Person">
    <property name="firstName" value="${firstName}" />
    <property name="lastName" value="${lastName}" />
</bean>
```

 - The Bean method for the BeanFactoryPostProcessor is static and it must always be static. This is because this method is invoked before the other beans.
 
## Teach your bean new Tricks with AOP
AOP gives ability to express patterns, these patterns match object. 

![Alt text](images/aop.png?raw=true "AOP")

To support this in Spring we have @EnableAspectJAutoProxy annotation that is placed on a Configuration class automatically registers all beans marked with @Aspect with the container and than registers all the advise they provide. Here we have an Aspect we want this aspect to only run when a method has annotation @Timed on it; jointpoint represents the current method execution.

![Alt text](images/aop-timed.png?raw=true "AOP Timed")

Transactions in Spring uses AOP.

# Power Tools in Core Spring
## Get Beans from Strange Places
FactoryBean in Spring plays a key role; as the name implies its a factory pattern for creating beans.

FactoryBean has three methods; getObject, getObjectType, isSingleton(). The getObject is charged with returning fully configured fully ready to use. FactoryBean is used in situations where you dont want spring to invoke a constructor of a class to create a bean; instead you want to get the bean from FactoryBean. FactoryBean examples are available in Spring Framework for example suppose you want to look up an object in JNDI you can use JndiObjectFactoryBean. Another example is a TaskExecutor, TaskExecutor in Spring models a Thread Pool. 

![Alt text](images/factorybean.png?raw=true "FactoryBean")
 
### SpEL Spring Expression Language

![Alt text](images/spel.png?raw=true "SpEL") 

```java
    @Value("#{ T(Math).random() }")
	private double aRandomValue;

   @Value("#{systemProperties['user.home']}")
   private String userHome;

   private File tmpDir;

   @Value("#{systemProperties['java.io.tmpdir']}")
	public void setIoTmpDir(String tmpDir) {
		this.tmpDir = new File(tmpDir);
	}
```

### Profiles
It's a common use case to inject a dataSource that is specific to a Environment because dataSource for development is different than from Testing or Production.

 - Property files work to an extent you can change URL, username, password etc. The reason to prefer Spring Profile over other options like property file profiles in Maven is that you cannot select the driver class at runtime, i.e. if you have embedded dataSource for Local Env and Oracle for Prod.
 - You can't actually change the Driver of the dataSource with Property Files, as its a completely different object. 
 
What we need is a way to tell that this is the bean for Local Env and this is the bean for PROD and tag them. Spring provide such a feature through Profiles. So a particular bean is available only when corresponding Tag is active.

![Alt text](images/profiles.png?raw=true "Spring Profiles") 

## Manage Threading with Spring
## Schedule jobs with Spring
## Cache expensive operations with CacheManager API
