package com.br.park.services.chain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CapacityLowerThan25PerCentChainTest {

    private CapacityLowerThan50PerCentChain nextChain;
    private CapacityLowerThan25PerCentChain service;

    @BeforeEach
    void setUp() {
        nextChain = mock(CapacityLowerThan50PerCentChain.class);
        service = new CapacityLowerThan25PerCentChain(nextChain);
    }

    @Test
    void deveAplicarRegraQuandoLotacaoForMenorOuIgualA25() {
        TestCapacityResultDTO expected = new TestCapacityResultDTO();
        try (MockedStatic<TestCapacityResultFactory> factoryMock = mockStatic(TestCapacityResultFactory.class)) {
            factoryMock.when(TestCapacityResultFactory::lowerThan25Percent)
                    .thenReturn(expected);

            TestCapacityResultDTO result = service.evaluate(20);

            assertEquals(expected, result);
            factoryMock.verify(TestCapacityResultFactory::lowerThan25Percent);
            verifyNoInteractions(nextChain);
        }
    }

    @Test
    void deveDelegarParaProximaRegraQuandoLotacaoMaiorQue25() {
        TestCapacityResultDTO delegated = new TestCapacityResultDTO();
        when(nextChain.evaluate(30)).thenReturn(delegated);

        TestCapacityResultDTO result = service.evaluate(30);

        assertEquals(delegated, result);
        verify(nextChain).evaluate(30);
    }
}
