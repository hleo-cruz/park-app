package com.br.park.services.chain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CapacityLowerThan75PerCentChainTest {

    private CapacityLowerThan100PerCentChain nextChain;
    private CapacityLowerThan75PerCentChain service;

    @BeforeEach
    void setUp() {
        nextChain = mock(CapacityLowerThan100PerCentChain.class);
        service = new CapacityLowerThan75PerCentChain(nextChain);
    }

    @Test
    void deveAplicarAumentoQuandoLotacaoAte75() {
        TestCapacityResultDTO expected = new TestCapacityResultDTO();
        try (MockedStatic<TestCapacityResultFactory> factoryMock = mockStatic(TestCapacityResultFactory.class)) {
            factoryMock.when(TestCapacityResultFactory::lowerThan75Percent)
                    .thenReturn(expected);

            TestCapacityResultDTO result = service.evaluate(70);

            assertEquals(expected, result);
            factoryMock.verify(TestCapacityResultFactory::lowerThan75Percent);
            verifyNoInteractions(nextChain);
        }
    }

    @Test
    void deveDelegarQuandoLotacaoMaiorQue75() {
        TestCapacityResultDTO delegated = new TestCapacityResultDTO();
        when(nextChain.evaluate(85)).thenReturn(delegated);

        TestCapacityResultDTO result = service.evaluate(85);

        assertEquals(delegated, result);
        verify(nextChain).evaluate(85);
    }
}
