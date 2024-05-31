package com.project.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(LoggingAspect.class);

    /**
	 * @param joinPoint
	 * @param result  
	 */
    @AfterReturning(pointcut = "execution(* com.project.spaceship.repository.SpaceshipRepository.findById(..)) && args(id)", returning = "result")
    public void logNegative(JoinPoint joinPoint, Long id, Object result) {
        if (id < 0) {
            logger.warning("Negative ID: " + id);
        }
    }
}
