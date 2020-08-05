package com.junior.technicaltest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.junior.technical.Application;
import com.junior.technical.lawn.LawnMowingSimulation;
import com.junior.technical.lawn.builder.LawnBuilder;

import uk.org.lidalia.slf4jtest.LoggingEvent;
import uk.org.lidalia.slf4jtest.TestLogger;
import uk.org.lidalia.slf4jtest.TestLoggerFactory;


public class OutpoutApplicationTest {

    private final TestLogger logger = TestLoggerFactory.getTestLogger(LawnMowingSimulation.class);

    @Test
    public void should_launch_simulation() {
        new Application(new LawnBuilder()).run(null);

        assertThat(logger.getAllLoggingEvents()).contains(LoggingEvent.info("1 3 N"), LoggingEvent.info("5 1 E"));
    }

    @Test
    public void should_launch_many_mowers_simulation() {
        new Application(new LawnBuilder()).run("classpath:many-mower-test.txt");

        assertThat(logger.getAllLoggingEvents()).hasSize(20);
    }
}
