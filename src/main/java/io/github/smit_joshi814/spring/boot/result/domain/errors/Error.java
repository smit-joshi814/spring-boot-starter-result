package io.github.smit_joshi814.spring.boot.result.domain.errors;

public class Error {
    private String message;

    public Error(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
