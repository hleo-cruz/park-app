package com.br.park.infrastructure.repository.revenue;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@PropertySource("classpath:/queries/revenue.properties")
public class RevenueQuery {

    @Value("${revenue.select}")
    private String select;

    public String select (String codSector, LocalDate date) {
        return String.format(select, codSector, date);
    }
}
