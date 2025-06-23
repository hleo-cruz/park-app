package com.br.park.error;

import org.springframework.http.HttpStatus;


public class SectorNotFoundErrorException extends CustomException {

    private static final String message = "There is not found data for sector for cod informed";

    public SectorNotFoundErrorException() {
        super(message, HttpStatus.NOT_FOUND);
    }
}
