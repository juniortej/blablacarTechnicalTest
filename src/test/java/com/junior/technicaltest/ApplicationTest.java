package com.junior.technicaltest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.junior.technical.Application;
import com.junior.technical.lawn.LawnMowingSimulation;
import com.junior.technical.lawn.builder.LawnBuilder;


public class ApplicationTest {
    @Mock
    private LawnBuilder lawnBuilder;

    @InjectMocks
    private Application application;

    @Test
    public void should_run_a_simulation_without_errors() {
        LawnMowingSimulation simulationMock = mock(LawnMowingSimulation.class);

        when(lawnBuilder.buildSimulation(any(List.class))).thenReturn(simulationMock);

        application.run(null);
    }
}
