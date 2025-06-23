package com.br.park.error;

import org.springframework.http.HttpStatus;


public class RevenueNotFoundErrorException extends CustomException {

    private static final String message = "There is not found data for revenue for date and sector informed";

    public RevenueNotFoundErrorException() {
        super(message, HttpStatus.NOT_FOUND);
    }
}
