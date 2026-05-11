package org.example.expert.config;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class AdminAOP {

    @Around("@annotation(org.example.expert.domain.common.annotation.AdminAspect)")
    public Object adminLog(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        // 메서드 실행 전

        long start = System.currentTimeMillis();
        log.info("[AOP Request] userID:{}, requestTime:{}, requestURL:{}, requestBody:{}",
                (Long) request.getAttribute("userId"),
                LocalDateTime.now(),
                request.getRequestURI(),
                Arrays.toString(joinPoint.getArgs())
        );

        Object result = joinPoint.proceed();
        // 메서드 실행 후

        long end = System.currentTimeMillis();

        log.info("[AOP Response] {} in {}ms", joinPoint.getSignature(), end - start);

        return result;
    }

}
