package com.pgssoft.prestige.model.game;

import com.pgssoft.prestige.model.enums.CardLevel;
import com.pgssoft.prestige.model.enums.MoveType;
import com.pgssoft.prestige.model.enums.Player;
import com.google.gson.annotations.Expose;

/**
 * Created by rtulaza on 2014-11-07.
 */

/**
 * Class encapsulating a single move on a game state.
 */
public class Move {


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Move move = (Move) o;

        if (getBlack != move.getBlack) return false;
        if (getBlue != move.getBlue) return false;
        if (getGreen != move.getGreen) return false;
        if (getRed != move.getRed) return false;
        if (getWhite != move.getWhite) return false;
        if (removeBlack != move.removeBlack) return false;
        if (removeBlue != move.removeBlue) return false;
        if (removeGreen != move.removeGreen) return false;
        if (removeJokers != move.removeJokers) return false;
        if (removeRed != move.removeRed) return false;
        if (removeWhite != move.removeWhite) return false;
        if (tableOrHandIndex != move.tableOrHandIndex) return false;
        if (cardLevel != move.cardLevel) return false;
        if (moveType != move.moveType) return false;
        if (player != move.player) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = moveType.hashCode();
        result = 31 * result + player.hashCode();
        result = 31 * result + getRed;
        result = 31 * result + getGreen;
        result = 31 * result + getBlue;
        result = 31 * result + getBlack;
        result = 31 * result + getWhite;
        result = 31 * result + removeRed;
        result = 31 * result + removeGreen;
        result = 31 * result + removeBlue;
        result = 31 * result + removeBlack;
        result = 31 * result + removeWhite;
        result = 31 * result + removeJokers;
        result = 31 * result + tableOrHandIndex;
        result = 31 * result + cardLevel.hashCode();
        return result;
    }

    @Expose protected MoveType moveType;
    @Expose protected Player player;
    @Expose protected int getRed = 0;
    @Expose protected int getGreen = 0;
    @Expose protected int getBlue = 0;
    @Expose protected int getBlack = 0;
    @Expose protected int getWhite = 0;
    @Expose protected int removeRed = 0;
    @Expose protected int removeGreen = 0;
    @Expose protected int removeBlue = 0;
    @Expose protected int removeBlack = 0;
    @Expose protected int removeWhite = 0;
    @Expose protected int removeJokers = 0;
    @Expose protected int tableOrHandIndex = 0;
    @Expose protected CardLevel cardLevel;


    /**
     * Returns an empty instance of a move.
     */
    public Move(){}

    /**
     * Returns new instance of a move, initiated with a second move.
     * @param other Move which should be cloned
     */
    public Move(Move other){
        player = other.player;
        getRed = other.getRed;
        getGreen = other.getGreen;
        getBlue = other.getBlue;
        getBlack = other.getBlack;
        getWhite = other.getWhite;
        removeRed = other.removeRed;
        removeGreen = other.removeGreen;
        removeBlue = other.removeBlue;
        removeBlack = other.removeBlack;
        removeWhite = other.removeWhite;
        removeJokers = other.removeJokers;
        tableOrHandIndex = other.tableOrHandIndex;
        cardLevel = other.cardLevel;
        moveType = other.moveType;
    }

    /**
     * Returns new instance of a move, initiated with given values.
     * @param moveType Type of a move
     * @param player Player taking this move
     * @param cardLevel Level of a chosen card
     * @param tableIndex Table position of a chosen card
     * @param removeRed Amount of red coins that were paid for the card
     * @param removeGreen Amount of green coins that were paid for the card
     * @param removeBlue Amount of blue coins that were paid for the card
     * @param removeBlack Amount of black coins that were paid for the card
     * @param removeWhite Amount of white coins that were paid for the card
     * @param removeJokers Amount of jokers that were paid for the card
     */
    public Move(MoveType moveType, Player player, CardLevel cardLevel, int tableIndex, int removeRed, int removeGreen, int removeBlue, int removeBlack, int removeWhite, int removeJokers) {
        this.moveType = moveType;
        this.cardLevel = cardLevel;
        this.tableOrHandIndex = tableIndex;
        this.player = player;
        this.removeRed = removeRed;
        this.removeGreen = removeGreen;
        this.removeBlue = removeBlue;
        this.removeWhite = removeWhite;
        this.removeBlack = removeBlack;
        this.removeJokers = removeJokers;
    }

    /**
     * Returns new instance of a move, initiated with given values.
     * @param moveType Type of a move
     * @param player Player taking this move
     * @param cardLevel Level of a chosen card
     * @param tableIndex Table position of a chosen card
     */
    public Move(MoveType moveType, Player player, CardLevel cardLevel, int tableIndex) {
        this.moveType = moveType;
        this.player = player;
        this.cardLevel = cardLevel;
        this.tableOrHandIndex = tableIndex;
    }

    /**
     * Returns new instance of a move, initiated with given values.
     * @param moveType Type of a move
     * @param player Player taking this move
     * @param tableOrHandIndex Table or hand position of a chosen card
     */
    public Move(MoveType moveType, Player player, int tableOrHandIndex) {
        this.moveType = moveType;
        this.player = player;
        this.tableOrHandIndex = tableOrHandIndex;
    }

    /**
     * Returns new instance of a move, initiated with given values.
     * @param moveType Type of a move
     * @param player Player taking this move
     * @param getRed Red coins taken
     * @param getGreen Green coins taken
     * @param getBlue Blue coins taken
     * @param getBlack Black coins taken
     * @param getWhite White coins taken
     * @param removeRed Amount of red coins that player had to remove
     * @param removeGreen Amount of green coins that player had to remove
     * @param removeBlue Amount of blue coins that player had to remove
     * @param removeBlack Amount of black coins that player had to remove
     * @param removeWhite Amount of white coins that player had to remove
     * @param removeJokers Amount of jokers that player had to remove
     */
    public Move(MoveType moveType, Player player, int getRed, int getGreen, int getBlue, int getBlack, int getWhite, int removeRed, int removeGreen, int removeBlue, int removeBlack, int removeWhite, int removeJokers) {
        this.moveType = moveType;
        this.player = player;
        this.getRed = getRed;
        this.getGreen = getGreen;
        this.getBlue = getBlue;
        this.getBlack = getBlack;
        this.getWhite = getWhite;
        this.removeRed = removeRed;
        this.removeGreen = removeGreen;
        this.removeBlue = removeBlue;
        this.removeBlack = removeBlack;
        this.removeWhite = removeWhite;
        this.removeJokers = removeJokers;
    }

    /**
     * Returns new instance of a Book Card From Table move.
     * @param player Player taking this move
     * @param cardLevel Level of a chosen card
     * @param tableIndex Table position of a chosen card
     * @param removeRed Amount of red coins that player had to remove
     * @param removeGreen Amount of green coins that player had to remove
     * @param removeBlue Amount of blue coins that player had to remove
     * @param removeBlack Amount of black coins that player had to remove
     * @param removeWhite Amount of white coins that player had to remove
     * @param removeJokers Amount of jokers that player had to remove
     */
    public static Move getMoveBookCardFromTable(Player player, CardLevel cardLevel, int tableIndex, int removeRed, int removeGreen, int removeBlue, int removeBlack, int removeWhite, int removeJokers) {
        return new Move(
                MoveType.BOOK_CARD_FROM_TABLE,
                player,
                cardLevel,
                tableIndex,
                removeRed,
                removeGreen,
                removeBlue,
                removeBlack,
                removeWhite,
                removeJokers);
    }

    /**
     * Returns new instance of a Book Card From Deck move.
     * @param player Player taking this move
     * @param cardLevel Level of a chosen card
     * @param removeRed Amount of red coins that player had to remove
     * @param removeGreen Amount of green coins that player had to remove
     * @param removeBlue Amount of blue coins that player had to remove
     * @param removeBlack Amount of black coins that player had to remove
     * @param removeWhite Amount of white coins that player had to remove
     * @param removeJokers Amount of jokers that player had to remove
     */
    public static Move getMoveBookCardFromDeck(Player player, CardLevel cardLevel, int removeRed, int removeGreen, int removeBlue, int removeBlack, int removeWhite, int removeJokers) {
        return new Move(
                MoveType.BOOK_CARD_FROM_DECK,
                player,
                cardLevel,
                0,
                removeRed,
                removeGreen,
                removeBlue,
                removeBlack,
                removeWhite,
                removeJokers);
    }

    /**
     * Returns new instance of a Buy Card From Table move.
     * @param player Player taking this move
     * @param cardLevel Level of a chosen card
     * @param tableIndex Table position of a chosen card
     */
    public static Move getMoveBuyCardFromTable(Player player, CardLevel cardLevel, int tableIndex) {
        return new Move(
                MoveType.BUY_CARD_FROM_TABLE,
                player,
                cardLevel,
                tableIndex
        );
    }

    /**
     * Returns new instance of a Buy Card From Hand move.
     * @param player Player taking this move
     * @param handIndex Hand position of a chosen card
     */
    public static Move getMoveBuyCardFromHand(Player player, int handIndex) {
        return new Move(
                MoveType.BUY_CARD_FROM_HAND,
                player,
                handIndex
        );
    }

    /**
     * Returns new instance of a Take Coins move.
     * @param player Player taking this move
     * @param getRed Red coins taken
     * @param getGreen Green coins taken
     * @param getBlue Blue coins taken
     * @param getBlack Black coins taken
     * @param getWhite White coins taken
     * @param removeRed Amount of red coins that player had to remove
     * @param removeGreen Amount of green coins that player had to remove
     * @param removeBlue Amount of blue coins that player had to remove
     * @param removeBlack Amount of black coins that player had to remove
     * @param removeWhite Amount of white coins that player had to remove
     * @param removeJokers Amount of jokers that player had to remove
     */
    public static Move getMoveTakeCoins(Player player, int getRed, int getGreen, int getBlue, int getBlack, int getWhite, int removeRed, int removeGreen, int removeBlue, int removeBlack, int removeWhite, int removeJokers){
        return new Move(
                MoveType.TAKE_COINS,
                player,
                getRed,
                getGreen,
                getBlue,
                getBlack,
                getWhite,
                removeRed,
                removeGreen,
                removeBlue,
                removeBlack,
                removeWhite,
                removeJokers);
    }


    /**
     * @return Player taking this move
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @return Amount of red coins that player had to remove
     */
    public int getRemoveRed() {
        return removeRed;
    }

    /**
     * @return Amount of green coins that player had to remove
     */
    public int getRemoveGreen() {
        return removeGreen;
    }

    /**
     * @return Amount of blue coins that player had to remove
     */
    public int getRemoveBlue() {
        return removeBlue;
    }

    /**
     * @return Amount of black coins that player had to remove
     */
    public int getRemoveBlack() {
        return removeBlack;
    }

    /**
     * @return Amount of white coins that player had to remove
     */
    public int getRemoveWhite() {
        return removeWhite;
    }

    /**
     * @return Amount of jokers that player had to remove
     */
    public int getRemoveJokers() {
        return removeJokers;
    }

    /**
     * @return Amount of coins that player had to remove
     */
    public int getRemoveSUM(){
        return removeRed+removeGreen+removeBlue+removeBlack+removeWhite+removeJokers;
    }

    /**
     * @return Amount of red coins taken
     */
    public int getGetRed() {
        return getRed;
    }

    /**
     * @return Amount of green coins taken
     */
    public int getGetGreen() {
        return getGreen;
    }

    /**
     * @return Amount of blue coins taken
     */
    public int getGetBlue() {
        return getBlue;
    }

    /**
     * @return Amount of black coins taken
     */
    public int getGetBlack() {
        return getBlack;
    }

    /**
     * @return Amount of white coins taken
     */
    public int getGetWhite() {
        return getWhite;
    }

    /**
     * @return Level of a chosen card
     */
    public CardLevel getCardLevel() {
        return cardLevel;
    }

    /**
     * @return Table or hand position of a chosen card
     */
    public int getTableOrHandIndex() {
        return tableOrHandIndex;
    }

    /**
     * @return Move type
     */
    public MoveType getMoveType() {
        return moveType;
    }
}
