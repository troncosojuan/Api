package com.Alkemy.Disney.exception;

public class ParamNotFound extends RuntimeException {
    public ParamNotFound(String error) {
        super(error);
    }
}
