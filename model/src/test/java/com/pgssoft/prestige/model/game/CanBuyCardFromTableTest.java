package com.pgssoft.prestige.model.game;

import com.pgssoft.prestige.model.enums.CardLevel;
import com.pgssoft.prestige.model.enums.Player;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class CanBuyCardFromTableTest {
    private BaseGameState game;
    private final String jsonPath = "model/src/test/json/";

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
        });

        //we do have enough coins
        CoinSet cs = game.canBuyCardFromTable(Player.PLAYER0, CardLevel.ONE, 0);
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
        });

        //we do have enough jokers
        CoinSet cs = game.canBuyCardFromTable(Player.PLAYER0, CardLevel.ONE, 0);
        Assert.assertNotEquals(null, cs);
        Assert.assertEquals(0, cs.getBlackCoins());
        Assert.assertEquals(2, cs.getJokers());
    }

    @Test
    public void enoughBonuses() {
        game.prepareDebugSetup(new EmptyDebugSetup() {
            @Override
            public int[] getPlayer0OwnedCards() {
                return new int[] {0, 1};
            }
        });

        //we do have enough bonuses
        CoinBonusSet cs = (CoinBonusSet)game.canBuyCardFromTable(Player.PLAYER0, CardLevel.ONE, 2);
        Assert.assertNotEquals(null, cs);
        Assert.assertEquals(0, cs.getBlackCoins());
        Assert.assertEquals(0, cs.getJokers());
        Assert.assertEquals(2, cs.getBlackBonuses());
    }

    @Test
    public void enoughBonuses2() throws FileNotFoundException {
        try {
            String s = new File(".").getCanonicalPath();
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String json = new Scanner(new File(jsonPath + "enoughBonuses2.txt")).useDelimiter("\\Z").next();
        BaseGameState game2 = BaseGameState.deserializeBase(json);

        Assert.assertNotNull(game2.canBuyCardFromTable(Player.PLAYER1, CardLevel.ONE, 0));
    }

    @Test
    public void enoughEverything() {
        game.prepareDebugSetup(new EmptyDebugSetup() {
            @Override
            public int[] getPlayer0OwnedCards() {
                return new int[] {0, 1};
            }

            @Override
            public CoinSet getPlayer0CoinSet() {
                return new CoinSet(0, 0, 0, 2, 0, 2);
            }
        });

        //everything test
        CoinBonusSet cs = (CoinBonusSet)game.canBuyCardFromTable(Player.PLAYER0, CardLevel.THREE, 3);
        Assert.assertNotEquals(null, cs);
        Assert.assertEquals(2, cs.getBlackCoins());
        Assert.assertEquals(1, cs.getJokers());
        Assert.assertEquals(2, cs.getBlackBonuses());
    }
}