package com.junior.technical.lawn.mower;

import com.junior.technical.lawn.Lawn;
import com.junior.technical.lawn.LawnPosition;
import com.junior.technical.lawn.LawnSize;

public class Mower implements Move{
    private final LawnPosition pos;
    private Direction dir;

    private Mower(LawnPosition pos, Direction dir) {
        this.pos = pos;
        this.dir = dir;
    }

    public void turnLeft() {
        int i = dir.ordinal() + 1;
        dir = Direction.values()[ (i > 3 ? 0 : i) ];
    }

    public void turnRight() {
        int i = dir.ordinal() - 1;
        dir = Direction.values()[ (i < 0 ? 3 : i) ];
    }

    public void moveForward(Lawn lawn) {
        if (lawn.acceptMove(this)){
            pos.move(dir);
        }
    }

    public String computeNextLawnPosition() {
        return pos.computeNextLawnPosition(dir);
    }

    public void setLawnSize(LawnSize lawnSize) {
        pos.setLimit(lawnSize);
    }

    public String getLawnPosition() {
        return pos.getX() + " " + pos.getY();
    }

    @Override
    public String toString() {
        return pos.getX() + " " + pos.getY() + " " + dir;
    }

    public static Mower of(int x, int y, Direction dir) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Mower coordinates must be positive!");
        }
        return new Mower(LawnPosition.of(x, y), dir);
    }

}
