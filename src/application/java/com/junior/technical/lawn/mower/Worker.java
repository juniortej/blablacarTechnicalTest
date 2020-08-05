package com.junior.technical.lawn.mower;

import java.util.List;
import java.util.function.Consumer;

import com.junior.technical.lawn.Lawn;

public class Worker extends Thread{
    private final Lawn lawn;
    private final Move move;
    private final List<Consumer<Move>> program;

    private Worker(Lawn lawn, Move Move, List<Consumer<Move>> program) {
        this.lawn = lawn;
        this.move = Move;
        this.program = program;
    }

    @Override
    public void run() {
        for (Consumer<Move> consumer: program) {
            synchronized (lawn) {
                consumer.accept(move);
            }
        }
    }

    /**
     * Call once the worker is done running
     * @return The position and orientation of the mower
     */
    public Move getMove() {
        return move;
    }

    public static Worker of(Lawn lawn, Move Move, List<Consumer<Move>> program) {
        return new Worker(lawn, Move, program);
    }
}
