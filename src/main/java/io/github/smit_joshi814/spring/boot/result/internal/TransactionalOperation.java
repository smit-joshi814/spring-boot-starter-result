package io.github.smit_joshi814.spring.boot.result.internal;

public interface TransactionalOperation {
    Boolean shouldRollback();
}
