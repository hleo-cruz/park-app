package com.br.park.services.webhook;

import com.br.park.api.dto.request.ParkLotRequestDTO;
import com.br.park.error.ParkLotNotFountWithActiveStatusByLicensePlateException;
import com.br.park.error.SectorNotFoundErrorException;
import com.br.park.error.SpotsNotAvailableException;
import com.br.park.infrastructure.repository.parklot.ParkLot;
import com.br.park.infrastructure.repository.sector.Sector;
import com.br.park.infrastructure.repository.spot.Spot;
import com.br.park.services.ParkLotService;
import com.br.park.services.SectorService;
import com.br.park.services.SpotService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static com.br.park.infrastructure.repository.parklot.ParkLotStatusEnum.ACTIVE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EntrySpotServiceTest {

    private ParkLotService parkLotService;
    private SpotService spotService;
    private SectorService sectorService;
    private EntrySpotService service;

    @BeforeEach
    void setUp() {
        parkLotService = mock(ParkLotService.class);
        spotService = mock(SpotService.class);
        sectorService = mock(SectorService.class);
        service = new EntrySpotService(parkLotService, spotService, sectorService);
    }

    @Test
    void deveProcessarEntradaComSucesso() {
        ParkLotRequestDTO dto = mock(ParkLotRequestDTO.class);
        when(dto.getLicensePlate()).thenReturn("ABC1234");

        Spot spot = new Spot();
        spot.setId(1);
        spot.setSector("SEC1");

        Sector sector = new Sector();
        sector.setBasePrice(new BigDecimal("20.00"));
        sector.setDurationLimitMinutes(60);

        ParkLot parkLot = new ParkLot();

        when(spotService.findNextAvailableSpot()).thenReturn(Optional.of(spot));
        when(sectorService.findSectorById("SEC1")).thenReturn(Optional.of(sector));
        when(parkLotService.findByPlateAndStatus("ABC1234", ACTIVE)).thenReturn(Optional.of(parkLot));

        service.process(dto);

        assertEquals(1, parkLot.getSpotId());
        assertEquals(new BigDecimal("20.00"), parkLot.getBasePrice());
        assertEquals(60, parkLot.getDurationLimitMinutes());

        verify(parkLotService).save(parkLot);
        verify(spotService).updateSpotToOccupied(spot);
    }

    @Test
    void deveLancarExcecaoQuandoNaoHouverVagaDisponivel() {
        when(spotService.findNextAvailableSpot()).thenReturn(Optional.empty());
        ParkLotRequestDTO dto = mock(ParkLotRequestDTO.class);

        assertThrows(SpotsNotAvailableException.class, () -> service.process(dto));
    }

    @Test
    void deveLancarExcecaoQuandoSetorNaoForEncontrado() {
        Spot spot = new Spot();
        spot.setSector("SETOR404");

        when(spotService.findNextAvailableSpot()).thenReturn(Optional.of(spot));
        when(sectorService.findSectorById("SETOR404")).thenReturn(Optional.empty());

        ParkLotRequestDTO dto = mock(ParkLotRequestDTO.class);

        assertThrows(SectorNotFoundErrorException.class, () -> service.process(dto));
    }

    @Test
    void deveLancarExcecaoQuandoParkLotNaoForEncontrado() {
        Spot spot = new Spot();
        spot.setSector("SEC1");

        Sector sector = new Sector();
        sector.setBasePrice(BigDecimal.TEN);
        sector.setDurationLimitMinutes(30);

        when(spotService.findNextAvailableSpot()).thenReturn(Optional.of(spot));
        when(sectorService.findSectorById("SEC1")).thenReturn(Optional.of(sector));
        when(parkLotService.findByPlateAndStatus("ABC1234", ACTIVE)).thenReturn(Optional.empty());

        ParkLotRequestDTO dto = mock(ParkLotRequestDTO.class);
        when(dto.getLicensePlate()).thenReturn("ABC1234");

        assertThrows(ParkLotNotFountWithActiveStatusByLicensePlateException.class, () -> service.process(dto));
    }
}
