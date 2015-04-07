package com.pgssoft.prestige.arena.bot;

import com.pgssoft.prestige.model.enums.Player;
import com.pgssoft.prestige.model.game.IBaseGameState;
import com.pgssoft.prestige.model.game.Move;

/**
 * Created by bstokrocki on 2014-11-20.
 */
public interface IBot {
    /**
     * @param state
     * @return Calculated Move for given game state
     */
    public Move makeMove(IBaseGameState state);

    /**
     * @return Player assigned to bot
     */
    public Player getPlayerId();

    /**
     *
     * @return Bot name
     */
    public String getName();
}
