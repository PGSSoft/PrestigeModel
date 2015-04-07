package com.pgssoft.prestige.model.game;

/**
 * Created by rtulaza on 2014-11-06.
 */
public class CoinSet {
    protected int redCoins = 0;
    protected int greenCoins = 0;
    protected int blueCoins = 0;
    protected int blackCoins = 0;
    protected int whiteCoins = 0;
    protected int jokers = 0;

    public CoinSet() {
    }

    public CoinSet(int redCoins, int greenCoins, int blueCoins, int blackCoins, int whiteCoins, int jokers) {
        this.redCoins = redCoins;
        this.greenCoins = greenCoins;
        this.blueCoins = blueCoins;
        this.blackCoins = blackCoins;
        this.whiteCoins = whiteCoins;
        this.jokers = jokers;
    }

    public int getRedCoins() {
        return redCoins;
    }

    public int getGreenCoins() {
        return greenCoins;
    }

    public int getBlueCoins() {
        return blueCoins;
    }

    public int getBlackCoins() {
        return blackCoins;
    }

    public int getWhiteCoins() {
        return whiteCoins;
    }

    public int getJokers() {
        return jokers;
    }


    public void setRedCoins(int redCoins) {
        this.redCoins = redCoins;
    }

    public void setGreenCoins(int greenCoins) {
        this.greenCoins = greenCoins;
    }

    public void setBlueCoins(int blueCoins) {
        this.blueCoins = blueCoins;
    }

    public void setBlackCoins(int blackCoins) {
        this.blackCoins = blackCoins;
    }

    public void setWhiteCoins(int whiteCoins) {
        this.whiteCoins = whiteCoins;
    }

    public void setJokers(int jokers) {
        this.jokers = jokers;
    }

    public void add(CoinSet coinSet) {
        redCoins += coinSet.redCoins;
        greenCoins += coinSet.greenCoins;
        blueCoins += coinSet.blueCoins;
        blackCoins += coinSet.blackCoins;
        whiteCoins += coinSet.whiteCoins;
        jokers += coinSet.jokers;
    }

    public void subtract(CoinSet coinSet) {
        redCoins -= coinSet.redCoins;
        greenCoins -= coinSet.greenCoins;
        blueCoins -= coinSet.blueCoins;
        blackCoins -= coinSet.blackCoins;
        whiteCoins -= coinSet.whiteCoins;
        jokers -= coinSet.jokers;
    }

    public int sum() {
        return redCoins +
                greenCoins +
                blueCoins +
                blackCoins +
                whiteCoins +
                jokers;
    }

    public void removeNegative() {
        redCoins = redCoins > 0 ? redCoins : 0;
        greenCoins = greenCoins > 0 ? greenCoins : 0;
        blueCoins = blueCoins > 0 ? blueCoins : 0;
        blackCoins = blackCoins > 0 ? blackCoins : 0;
        whiteCoins = whiteCoins > 0 ? whiteCoins : 0;
        jokers = jokers > 0 ? jokers : 0;
    }

    public int[] toArray() {
        return new int[] {
                redCoins,
                greenCoins,
                blueCoins,
                blackCoins,
                whiteCoins,
                jokers
        };
    }
}
