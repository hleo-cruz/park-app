package com.br.park.services.chain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CapacityLowerThan50PerCentChainTest {

    private CapacityLowerThan75PerCentChain nextChain;
    private CapacityLowerThan50PerCentChain service;

    @BeforeEach
    void setUp() {
        nextChain = mock(CapacityLowerThan75PerCentChain.class);
        service = new CapacityLowerThan50PerCentChain(nextChain);
    }

    @Test
    void deveRetornarRegraQuandoLotacaoMenorOuIgualA50() {
        TestCapacityResultDTO expected = new TestCapacityResultDTO();
        try (MockedStatic<TestCapacityResultFactory> factoryMock = mockStatic(TestCapacityResultFactory.class)) {
            factoryMock.when(TestCapacityResultFactory::lowerThan50Percent)
                    .thenReturn(expected);

            TestCapacityResultDTO result = service.evaluate(40);

            assertEquals(expected, result);
            factoryMock.verify(TestCapacityResultFactory::lowerThan50Percent);
            verifyNoInteractions(nextChain);
        }
    }

    @Test
    void deveDelegarQuandoLotacaoMaiorQue50() {
        TestCapacityResultDTO delegated = new TestCapacityResultDTO();
        when(nextChain.evaluate(60)).thenReturn(delegated);

        TestCapacityResultDTO result = service.evaluate(60);

        assertEquals(delegated, result);
        verify(nextChain).evaluate(60);
    }
}
