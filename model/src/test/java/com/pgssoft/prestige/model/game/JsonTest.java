package com.pgssoft.prestige.model.game;

import com.pgssoft.prestige.model.enums.Player;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JsonTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void fromJsonTest() {
        BaseGameState game = new BaseGameState(3);
        game.makeMoveGetCoins(Player.PLAYER0, 1,1,1,0,0, 0,0,0,0,0,0);

        String oldJson = game.toJsonBase();
        BaseGameState oldGameState = BaseGameState.deserializeBase(oldJson);

        Assert.assertEquals(game, oldGameState);

        game.makeMoveGetCoins(Player.PLAYER1, 1,1,1,0,0, 0,0,0,0,0,0);
        Assert.assertEquals(1, game.getPlayerCoins(Player.PLAYER0).getRedCoins());
        Assert.assertEquals(1, game.getPlayerCoins(Player.PLAYER1).getRedCoins());

        String newJson = game.toJsonBase();
        BaseGameState newGameState = BaseGameState.deserializeBase(newJson);

        Assert.assertTrue(BaseGameState.isMoveValid(newGameState.getLastMove(), oldGameState, newGameState));
    }

}