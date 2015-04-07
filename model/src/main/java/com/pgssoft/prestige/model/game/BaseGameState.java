package com.pgssoft.prestige.model.game;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pgssoft.prestige.model.enums.CardLevel;
import com.pgssoft.prestige.model.enums.CardPosition;
import com.pgssoft.prestige.model.enums.Player;
import com.pgssoft.prestige.model.enums.TokenColor;
import com.pgssoft.prestige.model.enums.VisitorPosition;
import com.pgssoft.prestige.model.exceptions.InvalidMoveException;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * Created by rtulaza on 2014-11-06.
 */

/**
 * State of game as seen by player
 */
public class BaseGameState implements IBaseGameState {

    protected final static Card[] cardsNormal = new Card[]{
        new Card(CardLevel.THREE, TokenColor.WHITE, 4, 3, 0, 0, 6, 3),
        new Card(CardLevel.THREE, TokenColor.WHITE, 5, 0, 0, 0, 7, 3),
        new Card(CardLevel.THREE, TokenColor.BLUE, 3, 3, 3, 0, 5, 3),
        new Card(CardLevel.THREE, TokenColor.GREEN, 5, 0, 3, 7, 0, 0),
        new Card(CardLevel.THREE, TokenColor.RED, 5, 3, 7, 0, 0, 0),
        new Card(CardLevel.THREE, TokenColor.BLACK, 3, 3, 5, 3, 0, 3),
        new Card(CardLevel.THREE, TokenColor.BLACK, 4, 7, 0, 0, 0, 0),
        new Card(CardLevel.THREE, TokenColor.BLUE, 4, 0, 0, 3, 3, 6),
        new Card(CardLevel.THREE, TokenColor.BLACK, 4, 6, 3, 0, 3, 0),
        new Card(CardLevel.THREE, TokenColor.BLUE, 5, 0, 0, 3, 0, 7),
        new Card(CardLevel.THREE, TokenColor.BLUE, 4, 0, 0, 0, 0, 7),
        new Card(CardLevel.THREE, TokenColor.BLACK, 5, 7, 0, 0, 3, 0),
        new Card(CardLevel.THREE, TokenColor.RED, 4, 3, 6, 3, 0, 0),
        new Card(CardLevel.THREE, TokenColor.GREEN, 4, 0, 3, 6, 0, 3),
        new Card(CardLevel.THREE, TokenColor.RED, 4, 0, 7, 0, 0, 0),
        new Card(CardLevel.THREE, TokenColor.WHITE, 4, 0, 0, 0, 7, 0),
        new Card(CardLevel.THREE, TokenColor.GREEN, 4, 0, 0, 7, 0, 0),
        new Card(CardLevel.THREE, TokenColor.RED, 3, 0, 3, 5, 3, 3),
        new Card(CardLevel.THREE, TokenColor.WHITE, 3, 5, 3, 3, 3, 0),
        new Card(CardLevel.THREE, TokenColor.GREEN, 3, 3, 0, 3, 3, 5),
        new Card(CardLevel.TWO, TokenColor.RED, 2, 0, 0, 0, 5, 3),
        new Card(CardLevel.TWO, TokenColor.GREEN, 3, 0, 6, 0, 0, 0),
        new Card(CardLevel.TWO, TokenColor.WHITE, 3, 0, 0, 0, 0, 6),
        new Card(CardLevel.TWO, TokenColor.WHITE, 2, 4, 1, 0, 2, 0),
        new Card(CardLevel.TWO, TokenColor.BLUE, 2, 1, 0, 0, 4, 2),
        new Card(CardLevel.TWO, TokenColor.BLACK, 3, 0, 0, 0, 6, 0),
        new Card(CardLevel.TWO, TokenColor.BLUE, 2, 0, 0, 3, 0, 5),
        new Card(CardLevel.TWO, TokenColor.WHITE, 2, 5, 0, 0, 3, 0),
        new Card(CardLevel.TWO, TokenColor.GREEN, 2, 0, 3, 5, 0, 0),
        new Card(CardLevel.TWO, TokenColor.RED, 1, 2, 0, 0, 3, 2),
        new Card(CardLevel.TWO, TokenColor.BLUE, 1, 3, 2, 2, 0, 0),
        new Card(CardLevel.TWO, TokenColor.BLUE, 2, 0, 0, 5, 0, 0),
        new Card(CardLevel.TWO, TokenColor.GREEN, 1, 0, 0, 3, 2, 2),
        new Card(CardLevel.TWO, TokenColor.GREEN, 2, 0, 5, 0, 0, 0),
        new Card(CardLevel.TWO, TokenColor.BLACK, 1, 0, 2, 2, 0, 3),
        new Card(CardLevel.TWO, TokenColor.RED, 3, 6, 0, 0, 0, 0),
        new Card(CardLevel.TWO, TokenColor.BLUE, 3, 0, 0, 6, 0, 0),
        new Card(CardLevel.TWO, TokenColor.RED, 2, 0, 0, 0, 5, 0),
        new Card(CardLevel.TWO, TokenColor.WHITE, 2, 5, 0, 0, 0, 0),
        new Card(CardLevel.TWO, TokenColor.GREEN, 1, 3, 2, 0, 0, 3),
        new Card(CardLevel.TWO, TokenColor.BLACK, 1, 0, 3, 0, 2, 3),
        new Card(CardLevel.TWO, TokenColor.BLACK, 2, 2, 4, 1, 0, 0),
        new Card(CardLevel.TWO, TokenColor.BLUE, 1, 0, 3, 2, 3, 0),
        new Card(CardLevel.TWO, TokenColor.BLACK, 2, 3, 5, 0, 0, 0),
        new Card(CardLevel.TWO, TokenColor.WHITE, 1, 2, 3, 0, 2, 0),
        new Card(CardLevel.TWO, TokenColor.GREEN, 2, 0, 0, 2, 1, 4),
        new Card(CardLevel.TWO, TokenColor.WHITE, 1, 3, 0, 3, 0, 2),
        new Card(CardLevel.TWO, TokenColor.RED, 1, 2, 0, 3, 3, 0),
        new Card(CardLevel.TWO, TokenColor.RED, 2, 0, 2, 4, 0, 1),
        new Card(CardLevel.TWO, TokenColor.BLACK, 2, 0, 0, 0, 0, 5),
        new Card(CardLevel.ONE, TokenColor.RED, 0, 0, 1, 1, 1, 1),
        new Card(CardLevel.ONE, TokenColor.BLUE, 0, 2, 2, 0, 0, 1),
        new Card(CardLevel.ONE, TokenColor.BLUE, 0, 2, 1, 0, 1, 1),
        new Card(CardLevel.ONE, TokenColor.GREEN, 0, 2, 0, 2, 0, 0),
        new Card(CardLevel.ONE, TokenColor.GREEN, 0, 1, 0, 1, 2, 1),
        new Card(CardLevel.ONE, TokenColor.GREEN, 0, 0, 1, 3, 0, 1),
        new Card(CardLevel.ONE, TokenColor.WHITE, 1, 0, 4, 0, 0, 0),
        new Card(CardLevel.ONE, TokenColor.WHITE, 0, 1, 1, 1, 1, 0),
        new Card(CardLevel.ONE, TokenColor.WHITE, 0, 0, 2, 2, 1, 0),
        new Card(CardLevel.ONE, TokenColor.RED, 0, 0, 0, 0, 0, 3),
        new Card(CardLevel.ONE, TokenColor.RED, 0, 1, 0, 0, 3, 1),
        new Card(CardLevel.ONE, TokenColor.RED, 1, 0, 0, 0, 0, 4),
        new Card(CardLevel.ONE, TokenColor.BLACK, 0, 0, 3, 0, 0, 0),
        new Card(CardLevel.ONE, TokenColor.BLACK, 0, 1, 1, 2, 0, 1),
        new Card(CardLevel.ONE, TokenColor.RED, 0, 0, 1, 1, 1, 2),
        new Card(CardLevel.ONE, TokenColor.GREEN, 0, 1, 0, 1, 1, 1),
        new Card(CardLevel.ONE, TokenColor.GREEN, 0, 0, 0, 1, 0, 2),
        new Card(CardLevel.ONE, TokenColor.BLUE, 1, 4, 0, 0, 0, 0),
        new Card(CardLevel.ONE, TokenColor.RED, 0, 2, 0, 0, 0, 2),
        new Card(CardLevel.ONE, TokenColor.WHITE, 0, 0, 0, 2, 2, 0),
        new Card(CardLevel.ONE, TokenColor.BLUE, 0, 1, 3, 1, 0, 0),
        new Card(CardLevel.ONE, TokenColor.WHITE, 0, 1, 2, 1, 1, 0),
        new Card(CardLevel.ONE, TokenColor.BLUE, 0, 0, 0, 0, 2, 1),
        new Card(CardLevel.ONE, TokenColor.BLUE, 0, 1, 1, 0, 1, 1),
        new Card(CardLevel.ONE, TokenColor.RED, 0, 0, 1, 0, 2, 2),
        new Card(CardLevel.ONE, TokenColor.BLACK, 0, 3, 1, 0, 1, 0),
        new Card(CardLevel.ONE, TokenColor.BLACK, 0, 1, 1, 1, 0, 1),
        new Card(CardLevel.ONE, TokenColor.BLACK, 0, 0, 2, 0, 0, 2),
        new Card(CardLevel.ONE, TokenColor.WHITE, 0, 0, 0, 1, 1, 3),
        new Card(CardLevel.ONE, TokenColor.BLACK, 0, 1, 0, 2, 0, 2),
        new Card(CardLevel.ONE, TokenColor.BLACK, 0, 1, 2, 0, 0, 0),
        new Card(CardLevel.ONE, TokenColor.RED, 0, 0, 1, 2, 0, 0),
        new Card(CardLevel.ONE, TokenColor.BLACK, 1, 0, 0, 4, 0, 0),
        new Card(CardLevel.ONE, TokenColor.GREEN, 1, 0, 0, 0, 4, 0),
        new Card(CardLevel.ONE, TokenColor.GREEN, 0, 3, 0, 0, 0, 0),
        new Card(CardLevel.ONE, TokenColor.BLUE, 0, 0, 0, 0, 3, 0),
        new Card(CardLevel.ONE, TokenColor.BLUE, 0, 0, 2, 0, 2, 0),
        new Card(CardLevel.ONE, TokenColor.WHITE, 0, 0, 0, 3, 0, 0),
        new Card(CardLevel.ONE, TokenColor.WHITE, 0, 2, 0, 0, 1, 0),
        new Card(CardLevel.ONE, TokenColor.GREEN, 0, 2, 0, 1, 2, 0)
    };

    protected final static VisitorCard[] visitorCardsNormal = new VisitorCard[]{
        new VisitorCard(3, 0, 0, 3, 3, 3),
        new VisitorCard(3, 3, 3, 3, 0, 0),
        new VisitorCard(3, 3, 0, 0, 3, 3),
        new VisitorCard(3, 4, 4, 0, 0, 0),
        new VisitorCard(3, 0, 4, 4, 0, 0),
        new VisitorCard(3, 4, 0, 0, 4, 0),
        new VisitorCard(3, 0, 0, 0, 4, 4),
        new VisitorCard(3, 0, 3, 3, 0, 3),
        new VisitorCard(3, 3, 3, 0, 3, 0),
        new VisitorCard(3, 0, 0, 4, 0, 4)
    };

    protected Card[] cardsArr;
    protected VisitorCard[] visitorCardsArr;

    protected boolean isDebug = false;

    @Expose
    protected int playersNum = 0;

    @Expose
    protected ArrayList<CardState> cardStates = new ArrayList<CardState>(60);

    @Expose
    protected ArrayList<VisitorState> visitorStates = new ArrayList<VisitorState>(10);

    @Expose
    protected Player turnTakingPlayer = Player.PLAYER0;

    @Expose
    protected int tableCoins[] = {0, 0, 0, 0, 0, 0};

    @Expose
    protected int player0Coins[] = {0, 0, 0, 0, 0, 0};

    @Expose
    protected int player1Coins[] = {0, 0, 0, 0, 0, 0};

    @Expose
    protected int player2Coins[] = {0, 0, 0, 0, 0, 0};

    @Expose
    protected int player3Coins[] = {0, 0, 0, 0, 0, 0};

    @Expose
    protected Move lastMove;

    @Expose
    private int roundNumber = 0;

    /**
     * Creates new game state with given number of players.
     * @param playersNum Number of players
     */
    public BaseGameState(int playersNum) {
        init(playersNum, cardsNormal, visitorCardsNormal);
    }

    /**
     * Creates new game state with given number of players and custom sets of cards.
     * @param playersNum Number of players
     * @param cards Cards used in game
     * @param visitorCards Visitor cards used in game
     */
    public BaseGameState(int playersNum, Card[] cards, VisitorCard[] visitorCards) {
        isDebug = true;
        init(playersNum, cards, visitorCards);
    }

    private void init(int playersNum, Card[] cards, VisitorCard[] visitorCards){
        cardsArr = cards;
        visitorCardsArr = visitorCards;
        this.playersNum = playersNum;
        roundNumber = 0;

        if(this.playersNum < 2 || this.playersNum > 4) {
            throw new InvalidMoveException(String.format("Invalid number of players. Must be between 2-4, %d given", this.playersNum));
        }

        ArrayList<Integer> randoms = new ArrayList<Integer>();
        for (int i=0; i<cardsArr.length; i++) {
            randoms.add(i);
        }

        if(isDebug) {
            Collections.shuffle(randoms, new Random(12));
        } else {
            Collections.shuffle(randoms, new Random());
        }

        for(int i = 0; i < cards.length; i++) {
            cardStates.add(new CardState(randoms.get(i), CardPosition.DECK));
        }

        for(int i = 0; i < 4; i++) {
            layOneCardFromDeck(CardLevel.ONE, i);
            layOneCardFromDeck(CardLevel.TWO, i);
            layOneCardFromDeck(CardLevel.THREE, i);
        }

        //get visitor cards, shuffle and set states

        randoms = new ArrayList<Integer>();
        for (int i = 0; i < visitorCardsArr.length; i++) {
            randoms.add(i);
        }
        if(isDebug) {
            Collections.shuffle(randoms, new Random(12));
        } else {
            Collections.shuffle(randoms, new Random());
        }

        for(int i = 0; i < visitorCards.length; i++) {
            if(i < playersNum+1) {
                visitorStates.add(new VisitorState(randoms.get(i), VisitorPosition.TABLE));
            } else {
                visitorStates.add(new VisitorState(randoms.get(i), VisitorPosition.NOT_USED));
            }
        }

        //initiate coins
        if(this.playersNum == 2) {
            tableCoins[TokenColor.BLACK.ordinal()] = 4;
            tableCoins[TokenColor.WHITE.ordinal()] = 4;
            tableCoins[TokenColor.GREEN.ordinal()] = 4;
            tableCoins[TokenColor.BLUE.ordinal()] = 4;
            tableCoins[TokenColor.RED.ordinal()] = 4;
        } else if(this.playersNum == 3) {
            tableCoins[TokenColor.BLACK.ordinal()] = 5;
            tableCoins[TokenColor.WHITE.ordinal()] = 5;
            tableCoins[TokenColor.GREEN.ordinal()] = 5;
            tableCoins[TokenColor.BLUE.ordinal()] = 5;
            tableCoins[TokenColor.RED.ordinal()] = 5;
        } else {
            tableCoins[TokenColor.BLACK.ordinal()] = 7;
            tableCoins[TokenColor.WHITE.ordinal()] = 7;
            tableCoins[TokenColor.GREEN.ordinal()] = 7;
            tableCoins[TokenColor.BLUE.ordinal()] = 7;
            tableCoins[TokenColor.RED.ordinal()] = 7;
        }
        tableCoins[TokenColor.JOKER.ordinal()] = 5;
    }


    /**
     * Check whether move is correct. Method makes given move, modifying before state, and compares it with given after state.
     * @param move Move to check
     * @param before Game state before move
     * @param after Game state after move
     * @return True, if move is valid and produces the same results as in after game state, else false
     */
    public static boolean isMoveValid(Move move, BaseGameState before, BaseGameState after) {
        if(move == null || before == null || after == null) {
            return false;
        }

        Move oldMove = before.lastMove;

        try {
            switch (move.getMoveType()) {
                case TAKE_COINS:
                    before.makeMoveGetCoins(move.getPlayer(), move.getGetRed(), move.getGetGreen(), move.getGetBlue(), move.getGetBlack(), move.getGetWhite(), move.getRemoveRed(), move.getRemoveGreen(), move.getRemoveBlue(), move.getRemoveBlack(), move.getRemoveWhite(), move.getRemoveJokers());
                    break;
                case BOOK_CARD_FROM_TABLE:
                    before.makeMoveBookCardFromTable(move.getPlayer(), move.getCardLevel(), move.getTableOrHandIndex(), move.getRemoveRed(), move.getRemoveGreen(), move.getRemoveBlue(), move.getRemoveBlack(), move.getRemoveWhite(), move.getRemoveJokers());
                    break;
                case BOOK_CARD_FROM_DECK:
                    before.makeMoveBookCardFromDeck(move.getPlayer(), move.getCardLevel(), move.getRemoveRed(), move.getRemoveGreen(), move.getRemoveBlue(), move.getRemoveBlack(), move.getRemoveWhite(), move.getRemoveJokers());
                    break;
                case BUY_CARD_FROM_TABLE:
                    before.makeMoveBuyCardFromTable(move.getPlayer(), move.getCardLevel(), move.getTableOrHandIndex());
                    break;
                case BUY_CARD_FROM_HAND:
                    before.makeMoveBuyCardFromHand(move.getPlayer(), move.tableOrHandIndex);
                    break;
            }
        }
        catch(InvalidMoveException e) {
            return false;
        }

        switch(before.playersNum) {
            case 2:
                if(oldMove.getPlayer() == move.getPlayer()) {
                    return false;
                }
                break;
            case 3:
                if(oldMove.getPlayer() == Player.PLAYER0 && move.getPlayer() != Player.PLAYER1) {
                    return false;
                } else if(oldMove.getPlayer() == Player.PLAYER1 && move.getPlayer() != Player.PLAYER2) {
                    return false;
                } else if(oldMove.getPlayer() == Player.PLAYER2 && move.getPlayer() != Player.PLAYER0) {
                    return false;
                }
                break;
            case 4:
                if(oldMove.getPlayer() == Player.PLAYER0 && move.getPlayer() != Player.PLAYER1) {
                    return false;
                } else if(oldMove.getPlayer() == Player.PLAYER1 && move.getPlayer() != Player.PLAYER2) {
                    return false;
                } else if(oldMove.getPlayer() == Player.PLAYER2 && move.getPlayer() != Player.PLAYER3) {
                    return false;
                } else if(oldMove.getPlayer() == Player.PLAYER3 && move.getPlayer() != Player.PLAYER0) {
                    return false;
                }
                break;
        }

        return before.equals(after);
    }

    /**
     * Makes given move on current game state.
     * @param move Move to make
     * @throws InvalidMoveException if the move is illegal
     */
    public void makeMove(Move move) {

        if (move.getPlayer() != turnTakingPlayer) {
            throw new InvalidMoveException("It's not given player's turn");
        }

        switch (move.getMoveType()) {
            case TAKE_COINS:
                makeMoveGetCoins(move.getPlayer(), move.getGetRed(), move.getGetGreen(), move.getGetBlue(),
                        move.getGetBlack(), move.getGetWhite(), move.getRemoveRed(), move.getRemoveGreen(), move.getRemoveBlue(),
                        move.getRemoveBlack(), move.getRemoveWhite(), move.getRemoveJokers());
                break;

            case BUY_CARD_FROM_TABLE:
                makeMoveBuyCardFromTable(move.getPlayer(), move.getCardLevel(), move.getTableOrHandIndex());
                break;

            case BUY_CARD_FROM_HAND:
                makeMoveBuyCardFromHand(move.getPlayer(), move.getTableOrHandIndex());
                break;

            case BOOK_CARD_FROM_TABLE:
                makeMoveBookCardFromTable(move.getPlayer(), move.getCardLevel(), move.getTableOrHandIndex(),
                        move.getRemoveRed(), move.getRemoveGreen(), move.getRemoveBlue(), move.getRemoveBlack(),
                        move.getRemoveWhite(), move.getRemoveJokers());
                break;

            case BOOK_CARD_FROM_DECK:
                makeMoveBookCardFromDeck(move.getPlayer(), move.getCardLevel(), move.getRemoveRed(),
                        move.getRemoveGreen(), move.getRemoveBlue(), move.getRemoveBlack(), move.getRemoveWhite(),
                        move.getRemoveJokers());
        }

        turnTakingPlayer = getNextPlayer();
        if (turnTakingPlayer == Player.PLAYER0)
        {
            roundNumber++;
        }
    }

    private Player getNextPlayer() {
        int pos = turnTakingPlayer.ordinal();
        return Player.values()[(pos + 1) % playersNum];
    }

    /**
     * @return Current turn taking player
     */
    public Player getTurnTakingPlayer() {
        return turnTakingPlayer;
    }

    @Override
    public CoinSet getTableCoins() {
        return new CoinSet(
                tableCoins[TokenColor.RED.ordinal()],
                tableCoins[TokenColor.GREEN.ordinal()],
                tableCoins[TokenColor.BLUE.ordinal()],
                tableCoins[TokenColor.BLACK.ordinal()],
                tableCoins[TokenColor.WHITE.ordinal()],
                tableCoins[TokenColor.JOKER.ordinal()]);
    }

    @Override
    public VisitorCard[] getAllVisitors() {
        return visitorCardsArr;
    }

    @Override
    public List<VisitorCard> getPlayerVisitors(Player player) {
        ArrayList<VisitorCard> ret = new ArrayList<VisitorCard>();
        for (VisitorState vState : visitorStates) {
            if (vState.getVisitorPosition() == getVisitorPositionForPlayer(player)) {
                ret.add(visitorCardsArr[vState.getVisitorCardIndex()]);
            }
        }
        return ret;
    }

    @Override
    public List<VisitorCard> getAvailableVisitors() {
        ArrayList<VisitorCard> ret = new ArrayList<VisitorCard>();
        for (VisitorState vState : visitorStates) {
            if (vState.getVisitorPosition() == VisitorPosition.TABLE) {
                ret.add(visitorCardsArr[vState.getVisitorCardIndex()]);
            }
        }
        return ret;
    }

    @Override
    public List<VisitorCard> getTableVisitors() {
        ArrayList<VisitorCard> ret = new ArrayList<VisitorCard>();
        for (VisitorState vState : visitorStates) {
            if (vState.getVisitorPosition() != VisitorPosition.NOT_USED) {
                ret.add(visitorCardsArr[vState.getVisitorCardIndex()]);
            }
        }
        return ret;
    }

    @Override
    public List<Player> getWinners() {
        if (turnTakingPlayer == Player.PLAYER0) {
            ArrayList<Player> winners = new ArrayList<Player>();

            if(playersNum >= 1) {
                winners.add(Player.PLAYER0);
            }
            if(playersNum >= 2) {
                winners.add(Player.PLAYER1);
            }
            if(playersNum >= 3) {
                winners.add(Player.PLAYER2);
            }
            if(playersNum >= 4) {
                winners.add(Player.PLAYER3);
            }

            Collections.sort(winners, new Comparator<Player>() {
                @Override
                public int compare(Player o1, Player o2) {
                    int p1 = getPrestige(o1);
                    int p2 = getPrestige(o2);

                    if(p1 < p2) return 1;
                    if(p1 > p2) return -1;

                    int c1 = getPlayerTableCards(o1).size();
                    int c2 = getPlayerTableCards(o2).size();

                    if(c1 > c2) return 1;
                    if(c1 < c2) return -1;
                    return 0;
                }
            });

            for(int i = winners.size()-1; i >= 0; i--) {
                int prestige = getPrestige(winners.get(0));
                if(prestige < 15) {
                    winners.remove(i);
                }
            }

            if(winners.size() == 0) {
                return null;
            }

            int maxPrestige = getPrestige(winners.get(0));
            int minCards = getPlayerTableCards(winners.get(0)).size();
            for(int i = winners.size()-1; i >= 1; i--) {
                if(getPrestige(winners.get(i)) != maxPrestige || getPlayerTableCards(winners.get(i)).size() != minCards) {
                    winners.remove(i);
                }
            }

            return winners;
        } else {
            return null;
        }
    }

    /**
     * Gets and removes selected amount of coins.
     * @param player
     * @param getRed
     * @param getGreen
     * @param getBlue
     * @param getBlack
     * @param getWhite
     * @param removeRed
     * @param removeGreen
     * @param removeBlue
     * @param removeBlack
     * @param removeWhite
     * @param removeJokers
     * @throws InvalidMoveException if turn is illegal
     */
    protected void makeMoveGetCoins(Player player, int getRed, int getGreen, int getBlue, int getBlack, int getWhite, int removeRed, int removeGreen, int removeBlue, int removeBlack, int removeWhite, int removeJokers) {
        int getCoins[] = {0, 0, 0, 0, 0, 0};
        getCoins[TokenColor.RED.ordinal()] = getRed;
        getCoins[TokenColor.GREEN.ordinal()] = getGreen;
        getCoins[TokenColor.BLUE.ordinal()] = getBlue;
        getCoins[TokenColor.BLACK.ordinal()] = getBlack;
        getCoins[TokenColor.WHITE.ordinal()] = getWhite;

        //check if player is correct
        if(!checkPlayer(player)) {
            throw new InvalidMoveException("Selected player is invalid");
        }

        if(getRed < 0 || getGreen < 0 || getBlue < 0 || getBlack < 0 || getWhite < 0 ||
                removeRed < 0 || removeGreen < 0 || removeBlue < 0 || removeBlack < 0 || removeWhite < 0 || removeJokers < 0) {
            throw new InvalidMoveException("You can't take or remove negative amount of coins!");
        }

        //check if user takes one, two or three coins
        int occurrence0 = 0;
        int occurrence1 = 0;
        int occurrence2 = 0;
        for(int i = 0; i < 6; i++) {
            if(getCoins[i] == 0) {
                occurrence0++;
            } else if(getCoins[i] == 1) {
                occurrence1++;
            } else if(getCoins[i] == 2) {
                occurrence2++;
            }
        }

        if(getCoins[0] + getCoins[1] + getCoins[2] + getCoins[3] + getCoins[4] + getCoins[5] == 1) {
            //player can take one coin only when there is only one color left
            int notEmptyColors = 0;
            for (int i = 0; i < 6; i++) {
                if (tableCoins[i] != 0 && i != TokenColor.JOKER.ordinal()) {
                    notEmptyColors++;
                }
            }
            if(notEmptyColors > 1) {
                throw new InvalidMoveException("You can't take these coins");
            }

            //check if coins are available (only one coin in fact)
            if(tableCoins[TokenColor.RED.ordinal()] - getRed < 0 ||
                    tableCoins[TokenColor.GREEN.ordinal()] - getGreen < 0 ||
                    tableCoins[TokenColor.BLUE.ordinal()] - getBlue < 0 ||
                    tableCoins[TokenColor.BLACK.ordinal()] - getBlack < 0 ||
                    tableCoins[TokenColor.WHITE.ordinal()] - getWhite < 0) {
                throw new InvalidMoveException("There are not enough coins");
            }
        } else if(getCoins[0] + getCoins[1] + getCoins[2] + getCoins[3] + getCoins[4] + getCoins[5] == 2) {
            //check if player takes two coins of the same color, or two coins of different color
            if(!(occurrence0 == 5 && occurrence1 == 0 && occurrence2 == 1) && !(occurrence0 == 4 && occurrence1 == 2 && occurrence2 == 0)) {
                throw new InvalidMoveException("You can't take these coins");
            }

            //player can take two coins of the same color only when there are at least 4 coins of that color on table
            if(occurrence0 == 5 && occurrence1 == 0 && occurrence2 == 1) {
                boolean isAtLeast4 = false;
                for(int i = 0; i < 6; i++) {
                    if(getCoins[i] == 2 && tableCoins[i] >= 4) {
                        isAtLeast4 = true;
                    }
                }
                if(!isAtLeast4) {
                    throw new InvalidMoveException("There are not enough coins");
                }
            }
            //player can take two coins of different color only when there are only two colors left
            else if(occurrence0 == 4 && occurrence1 == 2 && occurrence2 == 0) {
                int notEmptyColors = 0;
                for (int i = 0; i < 6; i++) {
                    if (tableCoins[i] != 0 && i != TokenColor.JOKER.ordinal()) {
                        notEmptyColors++;
                    }
                }

                if (notEmptyColors != 2) {
                    throw new InvalidMoveException("You can't take these coins");
                }

                //check if coins are available
                if (tableCoins[TokenColor.RED.ordinal()] - getRed < 0 ||
                        tableCoins[TokenColor.GREEN.ordinal()] - getGreen < 0 ||
                        tableCoins[TokenColor.BLUE.ordinal()] - getBlue < 0 ||
                        tableCoins[TokenColor.BLACK.ordinal()] - getBlack < 0 ||
                        tableCoins[TokenColor.WHITE.ordinal()] - getWhite < 0) {
                    throw new InvalidMoveException("There are not enough coins");
                }
            }
        } else if(getCoins[0] + getCoins[1] + getCoins[2] + getCoins[3] + getCoins[4] + getCoins[5] == 3) {
            if(!(occurrence0 == 3 && occurrence1 == 3 && occurrence2 == 0)) {
                throw new InvalidMoveException("You can't take these coins");
            }

            if(tableCoins[TokenColor.RED.ordinal()] - getRed < 0 ||
                    tableCoins[TokenColor.GREEN.ordinal()] - getGreen < 0 ||
                    tableCoins[TokenColor.BLUE.ordinal()] - getBlue < 0 ||
                    tableCoins[TokenColor.BLACK.ordinal()] - getBlack < 0 ||
                    tableCoins[TokenColor.WHITE.ordinal()] - getWhite < 0) {
                throw new InvalidMoveException("There are not enough coins");
            }
        } else {
            throw new InvalidMoveException("You can't take these coins");
        }

        CoinSet coinSet = getPlayerCoins(player);

        if(doesNotHaveToRemoveSoManyCoins(coinSet, getRed, getGreen, getBlue, getBlack, getWhite, 0, removeRed, removeGreen, removeBlue, removeBlack, removeWhite, removeJokers)) {
            throw new InvalidMoveException("Player does not have to remove so many coins");
        }
        if(!hasEnoughCoinsToRemove(coinSet, getRed, getGreen, getBlue, getBlack, getWhite, 0, removeRed, removeGreen, removeBlue, removeBlack, removeWhite, removeJokers)) {
            throw new InvalidMoveException("Player does not have enough coins to remove");
        }
        if(hasMoreThenTenCoins(coinSet, getRed, getGreen, getBlue, getBlack, getWhite, 0, removeRed, removeGreen, removeBlue, removeBlack, removeWhite, removeJokers)) {
            throw new InvalidMoveException("Player would have more then 10 coins");
        }

        //take coins
        tableCoins[TokenColor.RED.ordinal()] += removeRed - getRed;
        tableCoins[TokenColor.GREEN.ordinal()] += removeGreen - getGreen;
        tableCoins[TokenColor.BLUE.ordinal()] += removeBlue - getBlue;
        tableCoins[TokenColor.BLACK.ordinal()] += removeBlack - getBlack;
        tableCoins[TokenColor.WHITE.ordinal()] += removeWhite - getWhite;
        tableCoins[TokenColor.JOKER.ordinal()] += removeJokers;

        int[] coinsArr;

        //give coins to player
        switch(player) {
            case PLAYER0:
                coinsArr = player0Coins;

                break;
            case PLAYER1:
                coinsArr = player1Coins;
                break;
            case PLAYER2:
                coinsArr = player2Coins;
                break;
            case PLAYER3:
                coinsArr = player3Coins;
                break;
            default:
                throw new InvalidMoveException("Selected player is invalid");
        }

        coinsArr[TokenColor.RED.ordinal()] += getRed - removeRed;
        coinsArr[TokenColor.GREEN.ordinal()] += getGreen - removeGreen;
        coinsArr[TokenColor.BLUE.ordinal()] += getBlue - removeBlue;
        coinsArr[TokenColor.BLACK.ordinal()] += getBlack - removeBlack;
        coinsArr[TokenColor.WHITE.ordinal()] += getWhite - removeWhite;
        coinsArr[TokenColor.JOKER.ordinal()] -= removeJokers;

        logMove(Move.getMoveTakeCoins(player, getRed, getGreen, getBlue, getBlack, getWhite, removeRed, removeGreen, removeBlue, removeBlack, removeWhite, removeJokers));
    }

    /**
     * Method reserves card from table and puts it in given players hand. If Jokers are available, it gives one Joker to the player.
     * @param player
     * @param level Level of the card
     * @param tablePos Table position of chosen card
     * @param removeRed
     * @param removeGreen
     * @param removeBlue
     * @param removeBlack
     * @param removeWhite
     * @param removeJokers
     * @throws InvalidMoveException if turn is illegal
     */
    protected void makeMoveBookCardFromTable(Player player, CardLevel level, int tablePos, int removeRed, int removeGreen, int removeBlue, int removeBlack, int removeWhite, int removeJokers) {
        //check if player is correct
        if(!checkPlayer(player)) {
            throw new InvalidMoveException("Selected player is invalid");
        }

        CardState cs = getCardStateFromTable(level, tablePos);

        //check if card is on table
        if(cs.getCardPosition() != CardPosition.TABLE) {
            throw new InvalidMoveException("Card is not on table");
        }

        //check if player has already more then 3 cards
        int cardsHeld = checkHowManyCardsInHand(player);

        //if yes, throw exception
        if(cardsHeld >= 3) {
            throw new InvalidMoveException("Cannot reserve more then 3 cards");
        }

        if(tableCoins[TokenColor.JOKER.ordinal()] > 0) {

            CoinSet playerCoins = getPlayerCoins(player);

            //check if player will end up with more then 10 coins and if there are enough coins to remove
            if(doesNotHaveToRemoveSoManyCoins(playerCoins, 0, 0, 0, 0, 0, 1, removeRed, removeGreen, removeBlue, removeBlack, removeWhite, removeJokers)) {
                throw new InvalidMoveException("Player does not have to remove so many coins");
            }
            if (!hasEnoughCoinsToRemove(playerCoins, 0, 0, 0, 0, 0, 1, removeRed, removeGreen, removeBlue, removeBlack, removeWhite, removeJokers)) {
                throw new InvalidMoveException("Player does not have enough coins to remove");
            }
            if (hasMoreThenTenCoins(playerCoins, 0, 0, 0, 0, 0, 1, removeRed, removeGreen, removeBlue, removeBlack, removeWhite, removeJokers)) {
                throw new InvalidMoveException("Player would have more then 10 coins");
            }

            //get one gold coin if available
            //take coins
            tableCoins[TokenColor.RED.ordinal()] += removeRed;
            tableCoins[TokenColor.GREEN.ordinal()] += removeGreen;
            tableCoins[TokenColor.BLUE.ordinal()] += removeBlue;
            tableCoins[TokenColor.BLACK.ordinal()] += removeBlack;
            tableCoins[TokenColor.WHITE.ordinal()] += removeWhite;
            tableCoins[TokenColor.JOKER.ordinal()] += removeJokers - 1;

            //give coins to player
            int[] playerCoinsArr = getPlayerCoinsArray(player);
            playerCoinsArr[TokenColor.RED.ordinal()] -= removeRed;
            playerCoinsArr[TokenColor.GREEN.ordinal()] -= removeGreen;
            playerCoinsArr[TokenColor.BLUE.ordinal()] -= removeBlue;
            playerCoinsArr[TokenColor.BLACK.ordinal()] -= removeBlack;
            playerCoinsArr[TokenColor.WHITE.ordinal()] -= removeWhite;
            playerCoinsArr[TokenColor.JOKER.ordinal()] -= removeJokers - 1;
        }

        //reserve card
        cs.setHandIndex(getFirstFreeHandIndexForPlayer(player));
        cs.setCardPosition(getHandPositionForPlayer(player));

        layOneCardFromDeck(cardsArr[cs.getCardIndex()].getLevel(), cs.getTableIndex());

        logMove(Move.getMoveBookCardFromTable(player, level, tablePos, removeRed, removeGreen, removeBlue, removeBlack, removeWhite, removeJokers));
    }

    /**
     * Method reserves card from deck and puts it in given players hand. If Jokers are available, it gives one Joker to the player.
     * @param player
     * @param cardLevel Level of the card
     * @param removeRed
     * @param removeGreen
     * @param removeBlue
     * @param removeBlack
     * @param removeWhite
     * @param removeJokers
     * @throws InvalidMoveException if turn is illegal
     */
    protected void makeMoveBookCardFromDeck(Player player, CardLevel cardLevel, int removeRed, int removeGreen, int removeBlue, int removeBlack, int removeWhite, int removeJokers) {
        //check if player is correct
        if(!checkPlayer(player)) {
            throw new InvalidMoveException("Selected player is invalid");
        }

        //change position of card to hand
        CardState cs = getCardFromDeck(cardLevel);
        if (null == cs) {
            throw new InvalidMoveException("Deck level "+cardLevel+" is empty");
        }

        //check if player has already more then 3 cards
        int cardsHeld = checkHowManyCardsInHand(player);
        if(cardsHeld >= 3) {
            throw new InvalidMoveException("Cannot reserve more then 3 cards");
        }

        if(tableCoins[TokenColor.JOKER.ordinal()] > 0) {

            //check if player will end up with more then 10 coins and if there are enough coins to remove
            CoinSet coinSet = getPlayerCoins(player);

            if(doesNotHaveToRemoveSoManyCoins(coinSet, 0, 0, 0, 0, 0, 1, removeRed, removeGreen, removeBlue, removeBlack, removeWhite, removeJokers)) {
                throw new InvalidMoveException("Player does not have to remove so many coins");
            }
            if (false == hasEnoughCoinsToRemove(coinSet, 0, 0, 0, 0, 0, 1, removeRed, removeGreen, removeBlue, removeBlack, removeWhite, removeJokers)) {
                throw new InvalidMoveException("Player does not have enough coins to remove");
            }
            if (hasMoreThenTenCoins(coinSet, 0, 0, 0, 0, 0, 1, removeRed, removeGreen, removeBlue, removeBlack, removeWhite, removeJokers)) {
                throw new InvalidMoveException("Player would have more then 10 coins");
            }

            //get one gold coin if available
            //take coins
            tableCoins[TokenColor.RED.ordinal()] += removeRed;
            tableCoins[TokenColor.GREEN.ordinal()] += removeGreen ;
            tableCoins[TokenColor.BLUE.ordinal()] += removeBlue;
            tableCoins[TokenColor.BLACK.ordinal()] += removeBlack;
            tableCoins[TokenColor.WHITE.ordinal()] += removeWhite;
            tableCoins[TokenColor.JOKER.ordinal()] += removeJokers - 1;

            //give coins to player
            int[] coins = getPlayerCoinsArray(player);
            coins[TokenColor.RED.ordinal()] -= removeRed;
            coins[TokenColor.GREEN.ordinal()] -= removeGreen;
            coins[TokenColor.BLUE.ordinal()] -= removeBlue;
            coins[TokenColor.BLACK.ordinal()] -= removeBlack;
            coins[TokenColor.WHITE.ordinal()] -= removeWhite;
            coins[TokenColor.JOKER.ordinal()] -= removeJokers - 1;
        }

        cs.setHandIndex(getFirstFreeHandIndexForPlayer(player));
        cs.setCardPosition(getHandPositionForPlayer(player));

        logMove(Move.getMoveBookCardFromDeck(player, cardLevel, removeRed, removeGreen, removeBlue, removeBlack, removeWhite, removeJokers));
    }

    /**
     * Method buys given card from table. It checks whether card can be bought first, and throws exception if it cannot.
     * @param player
     * @param level Level of the card
     * @param tableIndex Position of the card on table
     * @throws InvalidMoveException if turn is illegal
     */
    protected void makeMoveBuyCardFromTable(Player player, CardLevel level, int tableIndex) {

        CardState cardState = getCardStateFromTable(level, tableIndex);

        CoinSet coinSet = canBuyCardFromTable(player, cardState);
        if(coinSet == null) {
            throw new InvalidMoveException("Error buying card");
        }

        payForCard(player, coinSet);

        putCardOnTable(player, cardState);

        layOneCardFromDeck(cardsArr[cardState.getCardIndex()].getLevel(), cardState.getTableIndex());

        checkVisitors(player);

        logMove(Move.getMoveBuyCardFromTable(player, level, tableIndex));
    }

    /**
     * Method buys given card from hand of a given player. It checks whether card can be bought first, and throws exception if it cannot.
     * @param player
     * @param handIndex Position of the card in hand
     * @throws InvalidMoveException if turn is illegal
     */
    protected void makeMoveBuyCardFromHand(Player player, int handIndex) {

        Card[] playerCards = getPlayerBookedCards(player);
        if(playerCards[handIndex] == null) {
            throw new InvalidMoveException("No card in hand");
        }

        CoinSet coinSet = canBuyCardFromHand(player, handIndex);
        if(coinSet == null) {
            throw new InvalidMoveException("Error buying card");
        }

        payForCard(player, coinSet);

        for(CardState cs : cardStates) {
            if (cs.getCardPosition() == getHandPositionForPlayer(player) && cs.getHandIndex() == handIndex) {
                putCardOnTable(player, cs);
                break;
            }
        }

        checkVisitors(player);

        logMove(Move.getMoveBuyCardFromHand(player, handIndex));
    }

    /**
     * Method checks if a card from player's hand can be bought.
     * @param player
     * @param handIndex Position of the card in hand
     * @return CoinSet with coins needed to buy card, or NULL if card cannot be bought.
     * @throws InvalidMoveException if invalid player is selected
     */
    public CoinSet canBuyCardFromHand(Player player, int handIndex) {
        //check if player is correct
        if(!checkPlayer(player)) {
            throw new InvalidMoveException("Wrong player passed to canBuyCardFromHand()");
        }

        for(CardState cs : cardStates) {
            if (cs.getCardPosition() == getHandPositionForPlayer(player) && cs.getHandIndex() == handIndex) {
                return canBuyCardForPlayer(player, getCoinsTableForPlayer(player), cardsArr[cs.getCardIndex()]);
            }
        }

        return null;
    }

    @Override
    public Card[] getAllCards() {
        return cardsArr;
    }

    @Override
    public Card[] getTableCards(CardLevel level) {
        Card[] ret = new Card[4];
        for (CardState cardState : cardStates) {
            if (cardState.getCardPosition() == CardPosition.TABLE && cardsArr[cardState.getCardIndex()].getLevel() == level) {
                ret[cardState.getTableIndex()] = cardsArr[cardState.getCardIndex()];
            }
        }
        return ret;
    }

    @Override
    public int getCardsLeftNo(CardLevel level) {
        int ret = 0;
        for (CardState cardState : cardStates) {
            if (cardState.getCardPosition() == CardPosition.DECK && cardsArr[cardState.getCardIndex()].getLevel() == level) {
                ret++;
            }
        }
        return ret;
    }

    @Override
    public List<Card> getPlayerTableCards(Player player) {
        ArrayList<Card> ret = new ArrayList<Card>();
        for (CardState cardState : cardStates) {
            if (cardState.getCardPosition()== getTablePositionForPlayer(player)) {
                ret.add(cardsArr[cardState.getCardIndex()]);
            }
        }
        return ret;
    }

    /**
     * Returns a list of cards from a given color bought by player.
     * @param player
     * @param color
     * @return A list of cards
     */
    public List<Card> getPlayerTableCardsWithBonusColor(Player player, TokenColor color) {
        ArrayList<Card> ret = new ArrayList<Card>();
        for (CardState cardState : cardStates) {
            if (cardState.getCardPosition()== getTablePositionForPlayer(player) && cardsArr[cardState.getCardIndex()].getBonus() == color) {
                ret.add(cardsArr[cardState.getCardIndex()]);
            }
        }
        return ret;
    }

    @Override
    public Card[] getPlayerBookedCards(Player player) {
        Card[] ret = {null, null, null};

        for (CardState cardState : cardStates) {
            if (cardState.getCardPosition()== getHandPositionForPlayer(player)) {
                ret[cardState.getHandIndex()] = cardsArr[cardState.getCardIndex()];
            }
        }
        return ret;
    }

    /**
     * Returns coins for given player.
     * @param player
     * @return CoinSet for given player, NULL if player is invalid
     */
    @Override
    public CoinSet getPlayerCoins(Player player) {
        if(!checkPlayer(player)) {
            return null;
        }

        int[] playerCoins = getPlayerCoinsArray(player);
        return new CoinSet(
                playerCoins[TokenColor.RED.ordinal()],
                playerCoins[TokenColor.GREEN.ordinal()],
                playerCoins[TokenColor.BLUE.ordinal()],
                playerCoins[TokenColor.BLACK.ordinal()],
                playerCoins[TokenColor.WHITE.ordinal()],
                playerCoins[TokenColor.JOKER.ordinal()]);
    }

    /**
     * Returns a CoinBonusSet, made of given players coins and bonuses.
     * @param player Sum of coins and bonuses
     * @return
     */
    public CoinBonusSet getPlayerCoinsAndBonuses(Player player) {
        CoinBonusSet coinBonusSet = checkBonusesForPlayer(player);
        coinBonusSet.add(getPlayerCoins(player));

        return coinBonusSet;
    }

    /**
     * Returns coins for given player as an array of size 6.
     * @param player
     * @return int array of size 6, NULL if player is invalid
     */
    private int[] getPlayerCoinsArray(Player player) {
        if(!checkPlayer(player)) {
            return null;
        }

        switch(player) {
            case PLAYER0:
                return player0Coins;
            case PLAYER1:
                return player1Coins;
            case PLAYER2:
                return player2Coins;
            case PLAYER3:
                return player3Coins;
        }

        return null;
    }

    /**
     * Checks if player can buy selected card from table.
     * @param player
     * @param cardLevel Level of a card
     * @param tableIndex Table position of a card
     * @return
     */
    public CoinSet canBuyCardFromTable(Player player, CardLevel cardLevel, int tableIndex) {
        CardState cardState = getCardStateFromTable(cardLevel, tableIndex);

        return canBuyCardFromTable(player, cardState);
    }

    /**
     * Method returns CoinSet needed to buy a card with given cardId from given deck and given player.
     * @param player
     * @param cardState Card to check
     * @return CoinSet with coins needed to buy card, or null if card cannot be bought
     */
    private CoinSet canBuyCardFromTable(Player player, CardState cardState) {
        //check if player is correct
        if(!checkPlayer(player)) {
            return null;
        }

        //check if card is on table
        if( cardState.getCardPosition() != CardPosition.TABLE) {
            throw new InvalidMoveException("Tried to buy card from outside table");
        }

        return canBuyCardForPlayer(player, getPlayerCoinsArray(player), cardsArr[cardState.getCardIndex()]);
    }

    /**
     * Pays for card. Method subtracts CoinSet from the account of given player and adds it to table coins.
     * @param player
     * @param coinSet
     */
    private void payForCard(Player player, CoinSet coinSet) {

        int[] playerCoins = getPlayerCoinsArray(player);
        playerCoins[TokenColor.RED.ordinal()] -= coinSet.getRedCoins();
        playerCoins[TokenColor.GREEN.ordinal()] -= coinSet.getGreenCoins();
        playerCoins[TokenColor.BLUE.ordinal()] -= coinSet.getBlueCoins();
        playerCoins[TokenColor.BLACK.ordinal()] -= coinSet.getBlackCoins();
        playerCoins[TokenColor.WHITE.ordinal()] -= coinSet.getWhiteCoins();
        playerCoins[TokenColor.JOKER.ordinal()] -= coinSet.getJokers();

        tableCoins[TokenColor.RED.ordinal()] += coinSet.getRedCoins();
        tableCoins[TokenColor.GREEN.ordinal()] += coinSet.getGreenCoins();
        tableCoins[TokenColor.BLUE.ordinal()] += coinSet.getBlueCoins();
        tableCoins[TokenColor.BLACK.ordinal()] += coinSet.getBlackCoins();
        tableCoins[TokenColor.WHITE.ordinal()] += coinSet.getWhiteCoins();
        tableCoins[TokenColor.JOKER.ordinal()] += coinSet.getJokers();
    }

    /**
     * Searches for the first free slot in given player's hand.
     * @param player
     * @return Index of the first free slot, or -1 if all three are occupied
     */
    public int getFirstFreeHandIndexForPlayer(Player player) {
        int[] pos = {0,0,0};
        for(CardState cardState : cardStates) {
            if(cardState.getCardPosition() == getHandPositionForPlayer(player)) {
                pos[cardState.getHandIndex()]++;
            }
        }

        if(pos[0] == 0) {
            return 0;
        } else if(pos[1] == 0) {
            return 1;
        } else if(pos[2] == 0) {
            return 2;
        } else {
            return -1;
        }
    }

    /**
     * Gets prestige points for given player
     * @return number of prestige points
     */
    @Override
    public int getPrestige(Player player) {
        int myPrestige = 0;

        if(!checkPlayer(player)) {
            throw new InvalidMoveException("Invalid player");
        }

        for(CardState state : cardStates) {
            if(state.getCardPosition() == getTablePositionForPlayer(player)) {
                myPrestige += cardsArr[state.getCardIndex()].getPrestigePoints();
            }
        }

        for(VisitorState vstate : visitorStates) {
            if(vstate.getVisitorPosition() == getVisitorPositionForPlayer(player)) {
                myPrestige += visitorCardsArr[vstate.getVisitorCardIndex()].getPrestigePoints();
            }
        }

        return myPrestige;
    }

    /**
     * Method checks if given player can buy given card. CardId has to be correct, as well as other parameters.
     * @param player
     * @param playerCoins
     * @param card
     * @return coins set needed to buy given card, or NULL if card cannot be bought
     */
    private CoinBonusSet canBuyCardForPlayer(Player player, int[] playerCoins, Card card) {
        CoinBonusSet result = new CoinBonusSet();

        //get data
        int myJokers = playerCoins[TokenColor.JOKER.ordinal()];
        CoinBonusSet myBonuses = checkBonusesForPlayer(player);

        int redPrice = card.getRedPrice() - myBonuses.getRedBonuses();
        redPrice = redPrice > 0 ? redPrice : 0;
        int greenPrice = card.getGreenPrice() - myBonuses.getGreenBonuses();
        greenPrice = greenPrice > 0 ? greenPrice : 0;
        int bluePrice = card.getBluePrice() - myBonuses.getBlueBonuses();
        bluePrice = bluePrice > 0 ? bluePrice : 0;
        int blackPrice = card.getBlackPrice() - myBonuses.getBlackBonuses();
        blackPrice = blackPrice > 0 ? blackPrice : 0;
        int whitePrice = card.getWhitePrice() - myBonuses.getWhiteBonuses();
        whitePrice = whitePrice > 0 ? whitePrice : 0;

        result.setRedBonuses(card.getRedPrice() - redPrice);
        result.setGreenBonuses(card.getGreenPrice() - greenPrice);
        result.setBlueBonuses(card.getBluePrice() - bluePrice);
        result.setBlackBonuses(card.getBlackPrice() - blackPrice);
        result.setWhiteBonuses(card.getWhitePrice() - whitePrice);


        //check red coins
        if(redPrice > playerCoins[TokenColor.RED.ordinal()]) {
            if(myJokers >= redPrice - playerCoins[TokenColor.RED.ordinal()]) {
                myJokers -= redPrice - playerCoins[TokenColor.RED.ordinal()];
                result.setJokers(result.getJokers() + redPrice - playerCoins[TokenColor.RED.ordinal()]);
                result.setRedCoins(playerCoins[TokenColor.RED.ordinal()]);
            } else {
                return null;
            }
        } else {
            result.setRedCoins(redPrice);
        }

        //check green coins
        if(greenPrice > playerCoins[TokenColor.GREEN.ordinal()]) {
            if(myJokers >= greenPrice - playerCoins[TokenColor.GREEN.ordinal()]) {
                myJokers -= greenPrice - playerCoins[TokenColor.GREEN.ordinal()];
                result.setJokers(result.getJokers() + greenPrice - playerCoins[TokenColor.GREEN.ordinal()]);
                result.setGreenCoins(playerCoins[TokenColor.GREEN.ordinal()]);
            } else {
                return null;
            }
        } else {
            result.setGreenCoins(greenPrice);
        }

        //check blue coins
        if(bluePrice > playerCoins[TokenColor.BLUE.ordinal()]) {
            if(myJokers >= bluePrice - playerCoins[TokenColor.BLUE.ordinal()]) {
                myJokers -= bluePrice - playerCoins[TokenColor.BLUE.ordinal()];
                result.setJokers(result.getJokers() + bluePrice - playerCoins[TokenColor.BLUE.ordinal()]);
                result.setBlueCoins(playerCoins[TokenColor.BLUE.ordinal()]);
            } else {
                return null;
            }
        } else {
            result.setBlueCoins(bluePrice);
        }

        //check white coins
        if(whitePrice > playerCoins[TokenColor.WHITE.ordinal()]) {
            if(myJokers >= whitePrice - playerCoins[TokenColor.WHITE.ordinal()]) {
                myJokers -= whitePrice - playerCoins[TokenColor.WHITE.ordinal()];
                result.setJokers(result.getJokers() + whitePrice - playerCoins[TokenColor.WHITE.ordinal()]);
                result.setWhiteCoins(playerCoins[TokenColor.WHITE.ordinal()]);
            } else {
                return null;
            }
        } else {
            result.setWhiteCoins(whitePrice);
        }

        //check black coins
        if(blackPrice > playerCoins[TokenColor.BLACK.ordinal()]) {
            if(myJokers >= blackPrice - playerCoins[TokenColor.BLACK.ordinal()]) {
                myJokers -= blackPrice - playerCoins[TokenColor.BLACK.ordinal()];
                result.setJokers(result.getJokers() + blackPrice - playerCoins[TokenColor.BLACK.ordinal()]);
                result.setBlackCoins(playerCoins[TokenColor.BLACK.ordinal()]);
            } else {
                return null;
            }
        } else {
            result.setBlackCoins(blackPrice);
        }

        return result;
    }

    /**
     * Check visitors. Method calculates number of bonuses for given player, and checks which visitors can now visit the player.
     * It automatically changes found visitors status to PLAYERX_TABLE.
     * @param player
     */
    private void checkVisitors(Player player) {
        CoinBonusSet myBonuses = checkBonusesForPlayer( player);

        //check if we can get any of visitor cards, that lay on the table
        for(VisitorState vstate : visitorStates) {
            VisitorPosition position = vstate.getVisitorPosition();

            if(position == VisitorPosition.TABLE) {
                if( myBonuses.getRedBonuses() >= visitorCardsArr[vstate.getVisitorCardIndex()].getRedPrice() &&
                    myBonuses.getGreenBonuses() >= visitorCardsArr[vstate.getVisitorCardIndex()].getGreenPrice() &&
                    myBonuses.getBlueBonuses() >= visitorCardsArr[vstate.getVisitorCardIndex()].getBluePrice() &&
                    myBonuses.getBlackBonuses() >= visitorCardsArr[vstate.getVisitorCardIndex()].getBlackPrice() &&
                    myBonuses.getWhiteBonuses() >= visitorCardsArr[vstate.getVisitorCardIndex()].getWhitePrice()) {

                        vstate.setVisitorPosition( getVisitorPositionForPlayer(player) );
                }
            }
        }
    }

    /**
     * Check how many bonuses player has, looking in all three decks.
     * @return array of 6 bonuses in six different colors (note that jokers should be 0)
     */
    @Override
    public CoinBonusSet checkBonusesForPlayer(Player player) {

        int myBonuses[] = {0,0,0,0,0,0};

        for(CardState cstate : cardStates) {

            if(cstate.getCardPosition() == getTablePositionForPlayer(player)) {
                myBonuses[cardsArr[cstate.getCardIndex()].getBonus().ordinal()]++;
            }
        }
        return new CoinBonusSet(
                myBonuses[TokenColor.RED.ordinal()],
                myBonuses[TokenColor.GREEN.ordinal()],
                myBonuses[TokenColor.BLUE.ordinal()],
                myBonuses[TokenColor.BLACK.ordinal()],
                myBonuses[TokenColor.WHITE.ordinal()],
                0,0,0,0,0,0);
    }

    /**
     * Check how many cards are in hand of a given user (only from given deck).
     * @param player
     * @return i how many cards user holds
     */
    public int checkHowManyCardsInHand(Player player) {
        int cardsHeld = 0;
        for(CardState cardState : cardStates) {
            cardsHeld += (cardState.getCardPosition() == getHandPositionForPlayer(player)) ? 1 : 0;
        }
        return cardsHeld;
    }

    /**
     * Check if player number is correct with given number of players.
     * @param player
     * @return true if player is correct
     */
    private boolean checkPlayer(Player player) {
        switch (player) {
            case PLAYER0:
            case PLAYER1:
                return true; // no game with less than 2 players
            case PLAYER2:
                if(playersNum < 3) {
                    return false;
                }
                break;
            case PLAYER3:
                if(playersNum < 4) {
                    return false;
                }
                break;
        }

        return true;
    }

    /**
     * Gets card state of a given card from table.
     * @param level Level of the card
     * @param tableIdx Table position of the card
     * @return CardState object representing given card's position
     * @throws InvalidMoveException if card doesn't exist
     */
    private CardState getCardStateFromTable(CardLevel level, int tableIdx) {
        for(CardState cs : cardStates) {
            if ( cardsArr[cs.getCardIndex()].getLevel() == level && cs.getCardPosition() == CardPosition.TABLE && cs.getTableIndex() == tableIdx) {
                return cs;
            }
        }
        throw new InvalidMoveException("No card on "+tableIdx+" pos of deck "+level);
    }

    /**
     * Returns hand position element from CardPosition enum, for given player.
     * @param player
     * @return Hand card position for given player
     */
    private CardPosition getHandPositionForPlayer(Player player) {
        switch(player) {
            case PLAYER0:
                return CardPosition.PLAYER0_HAND;
            case PLAYER1:
                return CardPosition.PLAYER1_HAND;
            case PLAYER2:
                return CardPosition.PLAYER2_HAND;
            case PLAYER3:
                return CardPosition.PLAYER3_HAND;
        }
        throw new InvalidMoveException("Wrong player in getHandPositionForPlayer()");
    }

    /**
     * Returns table position element from CardPosition enum, for given player.
     * @param player
     * @return Table card position for given player
     */
    private CardPosition getTablePositionForPlayer(Player player) {
        switch(player) {
            case PLAYER0:
                return CardPosition.PLAYER0_TABLE;
            case PLAYER1:
                return CardPosition.PLAYER1_TABLE;
            case PLAYER2:
                return CardPosition.PLAYER2_TABLE;
            case PLAYER3:
                return CardPosition.PLAYER3_TABLE;
        }
        throw new InvalidMoveException("Wrong player in getTablePositionForPlayer()");
    }

    /**
     * Returns visitor table position element from VisitorPosition enum, for given player.
     * @param player
     * @return Visitor table card position for given player
     */
    private VisitorPosition getVisitorPositionForPlayer(Player player) {
        switch(player) {
            case PLAYER0:
                return VisitorPosition.PLAYER0_TABLE;
            case PLAYER1:
                return VisitorPosition.PLAYER1_TABLE;
            case PLAYER2:
                return VisitorPosition.PLAYER2_TABLE;
            case PLAYER3:
                return VisitorPosition.PLAYER3_TABLE;
        }
        throw new InvalidMoveException("Wrong player in getTablePositionForPlayer()");
    }

    /**
     * Returns 6-element coin array, for given player.
     * @param player
     * @return 6-element array, representing each coin color
     * @throws InvalidMoveException If player is invalid
     */
    private int[] getCoinsTableForPlayer(Player player) {
        switch(player) {
            case PLAYER0:
                return player0Coins;
            case PLAYER1:
                return player1Coins;
            case PLAYER2:
                return player2Coins;
            case PLAYER3:
                return player3Coins;
        }
        throw new InvalidMoveException("Wrong player in getCoinsTableForPlayer()");
    }

    /**
     * Checks if player has enough coins (including new ones) to remove specified amount of coins.
     * @param playerCoins
     * @param getRed
     * @param getGreen
     * @param getBlue
     * @param getBlack
     * @param getWhite
     * @param removeRed
     * @param removeGreen
     * @param removeBlue
     * @param removeBlack
     * @param removeWhite
     * @param removeJoker
     * @return
     */
    private boolean hasEnoughCoinsToRemove(CoinSet playerCoins,
                                           int getRed, int getGreen, int getBlue, int getBlack, int getWhite, int getJoker,
                                           int removeRed, int removeGreen, int removeBlue, int removeBlack, int removeWhite, int removeJoker) {

        if (
                    playerCoins.getRedCoins() + getRed < removeRed ||
                    playerCoins.getGreenCoins() + getGreen < removeGreen ||
                    playerCoins.getBlueCoins() + getBlue < removeBlue ||
                    playerCoins.getBlackCoins() + getBlack < removeBlack ||
                    playerCoins.getWhiteCoins() + getWhite < removeWhite ||
                    playerCoins.getJokers() + getJoker < removeJoker
                ) {
            return false;
        }
        return true;

    }

    /**
     * Checks if player does not have to remove given amount of coins.
     * @param playerCoins Given player coins
     * @param getRed Red coins player wants to take
     * @param getGreen Green coins player wants to take
     * @param getBlue Blue coins player wants to take
     * @param getBlack Black coins player wants to take
     * @param getWhite White coins player wants to take
     * @param getJoker Jokers player wants to take
     * @param removeRed Red coins player wants to remove
     * @param removeGreen Red coins player wants to remove
     * @param removeBlue Red coins player wants to remove
     * @param removeBlack Red coins player wants to remove
     * @param removeWhite Red coins player wants to remove
     * @param removeJoker Red coins player wants to remove
     * @return
     */
    private boolean doesNotHaveToRemoveSoManyCoins(CoinSet playerCoins,
                                                           int getRed, int getGreen, int getBlue, int getBlack, int getWhite, int getJoker,
                                                           int removeRed, int removeGreen, int removeBlue, int removeBlack, int removeWhite, int removeJoker) {

        int hasToRemove =
                       (playerCoins.getRedCoins() + getRed +
                        playerCoins.getGreenCoins() + getGreen +
                        playerCoins.getBlueCoins() + getBlue +
                        playerCoins.getBlackCoins() + getBlack +
                        playerCoins.getWhiteCoins() + getWhite +
                        playerCoins.getJokers() + getJoker) - 10;
        hasToRemove = hasToRemove >= 0 ? hasToRemove : 0;

        int coinsToRemove = removeRed + removeGreen + removeBlue + removeBlack + removeWhite + removeJoker;

        return coinsToRemove > hasToRemove;

    }

    /**
     * Checks if player would end up with more then 10 coins after added new ones and deleting specified amount.
     * @param playerCoins
     * @param getRed
     * @param getGreen
     * @param getBlue
     * @param getBlack
     * @param getWhite
     * @param removeRed
     * @param removeGreen
     * @param removeBlue
     * @param removeBlack
     * @param removeWhite
     * @param removeJoker
     * @return
     */
    private boolean hasMoreThenTenCoins(CoinSet playerCoins,
                                        int getRed, int getGreen, int getBlue, int getBlack, int getWhite, int getJoker,
                                        int removeRed, int removeGreen, int removeBlue, int removeBlack, int removeWhite, int removeJoker) {

        int coins =
            playerCoins.getRedCoins() + getRed - removeRed+
            playerCoins.getGreenCoins() + getGreen - removeGreen +
            playerCoins.getBlueCoins() + getBlue - removeBlue +
            playerCoins.getBlackCoins() + getBlack - removeBlack +
            playerCoins.getWhiteCoins() + getWhite - removeWhite +
            playerCoins.getJokers() + getJoker - removeJoker;

        return coins > 10;
    }

    /**
     * Changes position of card to player table.
     * @param player
     * @param cardState Card to check
     */
    private void putCardOnTable(Player player, CardState cardState) {
        //change position of card to table
        cardState.setCardPosition(getTablePositionForPlayer(player));
    }

    /**
     * Take first card from given deck and change it position to table.
     * If deck is empty, do nothing.
     * @param level Level of a card to draw
     * @param tableIndex Table position, where the card should be put
     */
    private void layOneCardFromDeck(CardLevel level, int tableIndex) {
        CardState cs = getCardFromDeck(level);
        if (null != cs) {
            cs.setCardPosition(CardPosition.TABLE);
            cs.setTableIndex(tableIndex);
        }
    }

    /**
     * Take first card from given deck and change it position to table.
     * If deck is empty, do nothing.
     * @param level Level of a card to draw
     */
    private CardState getCardFromDeck(CardLevel level) {
        for(CardState card : cardStates) {
            if (cardsArr[card.getCardIndex()].getLevel() == level && card.getCardPosition() == CardPosition.DECK) {
                return card;
            }
        }
        return null;
    }


    /**
     * Saves last move to game state.
     * @param newMove
     */
    private void logMove(Move newMove) {
        lastMove = newMove;
    }


    public Move getLastMove() {
        return lastMove;
    }

    @Override
    public int getPlayersNum() {
        return playersNum;
    }

    /**
     * Returns array of coins available on table.
     * @return Array of coins
     */
    private int[] getTableCoinsArray() {
        return tableCoins;
    }

    public int getRoundNumber(){
        return roundNumber;
    }

    /**
     * Method initiates game state with provided setup.
     * @param setup Debug setup
     */
    public void prepareDebugSetup(IDebugSetup setup){
        if (false == isDebug){
            throw new RuntimeException("Action forbidden, only allowed in test");
        }

        CoinSet set = setup.getPlayer0CoinSet();
        if (null != set){
            player0Coins = new int[]{set.getRedCoins(), set.getGreenCoins(), set.getBlueCoins(), set.getBlackCoins(), set.getWhiteCoins(), set.getJokers()};
        }
        set = setup.getTableCoinSet();
        if (null != set){
            tableCoins = new int[]{set.getRedCoins(), set.getGreenCoins(), set.getBlueCoins(), set.getBlackCoins(), set.getWhiteCoins(), set.getJokers()};
        }

        int[] list = setup.getPlayer0BookedCards();
        if(list != null) {
            int i = 0;
            for(int id : list) {
                for(CardState state : cardStates) {
                    if(state.getCardIndex() == id) {
                        state.setCardPosition(CardPosition.PLAYER0_HAND);
                        state.setHandIndex(i);
                        i++;
                        break;
                    }
                }
            }
        }

        list = setup.getPlayer0OwnedCards();
        if(list != null) {
            for(int id : list) {
                for(CardState state : cardStates) {
                    if(state.getCardIndex() == id) {
                        state.setCardPosition(CardPosition.PLAYER0_TABLE);
                        break;
                    }
                }
            }
        }

        list = setup.getPlayer1OwnedCards();
        if(list != null) {
            for(int id : list) {
                for(CardState state : cardStates) {
                    if(state.getCardIndex() == id) {
                        state.setCardPosition(CardPosition.PLAYER1_TABLE);
                        break;
                    }
                }
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseGameState that = (BaseGameState) o;

        if (isDebug != that.isDebug) return false;
        if (playersNum != that.playersNum) return false;
        if (!cardStates.equals(that.cardStates)) return false;
        if (!Arrays.equals(cardsArr, that.cardsArr)) return false;
        if (!Arrays.equals(player0Coins, that.player0Coins)) return false;
        if (!Arrays.equals(player1Coins, that.player1Coins)) return false;
        if (!Arrays.equals(player2Coins, that.player2Coins)) return false;
        if (!Arrays.equals(player3Coins, that.player3Coins)) return false;
        if (!Arrays.equals(tableCoins, that.tableCoins)) return false;
        if (!Arrays.equals(visitorCardsArr, that.visitorCardsArr)) return false;
        if (!visitorStates.equals(that.visitorStates)) return false;

        // NO lastmove!
        return true;
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(cardsArr);
        result = 31 * result + Arrays.hashCode(visitorCardsArr);
        result = 31 * result + (isDebug ? 1 : 0);
        result = 31 * result + playersNum;
        result = 31 * result + cardStates.hashCode();
        result = 31 * result + visitorStates.hashCode();
        result = 31 * result + Arrays.hashCode(tableCoins);
        result = 31 * result + Arrays.hashCode(player0Coins);
        result = 31 * result + Arrays.hashCode(player1Coins);
        result = 31 * result + Arrays.hashCode(player2Coins);
        result = 31 * result + Arrays.hashCode(player3Coins);

        // NO lastmove!
        return result;
    }

    /**
     * Serializes game state to JSON.
     * @return String with JSON data
     */
    public String toJsonBase() {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(this);
    }

    /**
     * Deserializes game state from JSON.
     * @param json String with JSON data
     * @return Game state object
     */
    public static BaseGameState deserializeBase(String json){
        if (null == json){
            return null;
        }

        Gson gson = new Gson();
        BaseGameState deserialized = gson.fromJson(json, BaseGameState.class);
        deserialized.cardsArr = cardsNormal;
        deserialized.visitorCardsArr = visitorCardsNormal;

        return deserialized;
    }

}
