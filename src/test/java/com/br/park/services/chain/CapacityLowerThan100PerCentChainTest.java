package com.br.park.services.chain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CapacityLowerThan100PerCentChainTest {

    private CapacityLowerThan100PerCentChain service;

    @BeforeEach
    void setUp() {
        service = new CapacityLowerThan100PerCentChain();
    }

    @Test
    void deveRetornarRegraFinalIndependenteDaLotacao() {
        TestCapacityResultDTO expected = new TestCapacityResultDTO();
        try (MockedStatic<TestCapacityResultFactory> factoryMock = mockStatic(TestCapacityResultFactory.class)) {
            factoryMock.when(TestCapacityResultFactory::lowerThan100Percent)
                    .thenReturn(expected);

            TestCapacityResultDTO result = service.evaluate(95);

            assertEquals(expected, result);
            factoryMock.verify(TestCapacityResultFactory::lowerThan100Percent);
        }
    }
}
