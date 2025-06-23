package com.br.park.error;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
public class SpotsNotAvailableException extends CustomException {

    private static final String message = "Não foram encontrados vagas disponíveis";

    public SpotsNotAvailableException(Exception cause) {
        super(message, HttpStatus.NOT_FOUND, cause);
    }
}
