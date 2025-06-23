package com.br.park.infrastructure.repository.spotStatus;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:/queries/spot.status.properties")
public class SpotStatusQuery {

    @Value("${spot.status.select}")
    private String select;

    public String select (String lat, String lng) {
        return String.format(select, lat, lng);
    }
}
