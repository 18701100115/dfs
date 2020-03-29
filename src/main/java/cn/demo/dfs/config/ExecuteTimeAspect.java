package cn.demo.dfs.config;

import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecuteTimeAspect {

    private final Logger logger = LoggerFactory.getLogger(ExecuteTimeAspect.class);

    @Pointcut("execution(* cn.demo.dfs.api.*.*(..))")
    public void serviceExecutionTimeLog() {
    }


    @Around(value = "serviceExecutionTimeLog()")
    public Object doAfter(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        org.springframework.util.StopWatch stopWatch = new org.springframework.util.StopWatch();
        stopWatch.start();
        Object proceed = proceedingJoinPoint.proceed();
        stopWatch.stop();
        logger.info("execute-time-name : [{}], class-method : [{}]",
                stopWatch.getTotalTimeMillis(),
                proceedingJoinPoint.getTarget().getClass().getName() + "." + proceedingJoinPoint.getSignature().getName());

        return proceed;
    }
}