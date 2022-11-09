package com.security.forma_security.metric;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

//@Component
//@Aspect
//public class AppMonitoringMetrics {
//
//    private final MeterRegistry registry;
//
//    @Autowired
//    public AppMonitoringMetrics(MeterRegistry registry) {
//        this.registry = new CompositeMeterRegistry();;
//    }
//
//    @Before("execution(public * com.security.forma_security.Service.UserService.CommandeServiceImpl.create() )")
//    public void succes(JoinPoint joinPoint) {
//
//        Tag tag = Tag.of("ordr-impl", joinPoint.getTarget().getClass().getSimpleName());
//        registry.counter("order-processes",List.of(tag)).increment();
//    }
//
//    @AfterThrowing("execution(public * com.security.forma_security.Service.UserService.CommandeServiceImpl.create())")
//    public void fail(JoinPoint joinPoint) {
//
//        Tag tag = Tag.of("ordr-impl", joinPoint.getTarget().getClass().getSimpleName());
//        registry.counter("order-processed-failed").increment();
//    }
//
//    @Around("execution(public * com.security.forma_security.Service.UserService.CommandeServiceImpl.create())")
//    public Object duration(ProceedingJoinPoint joinPoint)
//            throws Throwable {
//        Tag tag = Tag.of("ordr-impl", joinPoint.getTarget().getClass().getSimpleName());
//        Timer timer = registry.timer("order-process-duration", List.of(tag));
//        Instant startTime = Instant.now();
//        try {
//            return joinPoint.proceed(joinPoint.getArgs());
//        } finally {
//            timer.record(Duration.between(startTime, Instant.now()));
//        }
//    }
//
//}
