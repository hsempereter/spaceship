package com.project.spaceship.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    /**
	 * @param joinPoint
	 * @param result  
	 */
    @AfterReturning(pointcut = "execution(* com.project.spaceship.repository.SpaceshipRepository.findById(..)) && args(id)", returning = "result")
    public void logNegative(JoinPoint joinPoint, Long id, Object result) {
        if (id < 0) {
            logger.warn("Negative ID: " + id);
        }
    }
}
