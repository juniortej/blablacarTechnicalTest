package com.junior.technical.lawn.mower;

import com.junior.technical.lawn.Lawn;

public interface Move {
	   /**
     * Turns the movable 90° left
     */
    void turnLeft();

    /**
     * Turns the movable 90° right
     */
    void turnRight();

    /**
     * Tries to move this object on the given lawn
     * Does nothing if Move can't move on the given lawn
     * @param lawn Lawn to move the Move on
     */
    void moveForward(Lawn lawn);

    /**
     * Gives the next hypothetical position of the Move
     * @return String of type "X Y"
     */
    String computeNextLawnPosition();

    /**
     * Gives the Move position
     * @return String of type "X Y O"
     */
    String getLawnPosition();
}
