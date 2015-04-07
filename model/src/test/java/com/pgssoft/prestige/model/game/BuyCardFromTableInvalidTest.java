package com.pgssoft.prestige.model.game;

import com.pgssoft.prestige.model.enums.CardLevel;
import com.pgssoft.prestige.model.enums.Player;
import com.pgssoft.prestige.model.exceptions.InvalidMoveException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BuyCardFromTableInvalidTest {
    private BaseGameState game;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        game = new BaseGameState(3);
    }

    @Test
    public void invalidPlayer() {
        exception.expect(InvalidMoveException.class);
        game.makeMoveBuyCardFromTable(Player.PLAYER3, CardLevel.ONE, 0);
    }
}