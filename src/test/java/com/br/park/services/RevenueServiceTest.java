package com.br.park.services;

import com.br.park.api.dto.request.RevenueRequestDTO;
import com.br.park.api.dto.response.RevenueResponseDTO;
import com.br.park.error.RevenueNotFoundErrorException;
import com.br.park.infrastructure.repository.revenue.Revenue;
import com.br.park.infrastructure.repository.revenue.RevenueRepository;
import com.br.park.mapper.RevenueMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RevenueServiceTest {

    private RevenueRepository repository;
    private RevenueService revenueService;

    @BeforeEach
    void setUp() {
        repository = mock(RevenueRepository.class);
        revenueService = new RevenueService(repository);
    }

    @Test
    void deveRetornarRevenueDTOComSomaCorreta() {

        RevenueRequestDTO request = buildRevenueRequestDTO();

        Revenue r1 = new Revenue();
        r1.setFinalPrice(new BigDecimal("10.50"));

        Revenue r2 = new Revenue();
        r2.setFinalPrice(new BigDecimal("20.25"));

        when(repository.findByDateAndSector(request.getSector(), request.getDate())).thenReturn(List.of(r1, r2));

        RevenueResponseDTO responseMock = new RevenueResponseDTO();
        try (MockedStatic<RevenueMapper> mapperMock = mockStatic(RevenueMapper.class)) {
            mapperMock.when(() -> RevenueMapper.toResponse(new BigDecimal("30.75"), request.getDate()))
                    .thenReturn(responseMock);

            RevenueResponseDTO result = revenueService.findByDateAndSector(request);

            assertEquals(responseMock, result);
            verify(repository).findByDateAndSector(request.getSector(), request.getDate());
        }
    }

    @Test
    void deveLancarExcecaoSeNaoEncontrarReceitas() {

        RevenueRequestDTO request = buildRevenueRequestDTO();

        when(repository.findByDateAndSector(anyString(), any()))
                .thenReturn(List.of());

        assertThrows(RevenueNotFoundErrorException.class, () -> {
            revenueService.findByDateAndSector(request);
        });
    }

    RevenueRequestDTO buildRevenueRequestDTO() {
        return new RevenueRequestDTO("SETOR404", LocalDate.now());
    }
}
