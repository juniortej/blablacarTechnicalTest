package com.junior.technicaltest.mower;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import com.junior.technical.lawn.Lawn;
import com.junior.technical.lawn.LawnSize;
import com.junior.technical.lawn.mower.Direction;
import com.junior.technical.lawn.mower.Mower;


public class MowerTest {
    @Test
    public void should_create_mower_using_builder_method() {
        // GIVEN
        Mower mower = Mower.of(0, 0, Direction.E);

        // WHEN

        // THEN
        assertThat(mower.toString()).isEqualTo("0 0 E");
    }

    @Test
    public void should_turn_mower_to_left_right() {
        Mower mower = Mower.of(0, 0, Direction.E);
        assertThat(mower.toString()).isEqualTo("0 0 E");

        mower.turnLeft();
        assertThat(mower.toString()).isEqualTo("0 0 N");

        mower.turnRight();
        assertThat(mower.toString()).isEqualTo("0 0 E");
    }

    @Test
    public void should_move_mower_forward() {
        // GIVEN
        Mower mower = Mower.of(0, 0, Direction.E);
        Lawn lawn = Lawn.of(LawnSize.of(5, 5), Collections.singletonList(mower));
        mower.setLawnSize( lawn.getLawnSize());

        // WHEN

        // THEN
        mower.moveForward(lawn);
        assertThat(mower.toString()).isEqualTo("1 0 E");
    }

    @Test
    public void should_block_mower() {
        // GIVEN
        Mower mower = Mower.of(0, 0, Direction.E);
        Mower blocker = Mower.of(1, 0, Direction.E);
        Lawn lawn = Lawn.of(LawnSize.of(5, 5), Arrays.asList(mower, blocker));
        mower.setLawnSize( lawn.getLawnSize());

        // WHEN

        // THEN
        mower.moveForward(lawn);
        assertThat(mower.toString()).isEqualTo("0 0 E");
    }

    @Test
    public void should_return_to_default_pos() {
        Mower mower = Mower.of(1, 1, Direction.E);
        Lawn lawn = Lawn.of(LawnSize.of(5, 5), Collections.singletonList(mower));
        mower.setLawnSize( lawn.getLawnSize());

        mower.turnLeft();
        mower.moveForward(lawn);
        mower.turnLeft();
        mower.moveForward(lawn);
        mower.turnLeft();
        mower.moveForward(lawn);
        mower.turnLeft();
        mower.moveForward(lawn);
        assertThat(mower.toString()).isEqualTo("1 1 E");
    }

    @Test
    public void should_not_exit_lawn_boundaries() {
        Mower mower = Mower.of(0, 0, Direction.W);
        Lawn lawn = Lawn.of(LawnSize.of(5, 5), Collections.singletonList(mower));
        mower.setLawnSize( lawn.getLawnSize());

        mower.moveForward(lawn);
        assertThat(mower.toString()).isEqualTo("0 0 W");
    }
}
