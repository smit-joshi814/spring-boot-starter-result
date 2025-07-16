package io.github.smit_joshi814.spring.boot.result.domain.errors;

public final class ValidationError extends Error {
    
    public ValidationError(String message) {
        super(message);
    }
}
