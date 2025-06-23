package com.br.park.services.webhook;

import com.br.park.api.EventTypeEnum;
import com.br.park.api.dto.request.ParkLotRequestDTO;
import com.br.park.infrastructure.repository.parklot.ParkLot;
import com.br.park.infrastructure.repository.parklot.TypeChargeApplyEnum;
import com.br.park.mapper.ParkLotMapper;
import com.br.park.services.ParkLotService;
import com.br.park.services.SpotService;
import com.br.park.services.chain.CapacityLowerThan25PerCentChain;
import com.br.park.services.chain.TestCapacityResultDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EntryParkLotServiceTest {

    private ParkLotService parkLotService;
    private SpotService spotService;
    private CapacityLowerThan25PerCentChain chain;
    private EntryParkLotService service;

    @BeforeEach
    void setUp() {
        parkLotService = mock(ParkLotService.class);
        spotService = mock(SpotService.class);
        chain = mock(CapacityLowerThan25PerCentChain.class);
        service = new EntryParkLotService(parkLotService, spotService, chain);
    }

    @Test
    void deveProcessarEntradaComCobranca() {

        ParkLotRequestDTO dto = buildParkLotRequestDTO();
        ParkLot parkLotMock = ParkLotMapper.toEntity(dto);

        try (MockedStatic<ParkLotMapper> parkLotMapperMock = mockStatic(ParkLotMapper.class)) {
            parkLotMapperMock.when(() -> ParkLotMapper.toEntity(dto)).thenReturn(parkLotMock);

            when(spotService.countPercentAvailableSpots()).thenReturn(17);

            TestCapacityResultDTO resultMock = new TestCapacityResultDTO();
            resultMock.setCharge(new BigDecimal("12.34"));
            resultMock.setTypeChargeApplyEnum(TypeChargeApplyEnum.INCREASE);

            when(chain.evaluate(17)).thenReturn(resultMock);

            service.process(dto);

            verify(parkLotService).save(parkLotMock);
            assertEquals(new BigDecimal("12.34"), parkLotMock.getAmountCharge());
            assertEquals(TypeChargeApplyEnum.INCREASE, parkLotMock.getTypeChargeApply());
        }
    }

    private ParkLotRequestDTO buildParkLotRequestDTO() {
        return ParkLotRequestDTO.builder()
                .entryTime(LocalDateTime.now())
                .exitTime(LocalDateTime.now())
                .licensePlate("AZX123456")
                .eventType(EventTypeEnum.ENTRY_PARK_LOT)
                .latitude("0.1234")
                .longitude("0.1234")
                .build();
    }

}
