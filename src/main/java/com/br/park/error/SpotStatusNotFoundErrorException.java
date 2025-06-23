package com.br.park.error;

import org.springframework.http.HttpStatus;


public class SpotStatusNotFoundErrorException extends CustomException {

    private static final String message = "There is not found spot data for lat and lng informed";

    public SpotStatusNotFoundErrorException() {
        super(message, HttpStatus.NOT_FOUND);
    }
}
