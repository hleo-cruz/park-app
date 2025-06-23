package com.br.park.services;

import com.br.park.error.ParkLotGenericErrorException;
import com.br.park.error.ParkLotNotFountWithActiveStatusByLicensePlateException;
import com.br.park.infrastructure.repository.parklot.ParkLot;
import com.br.park.infrastructure.repository.parklot.ParkLotRepository;
import com.br.park.infrastructure.repository.parklot.ParkLotStatusEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ParkLotServiceTest {

    private ParkLotRepository repository;
    private SpotService spotService;
    private ParkLotService parkLotService;

    @BeforeEach
    void setUp() {
        repository = mock(ParkLotRepository.class);
        spotService = mock(SpotService.class);
        parkLotService = new ParkLotService(repository, spotService);
    }

    @Test
    void deveSalvarComSucesso() {
        ParkLot parkLot = new ParkLot();

        assertDoesNotThrow(() -> parkLotService.save(parkLot));
        verify(repository).save(parkLot);
    }

    @Test
    void deveLancarExcecaoAoSalvar() {
        ParkLot parkLot = new ParkLot();
        doThrow(new RuntimeException("Erro")).when(repository).save(parkLot);

        assertThrows(ParkLotGenericErrorException.class, () -> parkLotService.save(parkLot));
    }

    @Test
    void deveRetornarParkLotQuandoEncontrado() {
        ParkLot parkLot = new ParkLot();
        when(repository.findByLicensePlateAndStatus("ABC1234", ParkLotStatusEnum.PARKED))
                .thenReturn(Optional.of(parkLot));

        Optional<ParkLot> result = parkLotService.findByPlateAndStatus("ABC1234", ParkLotStatusEnum.PARKED);

        assertTrue(result.isPresent());
        assertEquals(parkLot, result.get());
    }

    @Test
    void deveRetornarVazioQuandoNaoEncontrar() {
        when(repository.findByLicensePlateAndStatus("XYZ9999", ParkLotStatusEnum.EXITED))
                .thenReturn(Optional.empty());

        Optional<ParkLot> result = parkLotService.findByPlateAndStatus("XYZ9999", ParkLotStatusEnum.EXITED);

        assertFalse(result.isPresent());
    }

    @Test
    void deveLancarExcecaoAoBuscar() {
        when(repository.findByLicensePlateAndStatus("ERR0000", ParkLotStatusEnum.EXITED))
                .thenThrow(new RuntimeException("Falha"));

        assertThrows(ParkLotGenericErrorException.class, () -> {
            parkLotService.findByPlateAndStatus("ERR0000", ParkLotStatusEnum.EXITED);
        });
    }
}
