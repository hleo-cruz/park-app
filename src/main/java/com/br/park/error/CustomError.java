package com.br.park.error;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CustomError {

    private String message;
    private LocalDateTime timestamp;

}
