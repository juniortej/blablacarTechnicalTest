package com.junior.technical.lawn;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.junior.technical.lawn.mower.Worker;

public class LawnMowingSimulation {
    private static final Logger LOGGER = LoggerFactory.getLogger(LawnMowingSimulation.class);

    private final List<Worker> workers;

    private LawnMowingSimulation(List<Worker> workers) {
        this.workers = new ArrayList<>(workers);
    }

    public void startSimulation() {
        try {
            workers.forEach(Worker::start);
            for (Worker worker : workers) {
                worker.join();
                String position = worker.getMove().toString();
                LOGGER.info(position);
            }
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public static LawnMowingSimulation of(List<Worker> workers) {
        return new LawnMowingSimulation(workers);
    }
}
