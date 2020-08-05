package com.junior.technical.lawn.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.junior.technical.exception.SimulationException;
import com.junior.technical.lawn.Lawn;
import com.junior.technical.lawn.LawnMowingSimulation;
import com.junior.technical.lawn.LawnSize;
import com.junior.technical.lawn.mower.Move;
import com.junior.technical.lawn.mower.Order;
import com.junior.technical.lawn.mower.Worker;

import static com.junior.technical.lawn.parser.MowerFileParser.parseMower;
import static com.junior.technical.lawn.parser.MowerFileParser.parseOrders;
import static com.junior.technical.lawn.parser.MowerFileParser.parseSize;

public class LawnBuilder {
    public LawnMowingSimulation buildSimulation(List<String> lines) {
	    LawnSize size = parseSize(lines.get(0)); // lawn size
	
	    List<Move> moves = new ArrayList<>();
	    List<Worker> workers = new ArrayList<>();
	
	    Lawn lawn = Lawn.of(size, moves);
	
	    for (int i = 1; i < lines.size(); i+=2) {
	        Move move = parseMower(lines.get(i), size);
	        if (
	            moves.stream()
	                .anyMatch(m -> m.getLawnPosition().equals(move.getLawnPosition()))
	        ) {
	            throw new SimulationException("Mowers are overlapping. Check the input file.");
	        }
	        List<Order> orders = parseOrders(lines.get(i+1));
	
			@SuppressWarnings("preview")
			List<Consumer<Move>> program = orders.stream().<Consumer<Move>>map(order ->
	                switch (order) {
	                    case L -> Move::turnLeft;
	                    case R -> Move::turnRight;
	                    case F -> (m -> m.moveForward(lawn));
	            })
	            .collect(Collectors.toList());
	
	        moves.add(move);
	        workers.add(Worker.of(lawn, move, program));
	    }
	
	    return LawnMowingSimulation.of(workers);
	}
}
