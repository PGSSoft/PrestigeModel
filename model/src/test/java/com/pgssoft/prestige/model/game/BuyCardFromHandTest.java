package com.pgssoft.prestige.model.game;

import com.pgssoft.prestige.model.enums.Player;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BuyCardFromHandTest {
    private BaseGameState game;

    @Before
    public void setUp() throws Exception {
        game = new BaseGameState(3, TestData.cardsTesting, TestData.visitorCardsTesting);
    }

    @Test
    public void enoughCoins() {
        game.prepareDebugSetup(new EmptyDebugSetup() {
            @Override
            public int[] getPlayer0BookedCards() {
                return new int[] {0};
            }

            @Override
            public CoinSet getPlayer0CoinSet() {
                return new CoinSet(0, 0, 0, 2, 0, 0);
            }
        });

        //we do have enough coins
        game.makeMoveBuyCardFromHand(Player.PLAYER0, 0);
        Assert.assertEquals(0, game.getPlayerCoins(Player.PLAYER0).getBlackCoins());
        Assert.assertTrue(game.getPlayerTableCards(Player.PLAYER0).contains(game.getAllCards()[0]));

        Assert.assertEquals(0, game.getPlayerVisitors(Player.PLAYER0).size());
    }

    @Test
    public void enoughJokers() {
        game.prepareDebugSetup(new EmptyDebugSetup() {
            @Override
            public int[] getPlayer0BookedCards() {
                return new int[]{0};
            }

            @Override
            public CoinSet getPlayer0CoinSet() {
                return new CoinSet(0, 0, 0, 0, 0, 2);
            }
        });

        //we do have enough jokers
        game.makeMoveBuyCardFromHand(Player.PLAYER0, 0);
        Assert.assertEquals(0, game.getPlayerCoins(Player.PLAYER0).getJokers());
        Assert.assertTrue(game.getPlayerTableCards(Player.PLAYER0).contains(game.getAllCards()[0]));
    }

    @Test
    public void enoughJokersToBuy2() {
        game.prepareDebugSetup(new EmptyDebugSetup() {
            @Override
            public int[] getPlayer0BookedCards() {
                return new int[]{0, 1};
            }

            @Override
            public CoinSet getPlayer0CoinSet() {
                return new CoinSet(0, 0, 0, 0, 0, 4);
            }
        });

        //we do have enough jokers
        game.makeMoveBuyCardFromHand(Player.PLAYER0, 0);
        Assert.assertEquals(2, game.getPlayerCoins(Player.PLAYER0).getJokers());
        Assert.assertTrue(game.getPlayerTableCards(Player.PLAYER0).contains(game.getAllCards()[0]));

        game.makeMoveBuyCardFromHand(Player.PLAYER0, 1);

        Assert.assertEquals(1, game.getPlayerCoins(Player.PLAYER0).getJokers());
        Assert.assertTrue(game.getPlayerTableCards(Player.PLAYER0).contains(game.getAllCards()[1]));

        Assert.assertEquals(1, game.getPlayerVisitors(Player.PLAYER0).size());
    }

    @Test
    public void enoughBonusPlusJoker() {
        game.prepareDebugSetup(new EmptyDebugSetup() {
            @Override
            public int[] getPlayer0BookedCards() {
                return new int[] {0,1};
            }

            @Override
            public CoinSet getPlayer0CoinSet() {
                return new CoinSet(0, 0, 0, 0, 0, 4);
            }

            @Override
            public int[] getPlayer0OwnedCards() {
                return new int[] {4};
            }
        });

        //we do have enough bonuses
        game.makeMoveBuyCardFromHand(Player.PLAYER0, 0);
        Assert.assertEquals(null, game.getPlayerBookedCards(Player.PLAYER0)[0]);
        Assert.assertEquals(3, game.getPlayerCoins(Player.PLAYER0).getJokers());
        Assert.assertEquals(2, game.getPlayerTableCards(Player.PLAYER0).size());
        Assert.assertTrue(game.getPlayerTableCards(Player.PLAYER0).contains(game.getAllCards()[0]));
    }

    @Test
    public void enoughEverything() {
        game.prepareDebugSetup(new EmptyDebugSetup() {
            @Override
            public CoinSet getPlayer0CoinSet() {
                return new CoinSet(0, 0, 0, 2, 0, 2);
            }

            @Override
            public int[] getPlayer0OwnedCards() {
                return new int[] {0, 1};
            }

            @Override
            public int[] getPlayer0BookedCards() {
                return new int[] {12};
            }
        });

        //everything test
        game.makeMoveBuyCardFromHand(Player.PLAYER0, 0);
        Assert.assertEquals(0, game.getPlayerCoins(Player.PLAYER0).getBlackCoins());
        Assert.assertEquals(1, game.getPlayerCoins(Player.PLAYER0).getJokers());
        Assert.assertEquals(null, game.getPlayerBookedCards(Player.PLAYER0)[0]);
        Assert.assertEquals(3, game.getPlayerTableCards(Player.PLAYER0).size());
    }
}