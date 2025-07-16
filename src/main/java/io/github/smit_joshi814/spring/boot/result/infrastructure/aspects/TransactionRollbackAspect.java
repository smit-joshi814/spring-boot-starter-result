package io.github.smit_joshi814.spring.boot.result.infrastructure.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import io.github.smit_joshi814.spring.boot.result.internal.TransactionalOperation;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Aspect
@Component
public final class TransactionRollbackAspect {

    @Around("@annotation(RollbackOnFailure)")
    public Object handleTransactionRollback(ProceedingJoinPoint pjp) throws Throwable {
        Object result = pjp.proceed();

        if (result instanceof TransactionalOperation) {
            TransactionalOperation operation = (TransactionalOperation) result;
            if (operation.shouldRollback()) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
        }
        return result;
    }
}
