package com.pgssoft.prestige.model.game;

/**
 * Created by rtulaza on 2014-11-17.
 */
public class EmptyDebugSetup implements IDebugSetup {
    @Override
    public CoinSet getPlayer0CoinSet() {
        return null;
    }

    @Override
    public CoinSet getTableCoinSet() {
        return null;
    }

    @Override
    public int[] getPlayer0BookedCards() {
        return null;
    }

    @Override
    public int[] getPlayer0OwnedCards() {
        return null;
    }

    @Override
    public int[] getPlayer1OwnedCards() {
        return null;
    }
}
