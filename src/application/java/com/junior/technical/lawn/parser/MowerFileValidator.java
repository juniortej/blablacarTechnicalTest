package com.junior.technical.lawn.parser;

import java.util.Arrays;

import com.junior.technical.exception.SimulationException;
import com.junior.technical.lawn.LawnSize;

public class MowerFileValidator {

    private MowerFileValidator() {}

    public static void validateLawnSize(String[] tokens) {
        if (tokens.length != 2) {
            throw new SimulationException("Lawn size line is not valid: " + Arrays.toString(tokens));
        }
    }

    public static void validateMower(String[] tokens, LawnSize size) {
        if (tokens.length != 3) {
            throw new SimulationException("Mower position line is not valid: " + Arrays.toString(tokens));
        }
        int x = Integer.parseInt(tokens[0]);
        int y = Integer.parseInt(tokens[1]);
        if (x > size.getX() || y > size.getY()) {
            throw new SimulationException("Mower is specified out of bounds: " + Arrays.toString(tokens));
        }
    }
}
