package com.br.park.services.calculateprice;

import com.br.park.infrastructure.repository.parklot.TypeChargeApplyEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CalculatePriceServiceTest {

    private CalculatePriceService service;

    @BeforeEach
    void setUp() {
        service = new CalculatePriceService();
    }

    @Test
    void deveCalcularPrecoComAumento() {

        CalculatePriceDTO dto = CalculatePriceDTO.builder()
                .entryTime(LocalDateTime.now().minusMinutes(30))
                .exitTime(LocalDateTime.now())
                .basePrice(new BigDecimal("60.00"))
                .durationLimitMinutes(60)
                .charge(new BigDecimal("5.00"))
                .typeChargeApply(TypeChargeApplyEnum.INCREASE)
                .build();

        BigDecimal result = service.calculatePrice(dto);

        // Preço base dividido por 30 minutos (tempo de permanência)
        BigDecimal expected = new BigDecimal("31.50");
        assertEquals(expected, result);
    }

    @Test
    void deveCalcularPrecoComReducao() {
        CalculatePriceDTO dto = CalculatePriceDTO.builder()
                .entryTime(LocalDateTime.now().minusMinutes(30))
                .exitTime(LocalDateTime.now())
                .basePrice(new BigDecimal("60.00"))
                .durationLimitMinutes(60)
                .charge(new BigDecimal("5.00"))
                .typeChargeApply(TypeChargeApplyEnum.DECREASE)
                .build();

        BigDecimal result = service.calculatePrice(dto);

        // Preço base dividido por 30 minutos (tempo de permanência)
        BigDecimal expected = new BigDecimal("28.50");
        assertEquals(expected, result);
    }

    @Test
    void deveCalcularPrecoSemAlteracao() {
        CalculatePriceDTO dto = CalculatePriceDTO.builder()
                .entryTime(LocalDateTime.now().minusMinutes(15))
                .exitTime(LocalDateTime.now())
                .basePrice(new BigDecimal("30.00"))
                .durationLimitMinutes(60)
                .charge(BigDecimal.ZERO)
                .typeChargeApply(TypeChargeApplyEnum.NONE)
                .build();

        BigDecimal expected = new BigDecimal("30.00");
        BigDecimal result = service.calculatePrice(dto);

        assertEquals(expected, result);
    }
}
