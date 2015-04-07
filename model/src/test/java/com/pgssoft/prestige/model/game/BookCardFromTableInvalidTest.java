package com.pgssoft.prestige.model.game;

import com.pgssoft.prestige.model.enums.CardLevel;
import com.pgssoft.prestige.model.enums.Player;
import com.pgssoft.prestige.model.exceptions.InvalidMoveException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;

public class BookCardFromTableInvalidTest {
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
        game.makeMoveBookCardFromTable(Player.PLAYER3, CardLevel.ONE, 0, 0, 0, 0, 0, 0, 0);
    }


    @Test
    public void invalidCard() {
        game.prepareDebugSetup(new EmptyDebugSetup() {
            @Override
            public int[] getPlayer0BookedCards() {
                return new int[] {0};
            }
        });

        // card is not on table
        exception.expect(InvalidMoveException.class);
        game.makeMoveBookCardFromTable(Player.PLAYER0, CardLevel.ONE, 0, 0, 0, 0, 0, 0, 0);
    }

    @Test
    public void moreThenThreeCards() {
        game.prepareDebugSetup(new EmptyDebugSetup() {
            @Override
            public int[] getPlayer0BookedCards() {
                return new int[] {0,1,2};
            }
        });

        exception.expect(InvalidMoveException.class);
        game.makeMoveBookCardFromTable(Player.PLAYER0, CardLevel.ONE, 1, 0, 0, 0, 0, 0, 0);
    }
    
    @Test
    public void notEnoughCoinsToRemove() {
        exception.expect(InvalidMoveException.class);
        game.makeMoveBookCardFromTable(Player.PLAYER0, CardLevel.ONE, 0, 1, 0, 0, 0, 0, 0);
    }
    
    @Test
    public void wouldHaveMoreThenTenCoins() {
        game.prepareDebugSetup(new EmptyDebugSetup() {
            @Override
            public CoinSet getPlayer0CoinSet() {
                return new CoinSet(5, 5, 0, 0, 0, 0);
            }

            @Override
            public CoinSet getTableCoinSet() {
                return new CoinSet(3, 3, 0, 0, 0, 5);
            }
        });

        exception.expect(InvalidMoveException.class);
        game.makeMoveBookCardFromTable(Player.PLAYER0, CardLevel.ONE, 0, 0, 0, 0, 0, 0, 0);
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

        Card c = game.getTableCards(CardLevel.ONE)[0];
        exception.expect(InvalidMoveException.class);
        game.makeMoveBookCardFromTable(Player.PLAYER0, CardLevel.ONE, 0, 1, 0, 0, 0, 0, 0);

        Assert.assertFalse(Arrays.asList(game.getPlayerBookedCards(Player.PLAYER0)).contains(c));
        Assert.assertEquals(0, game.getPlayerCoins(Player.PLAYER0).getJokers());
        Assert.assertEquals(5, game.getPlayerCoins(Player.PLAYER0).getRedCoins());
        Assert.assertEquals(0, game.getTableCoins().getRedCoins());
        Assert.assertEquals(5, game.getTableCoins().getJokers());
    }
}