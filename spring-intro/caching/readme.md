# Spring Caching

 - Spring Doc: http://docs.spring.io/spring/docs/current/spring-framework-reference/html/cache.html
 
 - Spring’s cache abstraction comes in two forms:
  - Annotation-driven caching
  - XML-declared caching

 - You can enable annotation-driven caching by adding @EnableCaching to one of your configuration classes. 

```java
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CachingConfiguration {

   @Bean
   public CacheManager cacheManager() {
		return new ConcurrentMapCacheManager();
	}
}
```

 - If you’re configuring your application with XML, you can enable annotation-driven caching with the <cache:annotation-driven> element from Spring’s cache namespace.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans" 
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:cache="http://www.springframework.org/schema/cache"
	xsi:schemaLocation="
	http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
	http://www.springframework.org/schema/cache http://www.springframework.org/schema/cache/spring-cache.xsd">

  <cache:annotation-driven />

  <bean id="cacheManager" class="org.springframework.cache.concurrent.ConcurrentMapCacheManager" />

</beans>

```

 - Under the covers, @EnableCaching and <cache:annotation-driven> work the same way. They create an aspect with pointcuts that trigger off of Spring’s caching annotations. Depending on the annotation used and the state of the cache, that aspect will fetch a value from the cache, add a value to the cache, or remove a value from the cache.
 
 - The above configurations also declare a cacheManager bean, this bean is an implementation of org.springframework.cache.CacheManager out of the box, Spring 3.1 comes with five cache-manager implementations:
􏰀  - SimpleCacheManager
􏰀  - NoOpCacheManager
􏰀  - ConcurrentMapCacheManager
  - CompositeCacheManager
􏰀  - EhCacheCacheManager

 - RedisCacheManager and GemfireCacheManager are JCache (JSR-107) implementation provided by Spring Data outside of spring core framework, this is available in Spring 3.2.

## EhCache TODO
 
## Annotating Methods for Caching
 
 - @Cacheable: Indicates that Spring should look in a cache for the method's return value before invoking the method. If the value is found, the cached value is returned. If not, than the method is invoked and the return value is put in the cache.
 
 - @CachePut: Indicates that Spring should put the method's return value in a cache. The cache isn't checked prior to method invocation, and the method is always invoked.
 
 - @CacheEvict: Indicates that Spring should evict one or more entries from a cache.
 
 - @Caching: A grouping annotation for applying multiples of the other caching annotations at once.
 
## Populating the cache
 - @Cacheable and @Cacheput share a common set of attributes: **value**, **condition** (will be put in cache if evaluates to true), **key** (A SpEL expression to calculate a custom cache key), **unless** (A SpEL expression that, if evaluates to true, prevents the return value from being put in the cache).
 
```java
    @Override
	@Cacheable("books")
	public Book getBookByIsbn(String isbn) {
		simulateSlowService();
		return new Book(isbn, "Some book");
	}
```

equivalent xml:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:cache="http://www.springframework.org/schema/cache"
	xmlns:aop="http://www.springframework.org/schema/aop"
	xsi:schemaLocation="
	http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
	http://www.springframework.org/schema/cache http://www.springframework.org/schema/cache/spring-cache.xsd
	http://www.springframework.org/schema/aop
    http://www.springframework.org/schema/aop/spring-aop.xsd">

	<!--  <cache:annotation-driven /> -->

	<bean id="cacheManager" class="org.springframework.cache.concurrent.ConcurrentMapCacheManager" />
	<bean id="bookRepository" class="org.mujahed.repository.SimpleBookRepository"/>
	
	<aop:config>
		<aop:advisor advice-ref="cacheAdvice" pointcut="execution(* org.mujahed.repository.SimpleBookRepository.*(..))"/>
	</aop:config>
 	
	<cache:advice id="cacheAdvice">
		<cache:caching>
			<cache:cacheable cache="bookCache" method="getBookByIsbn" />
		</cache:caching>
	</cache:advice>
</beans>

```

### Customising the Cache Key
 - Both @Cacheable and @CachePut have a key attribute that lets you replace the default key with one derived from a SpEL expression.
 
 - Suppose you like to get the id for a particular entity that is saved and suppose the method has a void return type. Spring exposes metadata and offers several SpEL extensions specifically for defining cache rules like #result.id (The #result expression will give you the returned Entity. From that, you can reference the id property by setting the key attribute to #result.id)
   
 - More examples: 
  - Page 371 SIA
  - http://docs.spring.io/spring/docs/current/spring-framework-reference/html/cache.html#cache-spel-context

