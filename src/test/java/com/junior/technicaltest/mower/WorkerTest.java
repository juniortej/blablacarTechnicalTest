package com.junior.technicaltest.mower;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import org.junit.jupiter.api.Test;

import com.junior.technical.lawn.Lawn;
import com.junior.technical.lawn.LawnSize;
import com.junior.technical.lawn.mower.Direction;
import com.junior.technical.lawn.mower.Move;
import com.junior.technical.lawn.mower.Mower;
import com.junior.technical.lawn.mower.Worker;


public class WorkerTest {
    @Test
    public void should_create_and_run_worker() throws Exception {
        List<Consumer<Move>> program = new ArrayList<>();
        Mower mower = Mower.of(1, 1, Direction.E);
        Lawn lawn = Lawn.of(LawnSize.of(5, 5), Collections.singletonList(mower));

        Worker worker = Worker.of(lawn, mower, program);
        worker.start();
        worker.join();
        assertThat(worker.getMove().toString()).isEqualTo("1 1 E");
    }
}
