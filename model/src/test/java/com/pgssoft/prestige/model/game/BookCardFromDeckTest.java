package com.pgssoft.prestige.model.game;

import com.pgssoft.prestige.model.enums.CardLevel;
import com.pgssoft.prestige.model.enums.Player;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;

public class BookCardFromDeckTest {
    private BaseGameState game;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        game = new BaseGameState(3, TestData.cardsTesting, TestData.visitorCardsTesting);
    }

    @Test
    public void bookCardNormal() {
        Assert.assertNull(game.getPlayerBookedCards(Player.PLAYER0)[0]);
        Assert.assertNull(game.getPlayerBookedCards(Player.PLAYER0)[1]);
        Assert.assertNull(game.getPlayerBookedCards(Player.PLAYER0)[2]);
        game.makeMoveBookCardFromDeck(Player.PLAYER0, CardLevel.THREE, 0, 0, 0, 0, 0, 0);

        Assert.assertNotNull(game.getPlayerBookedCards(Player.PLAYER0)[0]);
        Assert.assertNull(game.getPlayerBookedCards(Player.PLAYER0)[1]);
        Assert.assertNull(game.getPlayerBookedCards(Player.PLAYER0)[2]);
        Assert.assertEquals(1, game.getPlayerCoins(Player.PLAYER0).getJokers());

        game.makeMoveBookCardFromDeck(Player.PLAYER0, CardLevel.THREE, 0, 0, 0, 0, 0, 0);

        Assert.assertNotNull(game.getPlayerBookedCards(Player.PLAYER0)[0]);
        Assert.assertNotNull(game.getPlayerBookedCards(Player.PLAYER0)[1]);
        Assert.assertNull(game.getPlayerBookedCards(Player.PLAYER0)[2]);
        Assert.assertEquals(2, game.getPlayerCoins(Player.PLAYER0).getJokers());
    }

    @Test
    public void bookCardRemoveIfDoesHaveTo() {
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

        Assert.assertEquals(0, game.getPlayerCoins(Player.PLAYER0).getJokers());
        Assert.assertEquals(5, game.getPlayerCoins(Player.PLAYER0).getRedCoins());
        Assert.assertEquals(5, game.getTableCoins().getJokers());
        Assert.assertEquals(0, game.getTableCoins().getRedCoins());

        Card c = game.getTableCards(CardLevel.THREE)[0];
        game.makeMoveBookCardFromDeck(Player.PLAYER0, CardLevel.THREE, 1, 0, 0, 0, 0, 0);

        Assert.assertTrue(Arrays.asList(game.getPlayerBookedCards(Player.PLAYER0)).contains(c));
        Assert.assertEquals(1, game.getPlayerCoins(Player.PLAYER0).getJokers());
        Assert.assertEquals(4, game.getPlayerCoins(Player.PLAYER0).getRedCoins());
        Assert.assertEquals(4, game.getTableCoins().getJokers());
        Assert.assertEquals(1, game.getTableCoins().getRedCoins());
    }

}