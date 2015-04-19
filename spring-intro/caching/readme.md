# Spring Caching

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



