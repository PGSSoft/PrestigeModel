package com.pgssoft.prestige.model.game;

import com.pgssoft.prestige.model.enums.CardLevel;
import com.pgssoft.prestige.model.enums.Player;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PrestigeTest {
    private BaseGameState game;

    @Before
    public void setUp() throws Exception {
        game = new BaseGameState(3, TestData.cardsTesting, TestData.visitorCardsTesting);
    }

    @Test
    public void getPrestige() {

        game.prepareDebugSetup(new EmptyDebugSetup() {
            @Override
            public CoinSet getPlayer0CoinSet() {
                return new CoinSet(0, 0, 0, 4, 0, 0);
            }
        });
        game.makeMoveBuyCardFromTable(Player.PLAYER0, CardLevel.ONE, 0);
        game.makeMoveBuyCardFromTable(Player.PLAYER0, CardLevel.ONE, 1);

        Assert.assertEquals(1, game.getPlayerCoins(Player.PLAYER0).getBlackCoins());
        Assert.assertEquals(1, game.getPlayerVisitors(Player.PLAYER0).size());
        Assert.assertEquals(9, game.getPrestige(Player.PLAYER0));
    }
}