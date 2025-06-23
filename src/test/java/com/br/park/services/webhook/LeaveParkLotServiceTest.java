package com.br.park.services.webhook;

import com.br.park.api.dto.request.ParkLotRequestDTO;
import com.br.park.error.MandatoryParamsNotInformedException;
import com.br.park.infrastructure.repository.parklot.ParkLot;
import com.br.park.infrastructure.repository.parklot.ParkLotStatusEnum;
import com.br.park.infrastructure.repository.parklot.TypeChargeApplyEnum;
import com.br.park.services.ParkLotService;
import com.br.park.services.calculateprice.CalculatePriceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class LeaveParkLotServiceTest {

    private ParkLotService parkLotService;
    private CalculatePriceService calculatePriceService;
    private LeaveParkLotService service;

    @BeforeEach
    void setUp() {
        parkLotService = mock(ParkLotService.class);
        calculatePriceService = mock(CalculatePriceService.class);
        service = new LeaveParkLotService(parkLotService, calculatePriceService);
    }

    @Test
    void deveProcessarSaidaComSucesso() {
        ParkLotRequestDTO dto = mock(ParkLotRequestDTO.class);
        when(dto.getLicensePlate()).thenReturn("ABC1234");

        LocalDateTime entryTime = LocalDateTime.now().minusMinutes(120);
        LocalDateTime exitTime = LocalDateTime.now();

        when(dto.getEntryTime()).thenReturn(entryTime);
        when(dto.getExitTime()).thenReturn(exitTime);

        ParkLot parkLot = new ParkLot();
        parkLot.setParkedTime(entryTime);
        parkLot.setBasePrice(BigDecimal.TEN);
        parkLot.setDurationLimitMinutes(60);
        parkLot.setTypeChargeApply(TypeChargeApplyEnum.INCREASE);

        when(parkLotService.findByPlateAndStatus("ABC1234", ParkLotStatusEnum.PARKED))
                .thenReturn(Optional.of(parkLot));

        when(calculatePriceService.calculatePrice(any())).thenReturn(new BigDecimal("40.00"));

        service.process(dto);

        assertEquals(ParkLotStatusEnum.EXITED, parkLot.getStatus());
        assertEquals(120, parkLot.getTotalMinutes());
        verify(parkLotService).save(parkLot);
    }

    @Test
    void deveLancarExcecaoSeNaoInformarExitTime() {
        ParkLotRequestDTO dto = mock(ParkLotRequestDTO.class);
        when(dto.getEntryTime()).thenReturn(LocalDateTime.now());
        when(dto.getExitTime()).thenReturn(null);

        assertThrows(MandatoryParamsNotInformedException.class, () -> {
            service.calculateParkingTime(dto);
        });
    }
}
