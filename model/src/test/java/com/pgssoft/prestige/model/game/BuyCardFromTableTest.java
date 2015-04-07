package com.pgssoft.prestige.model.game;

import com.pgssoft.prestige.model.enums.CardLevel;
import com.pgssoft.prestige.model.enums.Player;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BuyCardFromTableTest {
    private BaseGameState game;

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
        });

        //we do have enough coins
        game.makeMoveBuyCardFromTable(Player.PLAYER0, CardLevel.ONE, 0);
        Assert.assertEquals(0, game.getPlayerCoins(Player.PLAYER0).getBlackCoins());
        Assert.assertEquals(1, game.getPlayerTableCards(Player.PLAYER0).size());

        game.prepareDebugSetup(new EmptyDebugSetup() {
            @Override
            public CoinSet getPlayer0CoinSet() {
                return new CoinSet(0, 0, 0, 2, 0, 0);
            }
        });
        game.makeMoveBuyCardFromTable(Player.PLAYER0, CardLevel.ONE, 1);

        Assert.assertEquals(1, game.getPlayerVisitors(Player.PLAYER0).size());
    }

    @Test
    public void enoughJokers() {
        game.prepareDebugSetup(new EmptyDebugSetup() {
            @Override
            public CoinSet getPlayer0CoinSet() {
                return new CoinSet(0, 0, 0, 0, 0, 2);
            }
        });

        //we do have enough jokers
        game.makeMoveBuyCardFromTable(Player.PLAYER0, CardLevel.ONE, 0);
        Assert.assertEquals(0, game.getPlayerCoins(Player.PLAYER0).getJokers());
        Assert.assertTrue(game.getPlayerTableCards(Player.PLAYER0).contains(game.getAllCards()[0]));
        Assert.assertNull(game.getTableCards(CardLevel.ONE)[0]);
    }

    @Test
    public void enoughBonuses() {
        game.prepareDebugSetup(new EmptyDebugSetup() {
            @Override
            public int[] getPlayer0OwnedCards() {
                return new int[] {0,1};
            }
        });

        //we do have enough bonuses
        game.makeMoveBuyCardFromTable(Player.PLAYER0, CardLevel.ONE, 2);
        Assert.assertTrue(game.getPlayerTableCards(Player.PLAYER0).contains(game.getAllCards()[2]));
        Assert.assertNull(game.getTableCards(CardLevel.ONE)[2]);
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
                return new int[] {0,1};
            }
        });

        //everything test
        game.makeMoveBuyCardFromTable(Player.PLAYER0, CardLevel.THREE, 3);
        Assert.assertEquals(0, game.getPlayerCoins(Player.PLAYER0).getBlackCoins());
        Assert.assertEquals(1, game.getPlayerCoins(Player.PLAYER0).getJokers());
        Assert.assertTrue(game.getPlayerTableCards(Player.PLAYER0).contains(game.getAllCards()[12]));
        Assert.assertEquals(game.getAllCards()[18], game.getTableCards(CardLevel.THREE)[3]);
    }
}