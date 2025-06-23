package com.br.park.services;

import com.br.park.error.SpotGenericErrorException;
import com.br.park.infrastructure.repository.spot.Spot;
import com.br.park.infrastructure.repository.spot.SpotRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SpotServiceTest {

    private SpotRepository spotRepository;
    private SpotService spotService;

    @BeforeEach
    void setUp() {
        spotRepository = mock(SpotRepository.class);
        spotService = new SpotService(spotRepository);
    }

    @Test
    void deveRetornarSpotDisponivel() {
        Spot spot = new Spot();
        spot.setOccupied(false);

        when(spotRepository.findFirstByOccupied(false)).thenReturn(Optional.of(spot));

        Optional<Spot> resultado = spotService.findNextAvailableSpot();

        assertTrue(resultado.isPresent());
        assertEquals(spot, resultado.get());
        verify(spotRepository).findFirstByOccupied(false);
    }

    @Test
    void deveRetornarSpotInDisponivel() {
        Spot spot = new Spot();
        spot.setOccupied(false);
        when(spotRepository.findFirstByOccupied(false)).thenReturn(Optional.empty());
        Optional<Spot> resultado = spotService.findNextAvailableSpot();
        assertFalse(resultado.isPresent());
        verify(spotRepository).findFirstByOccupied(false);
    }

    @Test
    void deveLancarExcecaoQuandoRepositorioFalhar() {
        when(spotRepository.findFirstByOccupied(false)).thenThrow(new RuntimeException("Falha"));

        assertThrows(SpotGenericErrorException.class, () -> {
            spotService.findNextAvailableSpot();
        });
    }
}
