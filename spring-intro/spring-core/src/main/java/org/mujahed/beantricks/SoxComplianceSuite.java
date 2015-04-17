package org.mujahed.beantricks;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.util.Assert;

public class SoxComplianceSuite implements BeanFactoryPostProcessor {

	private Log log = LogFactory.getLog(getClass());

	// often a very useful question to ask: has this bean been registered
	// before?
	private boolean definitionExists(Class<?> clazz,
			ConfigurableListableBeanFactory r) {
		boolean exists = r.getBeansOfType(clazz).size() != 0;
		if (log.isDebugEnabled()) {
			log.debug(clazz.getSimpleName() + " is " + (exists ? " " : " not ")
					+ " already registered");
		}
		return exists;
	}

	@Override
	public void postProcessBeanFactory(
			ConfigurableListableBeanFactory beanFactory) throws BeansException {
		Assert.isInstanceOf(BeanDefinitionRegistry.class, beanFactory);
		BeanDefinitionRegistry registry = (BeanDefinitionRegistry) beanFactory;

		/**
		 * <bean class = "...AnnotationAwareAspectJAutoProxyCreator"/>
		 */
		if (!definitionExists(AnnotationAwareAspectJAutoProxyCreator.class,
				beanFactory)) {
			BeanDefinitionReaderUtils.registerWithGeneratedName(
					new RootBeanDefinition(
							AnnotationAwareAspectJAutoProxyCreator.class),
					registry);	
		}

		/**
		 * <bean class = "...MethodTimeLoggingAspect"/>
		 */
		if (!definitionExists(MethodTimeLoggingAspect.class,
				beanFactory)) {
			BeanDefinitionReaderUtils.registerWithGeneratedName(
					new RootBeanDefinition(
							MethodTimeLoggingAspect.class),
					registry);	
		}

	}

}
