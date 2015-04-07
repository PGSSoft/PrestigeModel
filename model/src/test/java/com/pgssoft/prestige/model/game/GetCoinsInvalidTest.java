package com.pgssoft.prestige.model.game;

import com.pgssoft.prestige.model.enums.Player;
import com.pgssoft.prestige.model.exceptions.InvalidMoveException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class GetCoinsInvalidTest {
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
        game.makeMoveGetCoins(Player.PLAYER3, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    }

    @Test
    public void invalidCoins() {
        // more then 2 or 3 in total
        exception.expect(InvalidMoveException.class);
        game.makeMoveGetCoins(Player.PLAYER0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0);

        // less then 2 or 3 in total when there are coins on table
        exception.expect(InvalidMoveException.class);
        game.makeMoveGetCoins(Player.PLAYER0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

        // 2 but different when there are coins on table
        exception.expect(InvalidMoveException.class);
        game.makeMoveGetCoins(Player.PLAYER0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0);

        // 3 but same
        exception.expect(InvalidMoveException.class);
        game.makeMoveGetCoins(Player.PLAYER0, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0);


        game.prepareDebugSetup(new EmptyDebugSetup() {
            @Override
            public CoinSet getPlayer0CoinSet() {
                return new CoinSet(0, 0, 0, 0, 0, 0);
            }

            @Override
            public CoinSet getTableCoinSet() {
                return new CoinSet(0, 3, 5, 5, 5, 5);
            }
        });

        // 3 but not enough coins
        exception.expect(InvalidMoveException.class);
        game.makeMoveGetCoins(Player.PLAYER0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0);

        // 2 but not enough coins
        exception.expect(InvalidMoveException.class);
        game.makeMoveGetCoins(Player.PLAYER0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0);

        // player does not have enough coins to remove (0)
        exception.expect(InvalidMoveException.class);
        game.makeMoveGetCoins(Player.PLAYER0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0);


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

        // player would have more then 10 coins
        exception.expect(InvalidMoveException.class);
        game.makeMoveGetCoins(Player.PLAYER0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0);
    }

    @Test
    public void getTwoCoinsRemoveEvenIfDoesNotHaveTo() {
        game.prepareDebugSetup(new EmptyDebugSetup() {
            @Override
            public CoinSet getPlayer0CoinSet() {
                return new CoinSet(2, 0, 0, 0, 0, 0);
            }

            @Override
            public CoinSet getTableCoinSet() {
                return new CoinSet(3, 5, 5, 5, 5, 5);
            }
        });
        exception.expect(InvalidMoveException.class);
        game.makeMoveGetCoins(Player.PLAYER0, 0, 2, 0, 0, 0, 1, 0, 0, 0, 0, 0);
        Assert.assertEquals(2, game.getPlayerCoins(Player.PLAYER0).getRedCoins());
        Assert.assertEquals(3, game.getTableCoins().getRedCoins());
        Assert.assertEquals(0, game.getPlayerCoins(Player.PLAYER0).getGreenCoins());
        Assert.assertEquals(5, game.getTableCoins().getGreenCoins());
    }

/*    @Test
    public void removeMoreCardsThenNeeded() {

    }*/
}