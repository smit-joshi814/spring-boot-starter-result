package io.github.smit_joshi814.spring.boot.result.domain.errors;

public final class UnauthorizedError extends Error {
    public UnauthorizedError(String message) {
        super(message);
    }
}
