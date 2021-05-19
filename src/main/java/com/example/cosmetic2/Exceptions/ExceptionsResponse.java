package com.example.cosmetic2.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
public class ExceptionsResponse {
    public static enum ERROR_CODE {
        PRODUCT_DOES_NOT_EXIST(4001),
        PRODUCT_ALREADY_EXIST(4002),
        NOT_HAVE_FILE(4003),
        SOME_DATA_NOT_NULL(4004);
        private int value;

        ERROR_CODE(int value) {
            this.value = value;
        }
    }

    private ERROR_CODE errorCode;
    private String message;
    private LocalDateTime dateTime;

}
