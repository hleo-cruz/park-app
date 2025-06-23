package com.br.park.error;

import org.springframework.http.HttpStatus;


public class LicensePlateStatusNotFoundErrorException extends CustomException {

    private static final String message = "There is not found data for license plate informed";

    public LicensePlateStatusNotFoundErrorException() {
        super(message, HttpStatus.NOT_FOUND);
    }
}
