package com.pgssoft.prestige.model.game;

import com.pgssoft.prestige.model.enums.CardLevel;
import com.pgssoft.prestige.model.enums.Player;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;

public class BookCardFromTableTest {
    private BaseGameState game;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        game = new BaseGameState(3, TestData.cardsTesting, TestData.visitorCardsTesting);
    }


    @Test
    public void bookCardNormal() {
        Card c = game.getTableCards(CardLevel.ONE)[0];
        game.makeMoveBookCardFromTable(Player.PLAYER0, CardLevel.ONE, 0, 0, 0, 0, 0, 0, 0);
        Assert.assertTrue(Arrays.asList(game.getPlayerBookedCards(Player.PLAYER0)).contains(c));
        Assert.assertEquals(1, game.getPlayerCoins(Player.PLAYER0).getJokers());
    }

    @Test
    public void bookCardRemoveIfDoesHaveTo() {

        BaseGameState game = new BaseGameState(3, TestData.cardsTesting, TestData.visitorCardsTesting);
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

        Card c = game.getTableCards(CardLevel.ONE)[0];
        game.makeMoveBookCardFromTable(Player.PLAYER0, CardLevel.ONE, 0, 1, 0, 0, 0, 0, 0);

        Assert.assertTrue(Arrays.asList(game.getPlayerBookedCards(Player.PLAYER0)).contains(c));
        Assert.assertEquals(1, game.getPlayerCoins(Player.PLAYER0).getJokers());
        Assert.assertEquals(4, game.getPlayerCoins(Player.PLAYER0).getRedCoins());
        Assert.assertEquals(1, game.getTableCoins().getRedCoins());
    }

}