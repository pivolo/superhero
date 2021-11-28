package es.pivol.superhero.restcontroller.time;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(0)
@Component
@Slf4j
public class LogTimeAspect {
    @Pointcut("@annotation(es.pivol.superhero.restcontroller.time.LogTime)")
    private void logTime() {
    }

    @Around("es.pivol.superhero.restcontroller.time.LogTimeAspect.logTime()")
    public Object doLogTime(ProceedingJoinPoint pjp) throws Throwable {
        long initMilliseconds = System.currentTimeMillis();
        Object proceed = pjp.proceed();
        long finishMilliseconds = System.currentTimeMillis();
        long totalMilliseconds = finishMilliseconds - initMilliseconds;
        log.info(pjp.getSignature().toShortString() + ". Duration in milliseconds: " + totalMilliseconds + "ms");
        return proceed;
    }

}
