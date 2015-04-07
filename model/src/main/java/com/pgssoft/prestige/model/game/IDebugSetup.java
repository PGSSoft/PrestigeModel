package com.pgssoft.prestige.model.game;

/**
 * Created by Tomek on 2014-11-15.
 */
public interface IDebugSetup {
    CoinSet getPlayer0CoinSet();
    CoinSet getTableCoinSet();
    int[] getPlayer0BookedCards();
    int[] getPlayer0OwnedCards();
    int[] getPlayer1OwnedCards();
}
