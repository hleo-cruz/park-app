package com.br.park.infrastructure.repository.licensePlateStatus;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:/queries/license.plate.status.properties")
public class LicensePlateStatusQuery {

    @Value("${license.plate.status.select}")
    private String select;

    public String select (String licensePlate) {
        return String.format(select, licensePlate);
    }
}
