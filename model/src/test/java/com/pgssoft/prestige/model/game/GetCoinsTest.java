package com.pgssoft.prestige.model.game;

import com.pgssoft.prestige.model.enums.Player;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class GetCoinsTest {
    private BaseGameState game;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        game = new BaseGameState(3, TestData.cardsTesting, TestData.visitorCardsTesting);
    }

    @Test
    public void getTwoCoins() {
        game.makeMoveGetCoins(Player.PLAYER0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Assert.assertEquals(2, game.getPlayerCoins(Player.PLAYER0).getRedCoins());
        Assert.assertEquals(3, game.getTableCoins().getRedCoins());
    }

    @Test
    public void getThreeCoins() {
        game.makeMoveGetCoins(Player.PLAYER0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0);
        Assert.assertEquals(1, game.getPlayerCoins(Player.PLAYER0).getRedCoins());
        Assert.assertEquals(1, game.getPlayerCoins(Player.PLAYER0).getGreenCoins());
        Assert.assertEquals(1, game.getPlayerCoins(Player.PLAYER0).getBlueCoins());
    }

    @Test
    public void getTwoCoinsRemoveIfDoesHaveTo() {
        game.prepareDebugSetup(new EmptyDebugSetup() {
            @Override
            public CoinSet getPlayer0CoinSet() {
                return new CoinSet(5, 5, 0, 0, 0, 0);
            }

            @Override
            public CoinSet getTableCoinSet() {
                return new CoinSet(0, 0, 5, 5, 5, 5);
            }
        });
        game.makeMoveGetCoins(Player.PLAYER0, 0, 0, 2, 0, 0, 1, 1, 0, 0, 0, 0);
        Assert.assertEquals(4, game.getPlayerCoins(Player.PLAYER0).getRedCoins());
        Assert.assertEquals(4, game.getPlayerCoins(Player.PLAYER0).getGreenCoins());
        Assert.assertEquals(2, game.getPlayerCoins(Player.PLAYER0).getBlueCoins());
        Assert.assertEquals(1, game.getTableCoins().getRedCoins());
        Assert.assertEquals(1, game.getTableCoins().getGreenCoins());
    }

    @Test
    public void getThreeCoinsRemoveIfDoesHaveTo() {
        game.prepareDebugSetup(new EmptyDebugSetup() {
            @Override
            public CoinSet getPlayer0CoinSet() {
                return new CoinSet(5, 5, 0, 0, 0, 0);
            }

            @Override
            public CoinSet getTableCoinSet() {
                return new CoinSet(0, 0, 5, 5, 5, 5);
            }
        });
        game.makeMoveGetCoins(Player.PLAYER0, 0, 0, 1, 1, 1, 1, 2, 0, 0, 0, 0);
        Assert.assertEquals(4, game.getPlayerCoins(Player.PLAYER0).getRedCoins());
        Assert.assertEquals(3, game.getPlayerCoins(Player.PLAYER0).getGreenCoins());
        Assert.assertEquals(1, game.getPlayerCoins(Player.PLAYER0).getBlueCoins());
        Assert.assertEquals(1, game.getPlayerCoins(Player.PLAYER0).getBlackCoins());
        Assert.assertEquals(1, game.getPlayerCoins(Player.PLAYER0).getWhiteCoins());
        Assert.assertEquals(1, game.getTableCoins().getRedCoins());
        Assert.assertEquals(2, game.getTableCoins().getGreenCoins());
    }

    @Test
    public void getTwoCoinsWhenTwoColorsLeft() {
        game.prepareDebugSetup(new EmptyDebugSetup() {
            @Override
            public CoinSet getPlayer0CoinSet() {
                return new CoinSet(0, 0, 0, 0, 0, 0);
            }

            @Override
            public CoinSet getTableCoinSet() {
                return new CoinSet(2, 1, 0, 0, 0, 0);
            }
        });
        game.makeMoveGetCoins(Player.PLAYER0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Assert.assertEquals(1, game.getPlayerCoins(Player.PLAYER0).getRedCoins());
        Assert.assertEquals(1, game.getPlayerCoins(Player.PLAYER0).getGreenCoins());
        Assert.assertEquals(1, game.getTableCoins().getRedCoins());
        Assert.assertEquals(0, game.getTableCoins().getGreenCoins());
    }

    @Test
    public void getOneCoin() {
        game.prepareDebugSetup(new EmptyDebugSetup() {
            @Override
            public CoinSet getPlayer0CoinSet() {
                return new CoinSet(0, 0, 0, 0, 0, 0);
            }

            @Override
            public CoinSet getTableCoinSet() {
                return new CoinSet(0, 1, 0, 0, 0, 0);
            }
        });
        game.makeMoveGetCoins(Player.PLAYER0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Assert.assertEquals(1, game.getPlayerCoins(Player.PLAYER0).getGreenCoins());
        Assert.assertEquals(0, game.getTableCoins().getGreenCoins());
    }
}