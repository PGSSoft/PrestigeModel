package com.pgssoft.prestige.model.game;

import com.pgssoft.prestige.model.enums.CardLevel;
import com.pgssoft.prestige.model.enums.TokenColor;

/**
 * Created by Tomek on 2014-11-14.
 */
public class TestData {
    public final static Card[] cardsTesting = new Card[]{
            new Card(CardLevel.ONE, TokenColor.BLACK, 2, 0, 0, 0, 2, 0), //0
            new Card(CardLevel.ONE, TokenColor.BLACK, 2, 0, 0, 0, 2, 0), //1
            new Card(CardLevel.ONE, TokenColor.BLACK, 2, 0, 0, 0, 2, 0), //2
            new Card(CardLevel.ONE, TokenColor.BLACK, 4, 0, 0, 0, 2, 0), //3
            new Card(CardLevel.TWO, TokenColor.BLACK, 3, 0, 0, 0, 2, 0), //4
            new Card(CardLevel.TWO, TokenColor.BLACK, 1, 0, 0, 0, 2, 0), //5
            new Card(CardLevel.TWO, TokenColor.BLACK, 2, 0, 0, 0, 2, 0), //6
            new Card(CardLevel.TWO, TokenColor.BLACK, 2, 0, 0, 0, 2, 0), //7
            new Card(CardLevel.THREE, TokenColor.BLACK, 2, 0, 0, 0, 2, 0), //8
            new Card(CardLevel.THREE, TokenColor.BLACK, 2, 0, 0, 0, 5, 0), //9
            new Card(CardLevel.THREE, TokenColor.BLACK, 2, 0, 0, 0, 2, 0), //10
            new Card(CardLevel.THREE, TokenColor.BLACK, 2, 0, 0, 0, 2, 0), //11
            new Card(CardLevel.THREE, TokenColor.BLACK, 2, 0, 0, 0, 5, 0), //12
            new Card(CardLevel.TWO, TokenColor.BLACK, 2, 0, 0, 0, 2, 0), //13
            new Card(CardLevel.THREE, TokenColor.BLACK, 2, 0, 0, 0, 2, 0), //14
            new Card(CardLevel.THREE, TokenColor.BLACK, 2, 0, 0, 0, 2, 0), //15
            new Card(CardLevel.TWO, TokenColor.BLACK, 2, 0, 0, 0, 2, 0), //16
            new Card(CardLevel.TWO, TokenColor.BLACK, 2, 0, 0, 0, 2, 0), //17
            new Card(CardLevel.THREE, TokenColor.BLACK, 2, 0, 0, 0, 2, 0), //18
            new Card(CardLevel.THREE, TokenColor.BLACK, 2, 0, 0, 0, 2, 0) //19
    };

    public final static VisitorCard[] visitorCardsTesting = new VisitorCard[]{
        new VisitorCard(3, 0, 0, 0, 2, 0),
        new VisitorCard(3, 2, 2, 2, 2, 2),
        new VisitorCard(3, 2, 2, 2, 2, 2),
        new VisitorCard(3, 2, 2, 2, 2, 2),
        new VisitorCard(3, 2, 2, 2, 2, 2)
    };


}
