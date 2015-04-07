package com.pgssoft.prestige.model.game;

import com.pgssoft.prestige.model.enums.Player;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CanBuyCardFromHandTest {
    private BaseGameState game;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        game = new BaseGameState(3, TestData.cardsTesting, TestData.visitorCardsTesting);
    }

    @Test
    public void enoughCoins() {
        game.prepareDebugSetup(new EmptyDebugSetup() {
            @Override
            public CoinSet getPlayer0CoinSet() {
                return new CoinSet(0, 0, 0, 2, 0, 0);
            }

            @Override
            public int[] getPlayer0BookedCards() {
                return new int[] {0};
            }
        });

        //we do have enough coins
        CoinSet cs = game.canBuyCardFromHand(Player.PLAYER0, 0);
        Assert.assertNotEquals(null, cs);
        Assert.assertEquals(2, cs.getBlackCoins());
        Assert.assertEquals(2, game.getPlayerCoins(Player.PLAYER0).getBlackCoins());
    }

    @Test
    public void enoughJokers() {
        game.prepareDebugSetup(new EmptyDebugSetup() {
            @Override
            public CoinSet getPlayer0CoinSet() {
                return new CoinSet(0, 0, 0, 0, 0, 2);
            }

            @Override
            public int[] getPlayer0BookedCards() {
                return new int[] {0};
            }
        });

        //we do have enough jokers
        CoinSet cs = game.canBuyCardFromHand(Player.PLAYER0, 0);
        Assert.assertNotEquals(null, cs);
        Assert.assertEquals(0, cs.getBlackCoins());
        Assert.assertEquals(2, cs.getJokers());
    }

    @Test
    public void enoughBonuses() {
        game.prepareDebugSetup(new EmptyDebugSetup() {
            @Override
            public CoinSet getPlayer0CoinSet() {
                return new CoinSet(0, 0, 0, 0, 0, 0);
            }

            @Override
            public int[] getPlayer0BookedCards() {
                return new int[] {0};
            }

            @Override
            public int[] getPlayer0OwnedCards() {
                return new int[] {1, 2};
            }
        });

        //we do have enough bonuses
        CoinBonusSet cs = (CoinBonusSet)game.canBuyCardFromHand(Player.PLAYER0, 0);
        Assert.assertNotEquals(null, cs);
        Assert.assertEquals(0, cs.getBlackCoins());
        Assert.assertEquals(0, cs.getJokers());
        Assert.assertEquals(2, cs.getBlackBonuses());
    }

    @Test
    public void enoughEverything() {
        game.prepareDebugSetup(new EmptyDebugSetup() {
            @Override
            public CoinSet getPlayer0CoinSet() {
                return new CoinSet(0, 0, 0, 2, 0, 2);
            }

            @Override
            public int[] getPlayer0BookedCards() {
                return new int[] {12};
            }

            @Override
            public int[] getPlayer0OwnedCards() {
                return new int[] {1, 2};
            }
        });

        //everything test
        CoinBonusSet cs = (CoinBonusSet)game.canBuyCardFromHand(Player.PLAYER0, 0);
        Assert.assertNotEquals(null, cs);
        Assert.assertEquals(2, cs.getBlackCoins());
        Assert.assertEquals(1, cs.getJokers());
        Assert.assertEquals(2, cs.getBlackBonuses());
    }
}