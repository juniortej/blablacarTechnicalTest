package com.junior.technical.lawn;

public class LawnVector {
  private int x;
    private int y;

    protected LawnVector(int x, int y) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Members of the class Point must be positive");
        }
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    protected void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    protected void setY(int y) {
        this.y = y;
    }
}
