package com.pgssoft.prestige.model.game;

import com.pgssoft.prestige.model.enums.CardLevel;
import com.pgssoft.prestige.model.enums.Player;
import com.pgssoft.prestige.model.exceptions.InvalidMoveException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CanBuyCardFromTableInvalid {
    private BaseGameState game;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        game = new BaseGameState(3, TestData.cardsTesting, TestData.visitorCardsTesting);
    }


    @Test
    public void invalidPlayer() {
        Assert.assertEquals(null, game.canBuyCardFromTable(Player.PLAYER0, CardLevel.ONE, 0));
    }

    @Test
    public void cantBuyCard() {
        game.prepareDebugSetup(new EmptyDebugSetup() {
            @Override
            public int[] getPlayer0BookedCards() {
                return new int[] {0};
            }
        });

        // card is not on table
        exception.expect(InvalidMoveException.class);
        game.canBuyCardFromTable(Player.PLAYER0, CardLevel.ONE, 0);

        //we don't have enough coins
        Assert.assertEquals(null, game.canBuyCardFromTable(Player.PLAYER0, CardLevel.ONE, 1));
    }
}