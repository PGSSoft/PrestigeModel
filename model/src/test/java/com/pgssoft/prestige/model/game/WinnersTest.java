package com.pgssoft.prestige.model.game;

import com.pgssoft.prestige.model.enums.Player;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class WinnersTest {
    private BaseGameState game;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        game = new BaseGameState(3, TestData.cardsTesting, TestData.visitorCardsTesting);
    }

    @Test
    public void noWinners() {
        Assert.assertNull(game.getWinners());
    }

    @Test
    public void oneWinner() {
        game.prepareDebugSetup(new EmptyDebugSetup() {
            @Override
            public int[] getPlayer0OwnedCards() {
                return new int[] {12,13,14,15,16,17,18,19};
            }
        });

        Assert.assertEquals(1, game.getWinners().size());
        Assert.assertEquals(Player.PLAYER0, game.getWinners().get(0));
    }

    @Test
    public void oneWinnerPrestigeDraw() {
        game.prepareDebugSetup(new EmptyDebugSetup() {
            @Override
            public int[] getPlayer0OwnedCards() {
                return new int[] {11,12,13,14,15,16,17,18,19};
            }

            @Override
            public int[] getPlayer1OwnedCards() {
                return new int[] {0,1,2,3,6,7,8};
            }
        });

        Assert.assertEquals(1, game.getWinners().size());
        Assert.assertEquals(Player.PLAYER0, game.getWinners().get(0));
    }

    @Test
    public void draw() {
        game.prepareDebugSetup(new EmptyDebugSetup() {
            @Override
            public int[] getPlayer0OwnedCards() {
                return new int[] {12,13,14,15,16,17,18,19};
            }

            @Override
            public int[] getPlayer1OwnedCards() {
                return new int[] {0,1,2,6,7,8,9,10};
            }
        });

        Assert.assertEquals(2, game.getWinners().size());
        Assert.assertTrue(game.getWinners().contains(Player.PLAYER0));
        Assert.assertTrue(game.getWinners().contains(Player.PLAYER1));
    }
}