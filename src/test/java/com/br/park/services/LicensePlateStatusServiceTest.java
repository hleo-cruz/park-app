package com.br.park.services;

import com.br.park.api.dto.request.LicensePlateStatusRequestDTO;
import com.br.park.api.dto.response.LicensePlateStatusResponseDTO;
import com.br.park.error.LicensePlateStatusNotFoundErrorException;
import com.br.park.infrastructure.repository.licensePlateStatus.LicensePlateStatus;
import com.br.park.infrastructure.repository.licensePlateStatus.LicensePlateStatusRepository;
import com.br.park.mapper.LicensePlateStatusMapper;
import com.br.park.services.calculateprice.CalculatePriceService;
import org.apache.commons.lang3.NotImplementedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class LicensePlateStatusServiceTest {

    private LicensePlateStatusRepository repository;
    private CalculatePriceService calculatePriceService;
    private LicensePlateStatusService service;

    @BeforeEach
    void setUp() {
        repository = mock(LicensePlateStatusRepository.class);
        calculatePriceService = mock(CalculatePriceService.class);
        service = new LicensePlateStatusService(repository, calculatePriceService);
    }

    @Test
    void deveRetornarStatusComValorCalculado() {
        LicensePlateStatusRequestDTO dto = buildLicensePlateStatusRequestDTO();

        LicensePlateStatus entity = buildLicensePlateStatus();
        when(repository.findLicensePlateStatus(dto.getLicensePlate())).thenReturn(Optional.of(entity));

        when(calculatePriceService.calculatePrice(any())).thenReturn(new BigDecimal("30.00"));

        LicensePlateStatusResponseDTO expected = new LicensePlateStatusResponseDTO();

        try (MockedStatic<LicensePlateStatusMapper> mapperMock = mockStatic(LicensePlateStatusMapper.class)) {
            mapperMock.when(() -> LicensePlateStatusMapper.toResponse(entity, new BigDecimal("30.00")))
                    .thenReturn(expected);

            LicensePlateStatusResponseDTO result = service.findByLicensePlate(dto);
            assertEquals(expected, result);
        }
    }

    @Test
    void deveRetornarStatusComPrecoCalculadoSemMockCalculate() {
        throw new NotImplementedException();
    }

    @Test
    void deveLancarExcecaoQuandoPlacaNaoEncontrada() {
        LicensePlateStatusRequestDTO dto =buildLicensePlateStatusRequestDTO();

        when(repository.findLicensePlateStatus("XYZ404")).thenReturn(Optional.empty());

        assertThrows(LicensePlateStatusNotFoundErrorException.class, () -> {
            service.findByLicensePlate(dto);
        });
    }

    private LicensePlateStatusRequestDTO buildLicensePlateStatusRequestDTO() {
        return new LicensePlateStatusRequestDTO("XYZ404");
    }

    private LicensePlateStatus buildLicensePlateStatus() {
        return LicensePlateStatus.builder()
                .entryTime(null)
                .timeParked(null)
                .basePrice(BigDecimal.TEN)
                .duration(60)
                .typeChargeApply(null)
                .build();
    }
}
