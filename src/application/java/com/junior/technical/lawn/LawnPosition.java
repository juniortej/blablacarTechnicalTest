package com.junior.technical.lawn;

import java.util.Vector;

import com.junior.technical.lawn.mower.Direction;

public class LawnPosition extends LawnVector{
    // Boundaries of the current point, between (0, 0) and (limit.x, limit.y)
    private LawnVector limit;

    private LawnPosition(int x, int y) {
        super(x, y);
    }

    /**
     * Change the coordinate of the Point using the provided {@link Direction}
     * @param direction {@link Direction} to move the Point to
     */
    public void move(Direction direction) {
        switch (direction) {
            case E -> setX(Math.min(getX() + 1, limit.getX()));
            case N -> setY(Math.min(getY() + 1, limit.getY()));
            case W -> setX(Math.max(getX() - 1, 0));
            case S -> setY(Math.max(getY() - 1, 0));
        }
    }

    /**
     * Returns the hypothetical LawnPosition of the object after a given move
     * @param direction Direction to move to
     * @return A String of type {@code "X Y"}
     */
    public String computeNextLawnPosition(Direction direction) {
        return switch (direction) {
            case E -> Math.min(getX() + 1, limit.getX()) + " " + getY();
            case N -> getX() + " " + Math.min(getY() + 1, limit.getY());
            case W -> Math.max(getX() - 1, 0) + " " + getY();
            case S -> getX() + " " + Math.max(getY() - 1, 0);
        };
    }

    public void setLimit(LawnVector limit) {
        this.limit = limit;
    }

    /**
     * Creates a {@link LawnPosition} using another LawnPosition
     * @param x LawnPosition x
     * @param y LawnPosition y
     * @return a new {@link LawnPosition} object
     */
    public static LawnPosition of(int x, int y) {
        return new LawnPosition(x, y);
    }
}
