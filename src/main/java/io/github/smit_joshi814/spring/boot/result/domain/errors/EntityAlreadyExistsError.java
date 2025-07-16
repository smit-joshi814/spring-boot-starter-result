package io.github.smit_joshi814.spring.boot.result.domain.errors;

public final class EntityAlreadyExistsError extends Error {
    public EntityAlreadyExistsError(String message) {
        super(message);
    }
}
