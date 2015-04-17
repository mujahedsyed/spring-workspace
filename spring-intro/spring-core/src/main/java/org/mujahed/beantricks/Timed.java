package org.mujahed.beantricks;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * Marker annotation to tell the
 * {@link org.mujahed.beantricks.MethodTimeLoggingBeanPostProcessor} that
 * methods should be logged
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Timed {

}
