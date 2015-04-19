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

```



