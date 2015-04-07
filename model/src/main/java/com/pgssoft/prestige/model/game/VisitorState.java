package com.pgssoft.prestige.model.game;

import com.pgssoft.prestige.model.enums.VisitorPosition;
import com.google.gson.annotations.Expose;

/**
 * Created by tzielinski on 2014-11-14.
 */

/**
 * Class describing state of a single visitor card.
 */
class VisitorState {
    @Expose
    final private int visitorCardIndex;
    @Expose
    private VisitorPosition position;

    /**
     * Returns a single visitor state.
     * @param cardIndex Index of a visitor card
     * @param cardPosition Position of a visitor card
     */
    public VisitorState(int cardIndex, VisitorPosition cardPosition) {
        this.visitorCardIndex = cardIndex;
        this.position = cardPosition;
    }

    /**
     * @return Visitor card index
     */
    public int getVisitorCardIndex() {
        return visitorCardIndex;
    }

    public void setVisitorPosition(VisitorPosition pos){
        position = pos;
    }

    /**
     * @return Visitor position
     */
    public VisitorPosition getVisitorPosition(){
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VisitorState that = (VisitorState) o;

        if (visitorCardIndex != that.visitorCardIndex) return false;
        if (position != that.position) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = visitorCardIndex;
        result = 31 * result + position.hashCode();
        return result;
    }
}
