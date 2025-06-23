package com.br.park.services;

import com.br.park.api.dto.request.SpotStatusRequestDTO;
import com.br.park.api.dto.response.SpotStatusResponseDTO;
import com.br.park.infrastructure.repository.spotStatus.SpotStatus;
import com.br.park.infrastructure.repository.spotStatus.SpotStatusRepository;
import com.br.park.mapper.SpotStatusMapper;
import com.br.park.services.calculateprice.CalculatePriceService;
import org.apache.commons.lang3.NotImplementedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class SpotStatusServiceTest {

    private SpotStatusRepository repository;
    private CalculatePriceService calculatePriceService;
    private SpotStatusService service;

    @BeforeEach
    void setUp() {
        repository = mock(SpotStatusRepository.class);
        calculatePriceService = mock(CalculatePriceService.class);
        service = new SpotStatusService(repository, calculatePriceService);
    }

    @Test
    void deveRetornarStatusComPrecoCalculado() {

        SpotStatusRequestDTO dto = buildSpotStatusRequestDTO();

        SpotStatus entity = buildSpotStatus();
        when(repository.findByLatAndLng(dto.getLat(), dto.getLng())).thenReturn(entity);
        when(calculatePriceService.calculatePrice(any())).thenReturn(new BigDecimal("50.00"));

        SpotStatusResponseDTO responseEsperado = new SpotStatusResponseDTO();
        try (MockedStatic<SpotStatusMapper> mapperMock = mockStatic(SpotStatusMapper.class)) {
            mapperMock.when(() -> SpotStatusMapper.toResponse(entity, new BigDecimal("50.00")))
                    .thenReturn(responseEsperado);

            SpotStatusResponseDTO result = service.findByLatLng(dto);

            assertEquals(responseEsperado, result);
        }
    }

    @Test
    void deveRetornarStatusComPrecoCalculadoSemMockCalculate() {
       throw new NotImplementedException();
    }

    @Test
    void deveLancarExcecaoSeNaoEncontrarSpotStatus() {
        SpotStatusRequestDTO dto = buildSpotStatusRequestDTO();

        when(repository.findByLatAndLng(dto.getLat(), dto.getLng())).thenReturn(null);

        assertThrows(NullPointerException.class, () -> {
            service.findByLatLng(dto);
        });
    }

    private SpotStatusRequestDTO buildSpotStatusRequestDTO() {
        return SpotStatusRequestDTO.builder()
                .lat("1.23")
                .lng("4.56")
                .build();
    }

    private SpotStatus buildSpotStatus() {
        return SpotStatus.builder()
                .entryTime(null)
                .timeParked(null)
                .basePrice(BigDecimal.TEN)
                .duration(60)
                .typeChargeApply(null)
                .build();
    }
}
