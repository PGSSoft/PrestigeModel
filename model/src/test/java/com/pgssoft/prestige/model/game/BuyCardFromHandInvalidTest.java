package com.pgssoft.prestige.model.game;

import com.pgssoft.prestige.model.enums.Player;
import com.pgssoft.prestige.model.exceptions.InvalidMoveException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BuyCardFromHandInvalidTest {
    private BaseGameState game;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        game = new BaseGameState(3, TestData.cardsTesting, TestData.visitorCardsTesting);
    }

    @Test
    public void invalidPlayer() {
        exception.expect(InvalidMoveException.class);
        game.makeMoveBuyCardFromHand(Player.PLAYER3, 0);
    }
}