package com.pgssoft.prestige.model.exceptions;

/**
 * Created by tzielinski on 2014-11-14.
 */

/**
 * Exception thrown while doing an illegal move on the game state.
 */
public class InvalidMoveException extends RuntimeException {

    public InvalidMoveException(String s) {
        super(s);
    }
}
