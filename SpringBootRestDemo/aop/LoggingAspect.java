package com.mercury.SpringBootRestDemo.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	private Logger log = (Logger) LoggerFactory.getLogger(this.getClass());
	final String str = "execution (* com.mercury.SpringBootRestDemo.controller.*.getAll(..))";
	
	// the first * means return, * means any return 
	@Before(str)
	public void logFoo() {
		log.warn("AoP Foo");
	}
	
	@Pointcut("execution (* com.mercury.SpringBootRestDemo.controller.*.getAll(..))")
	public void afterGetAllPointcut() {
		
	}
	
	@After("afterGetAllPointcut()")
	public void logBar() {
		log.warn("AOP Bar");
	}
	
	@Around("execution (* com.mercury.SpringBootRestDemo.controller.*.get*ById(..))")
	public Object logAround(ProceedingJoinPoint pjp) {
		log.warn("AOP Something Around Before");
		Object o=null;
		try {
			o=pjp.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			log.error("getById() exception out",e);
		}
		log.warn("Around After");
		return o;
	}
}
