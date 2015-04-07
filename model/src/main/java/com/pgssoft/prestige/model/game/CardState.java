package com.pgssoft.prestige.model.game;

import com.pgssoft.prestige.model.enums.CardPosition;
import com.google.gson.annotations.Expose;

/**
 * Created by rtulaza on 2014-11-05.
 */

/**
 * Class describing state of a single card.
 */
class CardState {

    @Expose
    final private int cardIndex;
    @Expose
    private CardPosition cardPosition;
    @Expose
    private int tableIndex;
    @Expose
    private int handIndex;

    /**
     * Returns a single card state.
     * @param cardIndex Index of a card
     * @param cardPosition Position of a card
     */
    public CardState(int cardIndex, CardPosition cardPosition) {
        this.cardIndex = cardIndex;
        this.cardPosition = cardPosition;
    }

    @Override
    public String toString(){
        return cardIndex+" position "+cardPosition+" tableIndex "+tableIndex;
    }

    /**
     * @return Position of a card
     */
    public CardPosition getCardPosition() {
        return cardPosition;
    }

    public void setCardPosition(CardPosition cardPosition) {
        this.cardPosition = cardPosition;
    }

    /**
     * @return Index of a card
     */
    public int getCardIndex() {
        return cardIndex;
    }

    public void setTableIndex(int tableIndex) {
        if (tableIndex<0 || tableIndex>=4)
        {
            throw new RuntimeException("invalid Table Index");
        }
        this.tableIndex = tableIndex;
    }

    /**
     * @return Table position of a card
     */
    public int getTableIndex(){
        return tableIndex;
    }


    public void setHandIndex(int handIndex) {
        if (handIndex<0 || handIndex>=3)
        {
            throw new RuntimeException("invalid Hand Index");
        }
        this.handIndex = handIndex;
    }

    /**
     * @return Hand position of a card
     */
    public int getHandIndex(){
        return handIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardState cardState = (CardState) o;

        if (cardIndex != cardState.cardIndex) return false;
        if (handIndex != cardState.handIndex) return false;
        if (tableIndex != cardState.tableIndex) return false;
        if (cardPosition != cardState.cardPosition) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cardIndex;
        result = 31 * result + cardPosition.hashCode();
        result = 31 * result + tableIndex;
        result = 31 * result + handIndex;
        return result;
    }
}
