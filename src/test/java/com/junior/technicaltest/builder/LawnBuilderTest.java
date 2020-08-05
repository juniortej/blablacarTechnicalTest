package com.junior.technicaltest.builder;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.junior.technical.exception.SimulationException;
import com.junior.technical.lawn.LawnMowingSimulation;
import com.junior.technical.lawn.builder.LawnBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class LawnBuilderTest {
    @Test
    public void should_create_simulation_object() {
        // GIVEN
        LawnBuilder builder = new LawnBuilder();
        List<String> lines = Arrays.asList(
                "5 5",
                "1 2 N",
                "LFLFLFLFF",
                "3 3 E",
                "FFRFFRFRRF"
        );

        // WHEN

        // THEN
        LawnMowingSimulation simulation = builder.buildSimulation(lines);
        assertThat(simulation).isNotNull();
    }

    @Test
    public void should_throw_lawn_simulation_exception_for_malformed_size() {

        // GIVEN
        LawnBuilder builder = new LawnBuilder();
        List<String> lines = Arrays.asList(
                "5 5 5",
                "1 2 N",
                "LFLFLFLFF",
                "3 3 E",
                "FFRFFRFRRF"
        );

        // WHEN

        // THEN
        assertThatThrownBy(() -> builder.buildSimulation(lines))
                .isInstanceOf(SimulationException.class)
                .hasMessageContaining("Lawn size line is not valid");
    }

    @Test
    public void should_throw_lawn_simulation_exception_for_malformed_size_number() {

        // GIVEN
        LawnBuilder builder = new LawnBuilder();
        List<String> lines = Arrays.asList(
                "A B",
                "1 2 N",
                "LFLFLFLFF",
                "3 3 E",
                "FFRFFRFRRF"
        );

        // WHEN

        // THEN
        assertThatThrownBy(() -> builder.buildSimulation(lines))
                .isInstanceOf(SimulationException.class)
                .hasMessageContaining("Couldn't parse size");
    }

    @Test
    public void should_throw_lawn_simulation_exception_for_malformed_mower() {

        // GIVEN
        LawnBuilder builder = new LawnBuilder();
        List<String> lines = Arrays.asList(
                "5 5",
                "1 1 2 N",
                "LFLFLFLFF",
                "3 3 E",
                "FFRFFRFRRF"
        );

        // WHEN

        // THEN
        assertThatThrownBy(() -> builder.buildSimulation(lines))
                .isInstanceOf(SimulationException.class)
                .hasMessageContaining("Mower position line is not valid");
    }

    @Test
    public void should_throw_lawn_simulation_exception_for_malformed_mower_number() {

        // GIVEN
        LawnBuilder builder = new LawnBuilder();
        List<String> lines = Arrays.asList(
                "5 5",
                "A 2 N",
                "LFLFLFLFF",
                "3 3 E",
                "FFRFFRFRRF"
        );

        // WHEN

        // THEN
        assertThatThrownBy(() -> builder.buildSimulation(lines))
                .isInstanceOf(SimulationException.class)
                .hasCauseExactlyInstanceOf(NumberFormatException.class)
                .hasMessageContaining("Couldn't parse mower");
    }

    @Test
    public void should_throw_lawn_simulation_exception_for_malformed_mower_direction() {

        // GIVEN
        LawnBuilder builder = new LawnBuilder();
        List<String> lines = Arrays.asList(
                "5 5",
                "1 2 A",
                "LFLFLFLFF",
                "3 3 E",
                "FFRFFRFRRF"
        );

        // WHEN

        // THEN
        assertThatThrownBy(() -> builder.buildSimulation(lines))
                .isInstanceOf(SimulationException.class)
                .hasCauseExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Couldn't parse mower");
    }

    @Test
    public void should_throw_lawn_simulation_exception_for_malformed_orders() {

        // GIVEN
        LawnBuilder builder = new LawnBuilder();
        List<String> lines = Arrays.asList(
                "5 5",
                "1 2 N",
                "LFLFLFLFO",
                "3 3 E",
                "FFRFFRFRRF"
        );

        // WHEN

        // THEN
        assertThatThrownBy(() -> builder.buildSimulation(lines))
                .isInstanceOf(SimulationException.class)
                .hasCauseExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Couldn't parse orders");
    }
}
