package com.pgssoft.prestige.model.game;

/**
 * Created by rtulaza on 2014-11-05.
 */

/**
 * Class describing a visitor card.
 */
public class VisitorCard {
    private int prestigePoints = 0;
    private int redPrice = 0;
    private int greenPrice = 0;
    private int blackPrice = 0;
    private int bluePrice = 0;
    private int whitePrice = 0;

    /**
     * Returns a single visitor card.
     * @param prestigePoints Amount of prestige points, given by this card
     * @param redPrice Red coins price of this card
     * @param greenPrice Green coins price of this card
     * @param bluePrice Blue coins price of this card
     * @param blackPrice Black coins price of this card
     * @param whitePrice White coins price of this card
     */
    public VisitorCard(int prestigePoints, int redPrice, int greenPrice, int bluePrice, int blackPrice, int whitePrice) {
        this.prestigePoints = prestigePoints;
        this.redPrice = redPrice;
        this.greenPrice = greenPrice;
        this.blackPrice = blackPrice;
        this.bluePrice = bluePrice;
        this.whitePrice = whitePrice;
    }

    /**
     * @return Amount of prestige points, given by this card
     */
    public int getPrestigePoints() {
        return prestigePoints;
    }

    public void setPrestigePoints(int prestigePoints) {
        this.prestigePoints = prestigePoints;
    }

    /**
     * @return Red coins price of this card
     */
    public int getRedPrice() {
        return redPrice;
    }

    public void setRedPrice(int redPrice) {
        this.redPrice = redPrice;
    }

    /**
     * @return Green coins price of this card
     */
    public int getGreenPrice() {
        return greenPrice;
    }

    public void setGreenPrice(int greenPrice) {
        this.greenPrice = greenPrice;
    }

    /**
     * @return Black coins price of this card
     */
    public int getBlackPrice() {
        return blackPrice;
    }

    public void setBlackPrice(int blackPrice) {
        this.blackPrice = blackPrice;
    }

    /**
     * @return Blue coins price of this card
     */
    public int getBluePrice() {
        return bluePrice;
    }

    public void setBluePrice(int bluePrice) {
        this.bluePrice = bluePrice;
    }

    /**
     * @return White coins price of this card
     */
    public int getWhitePrice() {
        return whitePrice;
    }

    public void setWhitePrice(int whitePrice) {
        this.whitePrice = whitePrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VisitorCard that = (VisitorCard) o;

        if (blackPrice != that.blackPrice) return false;
        if (bluePrice != that.bluePrice) return false;
        if (greenPrice != that.greenPrice) return false;
        if (prestigePoints != that.prestigePoints) return false;
        if (redPrice != that.redPrice) return false;
        if (whitePrice != that.whitePrice) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = prestigePoints;
        result = 31 * result + redPrice;
        result = 31 * result + greenPrice;
        result = 31 * result + blackPrice;
        result = 31 * result + bluePrice;
        result = 31 * result + whitePrice;
        return result;
    }
}
