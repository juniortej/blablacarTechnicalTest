package com.junior.technical.lawn;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

import com.junior.technical.lawn.mower.Move;
import com.junior.technical.lawn.mower.Mower;


public class Lawn {
    private final LawnSize size;
    private final ConcurrentLinkedDeque<Move> moves;

    private Lawn(int x, int y, List<Move> Moves) {
        this.size = LawnSize.of(x, y);
        this.moves = new ConcurrentLinkedDeque<>(Moves);
    }

    public LawnSize getLawnSize() {
        return size;
    }

    /**
     * Allows (or not) a mower to move to its next position
     * @param mower Mower that wants to move on this Lawn
     * @return true if the mower can move
     *         false otherwise
     */
    public boolean acceptMove(Mower mower) {
        String nextPos = mower.computeNextLawnPosition();
        return moves.stream()
            .map(Move::getLawnPosition)
            .filter(m -> !m.equals(mower.getLawnPosition()))
            .noneMatch(m -> m.equals(nextPos));
    }

    public static Lawn of(LawnSize size, List<Move> Moves) {
        return new Lawn(size.getX(), size.getY(), Moves);
    }
}
