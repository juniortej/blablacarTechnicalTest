package com.junior.technical.lawn.parser;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.junior.technical.exception.SimulationException;
import com.junior.technical.lawn.LawnSize;
import com.junior.technical.lawn.mower.Direction;
import com.junior.technical.lawn.mower.Move;
import com.junior.technical.lawn.mower.Mower;
import com.junior.technical.lawn.mower.Order;

import static com.junior.technical.lawn.parser.MowerFileValidator.validateLawnSize;
import static com.junior.technical.lawn.parser.MowerFileValidator.validateMower;

public class MowerFileParser {

    private MowerFileParser() {}

    public static LawnSize parseSize(String lawnSizeLine) {
        String[] tokens = lawnSizeLine.split(" ");
        try {
            validateLawnSize(tokens);
            return LawnSize.of(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
        } catch (NumberFormatException ex) {
            throw new SimulationException("Couldn't parse size: " + Arrays.toString(tokens), ex);
        }
    }

    public static Move parseMower(String mowerLine, LawnSize size) {
        String[] tokens = mowerLine.split(" ");
        try {
            validateMower(tokens, size);
            Mower mower = Mower.of(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), Direction.valueOf(tokens[2]));
            mower.setLawnSize(size);
            return mower;
        } catch (IllegalArgumentException ex) {
            throw new SimulationException("Couldn't parse mower: " + Arrays.toString(tokens), ex);
        }
    }

    public static List<Order> parseOrders(String ordersLine) {
        String[] tokens = ordersLine.split("");
        try {
            return Stream.of(tokens)
                    .map(Order::valueOf)
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException ex) {
            throw new SimulationException("Couldn't parse orders: " + Arrays.toString(tokens), ex);
        }
    }
}
