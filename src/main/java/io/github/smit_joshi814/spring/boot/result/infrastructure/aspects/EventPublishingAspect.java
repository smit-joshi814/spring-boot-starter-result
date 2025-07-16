package io.github.smit_joshi814.spring.boot.result.infrastructure.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import io.github.smit_joshi814.spring.boot.result.Result;
import io.github.smit_joshi814.spring.boot.result.annotations.PublishEvent;
import io.github.smit_joshi814.spring.boot.result.domain.events.ResultEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Aspect
@Component
public final class EventPublishingAspect {

    private final ApplicationEventPublisher eventPublisher;

    public EventPublishingAspect(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Around("@annotation(publishEvent)")
    public Object publishResultEvent(ProceedingJoinPoint pjp, PublishEvent publishEvent) throws Throwable {
        Object result = pjp.proceed();

        if (result instanceof Result<?>) {
            Result<?> resultObj = (Result<?>) result;
            String eventName = publishEvent.eventName().isEmpty() 
                ? pjp.getSignature().getName() 
                : publishEvent.eventName();

            boolean shouldPublish = switch (publishEvent.on()) {
                case SUCCESS -> resultObj.isSuccess();
                case FAILURE -> !resultObj.isSuccess();
                case BOTH -> true;
            };

            if (shouldPublish) {
                ResultEvent<?> event = new ResultEvent<>(
                    pjp.getTarget(),
                    eventName, 
                    resultObj, 
                    pjp.getSignature().getName(), 
                    pjp.getArgs()
                );
                eventPublisher.publishEvent(event);
            }
        }

        return result;
    }
}
