package com.example.cosmetic2.Exceptions;

public class ProductsExceptions extends RuntimeException{
    ExceptionsResponse.ERROR_CODE errorCode;
    public ProductsExceptions(ExceptionsResponse.ERROR_CODE errorCode, String s) {
        super(s);
        this.errorCode = errorCode;
    }

    public ExceptionsResponse.ERROR_CODE getErrorCode() {
        return this.errorCode;
    }
}
