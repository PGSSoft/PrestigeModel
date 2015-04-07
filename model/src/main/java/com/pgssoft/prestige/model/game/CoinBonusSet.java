package com.pgssoft.prestige.model.game;

/**
 * Created by rtulaza on 2014-11-06.
 */

/**
 * Represents a set of coins and bonuses.
 */
public class CoinBonusSet extends CoinSet {

    private int redBonuses = 0;
    private int greenBonuses = 0;
    private int blueBonuses = 0;
    private int blackBonuses = 0;
    private int whiteBonuses = 0;

    public CoinBonusSet() {
    }

    /**
     * Returns a coin and bonus set, initiated with selected coins and bonuses.
     * @param redBonuses amount of red bonuses
     * @param greenBonuses amount of green bonuses
     * @param blueBonuses amount of blue bonuses
     * @param blackBonuses amount of black bonuses
     * @param whiteBonuses amount of white bonuses
     * @param redCoins amount of red coins
     * @param greenCoins amount of green coins
     * @param blueCoins amount of blue coins
     * @param blackCoins amount of black coins
     * @param whiteCoins amount of white coins
     * @param jokers amount of jokers
     */
    public CoinBonusSet(int redBonuses, int greenBonuses, int blueBonuses, int blackBonuses, int whiteBonuses,
                        int redCoins, int greenCoins, int blueCoins, int blackCoins, int whiteCoins, int jokers) {
        super(redCoins, greenCoins, blueCoins, blackCoins, whiteCoins, jokers);
        this.redBonuses = redBonuses;
        this.greenBonuses = greenBonuses;
        this.blueBonuses = blueBonuses;
        this.blackBonuses = blackBonuses;
        this.whiteBonuses = whiteBonuses;
    }

    /**
     * Returns a coin set, initiated with selected coins.
     * @param redCoins amount of red coins
     * @param greenCoins amount of green coins
     * @param blueCoins amount of blue coins
     * @param blackCoins amount of black coins
     * @param whiteCoins amount of white coins
     * @param jokers amount of jokers
     */
    public CoinBonusSet(int redCoins, int greenCoins, int blueCoins, int blackCoins, int whiteCoins, int jokers) {
        super(redCoins, greenCoins, blueCoins, blackCoins, whiteCoins, jokers);
    }

    /**
     * @return Amount of red bonuses
     */
    public int getRedBonuses() {
        return redBonuses;
    }

    /**
     * @return Amount of blue bonuses
     */
    public int getBlueBonuses() {
        return blueBonuses;
    }

    /**
     * @return Amount of green bonuses
     */
    public int getGreenBonuses() {
        return greenBonuses;
    }

    /**
     * @return Amount of black bonuses
     */
    public int getBlackBonuses() {
        return blackBonuses;
    }

    /**
     * @return Amount of white bonuses
     */
    public int getWhiteBonuses() {
        return whiteBonuses;
    }


    public void setRedBonuses(int redBonuses) {
        this.redBonuses = redBonuses;
    }

    public void setGreenBonuses(int greenBonuses) {
        this.greenBonuses = greenBonuses;
    }

    public void setWhiteBonuses(int whiteBonuses) {
        this.whiteBonuses = whiteBonuses;
    }

    public void setBlackBonuses(int blackBonuses) {
        this.blackBonuses = blackBonuses;
    }

    public void setBlueBonuses(int blueBonuses) {
        this.blueBonuses = blueBonuses;
    }

    /**
     * Adds selected coin set to the current set.
     * @param coinSet Coin set to be added
     */
    public void add(CoinBonusSet coinSet) {
        super.add(coinSet);

        redBonuses += coinSet.getRedBonuses();
        greenBonuses += coinSet.getGreenBonuses();
        blueBonuses += coinSet.getBlueBonuses();
        blackBonuses += coinSet.getBlackBonuses();
        whiteBonuses += coinSet.getWhiteBonuses();
    }

    /**
     * Subtracts selected coin set from the current set.
     * @param coinSet Coin set to be subtracted
     */
    public void subtract(CoinBonusSet coinSet) {
        super.subtract(coinSet);

        redBonuses -= coinSet.getRedBonuses();
        greenBonuses -= coinSet.getGreenBonuses();
        blueBonuses -= coinSet.getBlueBonuses();
        blackBonuses -= coinSet.getBlackBonuses();
        whiteBonuses -= coinSet.getWhiteBonuses();
    }

    /**
     * Converts this CoinBonusSet to CoinSet. Method adds bonuses amounts of each color to their respective coins amounts.
     * @return New instance of a CoinSet, created by adding coins and bonuses
     */
    public CoinSet toCoinSet() {
        return new CoinSet(
                redCoins + redBonuses,
                greenCoins + greenBonuses,
                blueCoins + blueBonuses,
                blackCoins + blackBonuses,
                whiteCoins + whiteBonuses,
                jokers);
    }
}
