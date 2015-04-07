package com.pgssoft.prestige.model.game;

import com.pgssoft.prestige.model.enums.CardLevel;
import com.pgssoft.prestige.model.enums.TokenColor;

/**
 * Created by rtulaza on 2014-11-05.
 */

/**
 * Class describing a single card.
 */
public class Card {
    private CardLevel level;
    private TokenColor bonus;
    private int prestigePoints = 0;
    private int redPrice = 0;
    private int greenPrice = 0;
    private int blackPrice = 0;
    private int bluePrice = 0;
    private int whitePrice = 0;

    /**
     * Returns a single card.
     * @param level Card level
     * @param bonus Card bonus color
     * @param prestigePoints Amount of prestige points given by this card
     * @param redPrice Amount of red coins needed to buy this card
     * @param greenPrice Amount of green coins needed to buy this card
     * @param bluePrice Amount of blue coins needed to buy this card
     * @param blackPrice Amount of black coins needed to buy this card
     * @param whitePrice Amount of white coins needed to buy this card
     */
    public Card(CardLevel level, TokenColor bonus, int prestigePoints, int redPrice, int greenPrice, int bluePrice, int blackPrice, int whitePrice) {
        this.level = level;
        this.bonus = bonus;
        this.prestigePoints = prestigePoints;
        this.redPrice = redPrice;
        this.greenPrice = greenPrice;
        this.blackPrice = blackPrice;
        this.bluePrice = bluePrice;
        this.whitePrice = whitePrice;
    }

    /**
     * @return Card level
     */
    public CardLevel getLevel() {
        return level;
    }

    /**
     * @return Card bonus color
     */
    public TokenColor getBonus() {
        return bonus;
    }

    /**
     * @return Amount of red coins needed to buy this card
     */
    public int getRedPrice() {
        return redPrice;
    }

    /**
     * @return Amount of green coins needed to buy this card
     */
    public int getGreenPrice() {
        return greenPrice;
    }

    /**
     * @return Amount of black coins needed to buy this card
     */
    public int getBlackPrice() {
        return blackPrice;
    }

    /**
     * @return Amount of blue coins needed to buy this card
     */
    public int getBluePrice() {
        return bluePrice;
    }

    /**
     * @return Amount of white coins needed to buy this card
     */
    public int getWhitePrice() {
        return whitePrice;
    }

    /**
     * @return Amount of prestige points given by this card
     */
    public int getPrestigePoints() {
        return prestigePoints;
    }

    public void setPrestigePoints(int prestigePoints) {
        this.prestigePoints = prestigePoints;
    }

    @Override
    public String toString(){
        return level+"/"+bonus+" ("+redPrice+"/"+greenPrice+"/"+bluePrice+"/"+blackPrice+"/"+whitePrice+")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (blackPrice != card.blackPrice ||
            bluePrice != card.bluePrice ||
            greenPrice != card.greenPrice ||
            prestigePoints != card.prestigePoints ||
            redPrice != card.redPrice ||
            whitePrice != card.whitePrice ||
            bonus != card.bonus ||
            level != card.level){

            return false;
        }

        return true;
    }



    @Override
    public int hashCode() {
        int result = level.hashCode();
        result = 31 * result + bonus.hashCode();
        result = 31 * result + prestigePoints;
        result = 31 * result + redPrice;
        result = 31 * result + greenPrice;
        result = 31 * result + blackPrice;
        result = 31 * result + bluePrice;
        result = 31 * result + whitePrice;
        return result;
    }

}
