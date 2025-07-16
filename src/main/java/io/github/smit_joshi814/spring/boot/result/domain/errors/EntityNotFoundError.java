package io.github.smit_joshi814.spring.boot.result.domain.errors;

public final class EntityNotFoundError extends Error {
    public EntityNotFoundError(String message) {
        super(message);
    }
}
