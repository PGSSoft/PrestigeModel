package com.pgssoft.prestige.model.game;

import com.pgssoft.prestige.model.enums.Player;
import com.pgssoft.prestige.model.exceptions.InvalidMoveException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CanBuyCardFromHandInvalidTest {
    private BaseGameState game;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        game = new BaseGameState(3, TestData.cardsTesting, TestData.visitorCardsTesting);
    }

    @Test
    public void invalidPlayer() {
        game.prepareDebugSetup(new EmptyDebugSetup() {
            @Override
            public int[] getPlayer0BookedCards() {
                return new int[] {0};
            }
        });

        exception.expect(InvalidMoveException.class);
        game.canBuyCardFromHand(Player.PLAYER3, 0);
    }

    @Test
    public void cantBuyCard() {
        // card is not in players hand
        Assert.assertEquals(null, game.canBuyCardFromHand(Player.PLAYER0, 0));

        //we don't have enough coins

        game.prepareDebugSetup(new EmptyDebugSetup() {
            @Override
            public int[] getPlayer0BookedCards() {
                return new int[] {0};
            }
        });

        Assert.assertEquals(null, game.canBuyCardFromHand(Player.PLAYER0, 0));
    }
}