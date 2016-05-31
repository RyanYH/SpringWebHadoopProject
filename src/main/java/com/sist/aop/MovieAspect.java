package com.sist.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sist.mapred.MovieDriver;

@Aspect
@Component
public class MovieAspect {
	@Autowired
	private MovieDriver md;
   @Before("execution(* com.sist.mapred.MovieDriver.jobCall(..))")
   public void before()
   {
	   md.fileRemove();
	   md.copyFromLocal();
   }
   @After("execution(* com.sist.mapred.MovieDriver.jobCall(..))")
   public void after()
   {
	   md.copyToLocal();
   }
}





