package com.br.park.services;

import com.br.park.error.SectorGenericErrorException;
import com.br.park.infrastructure.repository.sector.Sector;
import com.br.park.infrastructure.repository.sector.SectorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SectorServiceTest {

    private SectorRepository repository;
    private SectorService service;

    @BeforeEach
    void setUp() {
        repository = mock(SectorRepository.class);
        service = new SectorService(repository);
    }

    @Test
    void deveSalvarSetorComSucesso() {
        Sector sector = new Sector();
        assertDoesNotThrow(() -> service.saveSector(sector));
        verify(repository).save(sector);
    }

    @Test
    void deveLancarExcecaoAoSalvar() {
        Sector sector = new Sector();
        doThrow(new RuntimeException("Erro ao salvar")).when(repository).save(sector);

        assertThrows(SectorGenericErrorException.class, () -> service.saveSector(sector));
    }

    @Test
    void deveRetornarSetorQuandoEncontrado() {
        Sector sector = new Sector();
        when(repository.findById("SEC01")).thenReturn(Optional.of(sector));

        Optional<Sector> result = service.findSectorById("SEC01");

        assertTrue(result.isPresent());
        assertEquals(sector, result.get());
    }

    @Test
    void deveLancarExcecaoAoBuscarSetor() {
        when(repository.findById("SEC02")).thenThrow(new RuntimeException("Erro"));
        assertThrows(SectorGenericErrorException.class, () -> service.findSectorById("SEC02"));
    }
}
