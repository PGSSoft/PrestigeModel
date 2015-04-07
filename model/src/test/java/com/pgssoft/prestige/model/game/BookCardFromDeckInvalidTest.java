package com.pgssoft.prestige.model.game;

import com.pgssoft.prestige.model.enums.CardLevel;
import com.pgssoft.prestige.model.enums.Player;
import com.pgssoft.prestige.model.exceptions.InvalidMoveException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BookCardFromDeckInvalidTest {
    private BaseGameState game;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        game = new BaseGameState(3, TestData.cardsTesting, TestData.visitorCardsTesting);
    }

    @Test
    public void invalidPlayer() {
        exception.expect(InvalidMoveException.class);
        game.makeMoveBookCardFromDeck(Player.PLAYER3, CardLevel.TWO, 0, 0, 0, 0, 0, 0);
    }

    @Test
    public void invalidCard() {
        // deck is empty
        exception.expect(InvalidMoveException.class);
        game.makeMoveBookCardFromDeck(Player.PLAYER0, CardLevel.ONE, 0, 0, 0, 0, 0, 0);
    }

    @Test
    public void moreThenThreeCards() {
        game.prepareDebugSetup(new EmptyDebugSetup() {
            @Override
            public int[] getPlayer0BookedCards() {
                return new int[] {8,9,10};
            }
        });

        exception.expect(InvalidMoveException.class);
        game.makeMoveBookCardFromDeck(Player.PLAYER0, CardLevel.THREE, 0, 0, 0, 0, 0, 0);
    }

    @Test
    public void notEnoughCoinsToRemove() {
        exception.expect(InvalidMoveException.class);
        game.makeMoveBookCardFromDeck(Player.PLAYER0, CardLevel.THREE, 1, 0, 0, 0, 0, 0);
    }

    @Test
    public void wouldHaveMoreThanTenCoins() {
        game.prepareDebugSetup(new EmptyDebugSetup() {
            @Override
            public CoinSet getPlayer0CoinSet() {
                return new CoinSet(5,5,0,0,0,0);
            }

            @Override
            public CoinSet getTableCoinSet() {
                return new CoinSet(0, 0, 5, 5, 5, 5);
            }
        });

        exception.expect(InvalidMoveException.class);
        game.makeMoveBookCardFromDeck(Player.PLAYER0, CardLevel.THREE, 0, 0, 0, 0, 0, 0);
    }

    @Test
    public void bookCardRemoveEvenIfDoesNotHaveTo() {
        BaseGameState game = new BaseGameState(3, TestData.cardsTesting, TestData.visitorCardsTesting);
        game.prepareDebugSetup(new EmptyDebugSetup() {
            @Override
            public CoinSet getPlayer0CoinSet() {
                return new CoinSet(5, 0, 0, 0, 0, 0);
            }

            @Override
            public CoinSet getTableCoinSet() {
                return new CoinSet(0, 5, 5, 5, 5, 5);
            }
        });

        exception.expect(InvalidMoveException.class);
        game.makeMoveBookCardFromDeck(Player.PLAYER0, CardLevel.THREE, 1, 0, 0, 0, 0, 0);

        Assert.assertEquals(0, game.getPlayerCoins(Player.PLAYER0).getJokers());
        Assert.assertEquals(5, game.getPlayerCoins(Player.PLAYER0).getRedCoins());
        Assert.assertEquals(0, game.getTableCoins().getRedCoins());
        Assert.assertEquals(5, game.getTableCoins().getJokers());
    }
}