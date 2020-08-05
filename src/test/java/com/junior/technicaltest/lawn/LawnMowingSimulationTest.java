package com.junior.technicaltest.lawn;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.org.lidalia.slf4jtest.LoggingEvent.info;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import com.junior.technical.lawn.Lawn;
import com.junior.technical.lawn.LawnMowingSimulation;
import com.junior.technical.lawn.LawnSize;
import com.junior.technical.lawn.mower.Direction;
import com.junior.technical.lawn.mower.Mower;
import com.junior.technical.lawn.mower.Worker;

import uk.org.lidalia.slf4jtest.TestLogger;
import uk.org.lidalia.slf4jtest.TestLoggerFactory;

public class LawnMowingSimulationTest {

    private final TestLogger logger = TestLoggerFactory.getTestLogger(LawnMowingSimulation.class);

    @Test
    public void should_start_simulation() {
        // GIVEN
        Mower mower = Mower.of(0, 0, Direction.E);
        Worker worker = Worker.of(
                Lawn.of(LawnSize.of(5, 5), Collections.singletonList(mower)),
                mower,
                Collections.emptyList()
        );
        LawnMowingSimulation simulation = LawnMowingSimulation.of(Collections.singletonList(worker));

        // WHEN

        // THEN
        simulation.startSimulation();
        assertThat(logger.getAllLoggingEvents()).contains(info("0 0 E"));
    }
}
