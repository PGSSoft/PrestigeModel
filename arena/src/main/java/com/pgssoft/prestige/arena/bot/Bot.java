package com.pgssoft.prestige.arena.bot;

import com.pgssoft.prestige.arena.utils.Pair;
import com.pgssoft.prestige.model.enums.CardLevel;
import com.pgssoft.prestige.model.enums.Player;
import com.pgssoft.prestige.model.game.Card;
import com.pgssoft.prestige.model.game.CoinBonusSet;
import com.pgssoft.prestige.model.game.CoinSet;
import com.pgssoft.prestige.model.game.IBaseGameState;
import com.pgssoft.prestige.model.game.Move;

import java.util.ArrayList;

/**
 * Created by bstokrocki on 2014-11-18.
 */


public abstract class Bot implements IBot{
    enum CARD_PRICE {
        CHEAPEST,
        CLOSEST
    }

    Player playerId;

    public Bot(Player player) {
        playerId = player;
    }

    public abstract Move makeMove(IBaseGameState state);
    protected abstract CoinSet selectCoinsToTake(IBaseGameState state);
    protected abstract CoinSet selectCoinsToGiveBack(IBaseGameState state, CoinSet coinsToTake);
    protected abstract Pair<CardLevel, Integer> selectCardToBook(IBaseGameState state);

    public Player getPlayerId() {
        return playerId;
    }

    /**
     * Buys given card from table
     * @param state
     * @param card
     * @param tableIndex
     * @return Move for buying card from table
     */
    protected Move buyCardFromTable(IBaseGameState state, CardLevel cardLevel, int tableIndex) {
        CoinSet coinSet = state.canBuyCardFromTable(playerId, cardLevel, tableIndex);
        if (coinSet != null) {
            return Move.getMoveBuyCardFromTable(playerId, cardLevel, tableIndex);
        } else {
            return null;
        }
    }

    /**
     * Buys given card from hand
     * @param state
     * @param card
     * @param tableIndex
     * @return Move for buying card from hand
     */
    protected Move buyCardFromHand(IBaseGameState state, int handIndex) {
        CoinSet coinSet = state.canBuyCardFromHand(playerId, handIndex);
        if (coinSet != null) {
            return Move.getMoveBuyCardFromHand(playerId, handIndex);
        } else {
            return null;
        }
    }

    /**
     * Takes coins specified in coinsToTake from table
     * @param coinsToTake Coins to take from table
     * @param coinsToRemove Coins to give back if player would have more than 10 coins (null otherwise)
     * @return Move for taking coins
     */
    protected Move takeCoins(IBaseGameState state) {
        CoinSet coinsToTake = selectCoinsToTake(state);
        if (getCoinsCount(coinsToTake) + getCoinsCount(state.getPlayerCoins(playerId)) > 10) {
            CoinSet coinsToRemove = selectCoinsToGiveBack(state, coinsToTake);
            return Move.getMoveTakeCoins(playerId, coinsToTake.getRedCoins(), coinsToTake.getGreenCoins(),
                    coinsToTake.getBlueCoins(), coinsToTake.getBlackCoins(), coinsToTake.getWhiteCoins(),
                    coinsToRemove.getRedCoins(), coinsToRemove.getGreenCoins(), coinsToRemove.getBlueCoins(),
                    coinsToRemove.getBlackCoins(), coinsToRemove.getWhiteCoins(), coinsToRemove.getJokers());
        } else {
            return Move.getMoveTakeCoins(playerId, coinsToTake.getRedCoins(), coinsToTake.getGreenCoins(),
                    coinsToTake.getBlueCoins(), coinsToTake.getBlackCoins(), coinsToTake.getWhiteCoins(),
                    0, 0, 0, 0, 0, 0);
        }
    }

    /**
     * Books card from table
     * @param cardLevel
     * @param tableIndex
     * @param coinsToRemove
     * @return Move for booking card from table
     */
    protected Move bookCardFromTable(IBaseGameState state) {
        Pair<CardLevel, Integer> cheapestCard = selectCardToBook(state);
        CoinSet coinsToRemove = getCoinsCount(state.getPlayerCoins(playerId)) >= 10 ?
                selectCoinsToGiveBack(state, new CoinSet(0, 0, 0, 0, 0, 1)) :
                null;

        return coinsToRemove != null ?
                Move.getMoveBookCardFromTable(playerId, cheapestCard.getKey(), cheapestCard.getValue(),
                        coinsToRemove.getRedCoins(), coinsToRemove.getGreenCoins(),
                        coinsToRemove.getBlueCoins(), coinsToRemove.getBlackCoins(),
                        coinsToRemove.getWhiteCoins(), coinsToRemove.getJokers()) :
                Move.getMoveBookCardFromTable(playerId, cheapestCard.getKey(), cheapestCard.getValue(),
                        0, 0, 0, 0, 0, 0);
    }

    protected Integer[] getReachableCardsFromTable(IBaseGameState state, CardLevel level) {
        Card[] levelCards = state.getTableCards(level);
        ArrayList<Integer> cardIndices = new ArrayList<Integer>();

        for (int i = 0; i < levelCards.length; i++) {
            Card card = levelCards[i];
            if (card != null) {
                if (state.canBuyCardFromTable(playerId, level, i) != null) {
                    cardIndices.add(i);
                }
            }
        }

        return cardIndices.toArray(new Integer[cardIndices.size()]);
    }

    protected Integer[] getReachableCardsFromHand(IBaseGameState state) {
        Card[] levelCards = state.getPlayerBookedCards(playerId);
        ArrayList<Integer> cardIndices = new ArrayList<Integer>();

        for (int i = 0; i < levelCards.length; i++) {
            Card card = levelCards[i];
            if (card != null) {
                if (state.canBuyCardFromHand(playerId, i) != null) {
                    cardIndices.add(i);
                }
            }
        }

        return cardIndices.toArray(new Integer[cardIndices.size()]);
    }

    protected CoinSet updateCoinsArrayToSet(Integer[] coinArray, int amountOfSingleCoinToTake) {
        CoinSet result = new CoinSet();
        for (Integer i : coinArray) {
            updateCoinSet(result, i, amountOfSingleCoinToTake);
        }

        return result;
    }

    private void updateCoinSet(CoinSet set, int index, int value) {
        switch (index) {
            case 0:
                set.setRedCoins(value);
                return;
            case 1:
                set.setGreenCoins(value);
                return;
            case 2:
                set.setBlueCoins(value);
                return;
            case 3:
                set.setBlackCoins(value);
                return;
            case 4:
                set.setWhiteCoins(value);
                return;
        }
    }

    protected int getCoinCountByIndex(CoinSet coinSet, int index) {
        switch (index) {
            case 0:
                return coinSet.getRedCoins();
            case 1:
                return coinSet.getGreenCoins();
            case 2:
                return coinSet.getBlueCoins();
            case 3:
                return coinSet.getBlackCoins();
            case 4:
                return coinSet.getWhiteCoins();
            case 5:
                return coinSet.getJokers();
            default:
                return 0;
        }
    }

    protected int getCardCoinsCount(Card card) {
        return card.getRedPrice() + card.getGreenPrice() + card.getBluePrice() +
                card.getBlackPrice() + card.getWhitePrice();
    }


    protected int getCoinsCount(CoinSet coins) {
        return coins.getRedCoins() + coins.getGreenCoins() + coins.getBlueCoins() +
                coins.getBlackCoins() + coins.getWhiteCoins() + coins.getJokers();
    }

    protected ArrayList<Integer> getNonEmptyCoinsIndices(CoinSet tableCoins) {
        ArrayList<Integer> nonEmptyColors = new ArrayList<Integer>();

        if (tableCoins.getRedCoins() > 0) {
            nonEmptyColors.add(0);
        }
        if (tableCoins.getGreenCoins() > 0) {
            nonEmptyColors.add(1);
        }
        if (tableCoins.getBlueCoins() > 0) {
            nonEmptyColors.add(2);
        }
        if (tableCoins.getBlackCoins() > 0) {
            nonEmptyColors.add(3);
        }
        if (tableCoins.getWhiteCoins() > 0) {
            nonEmptyColors.add(4);
        }

        return nonEmptyColors;
    }

    protected ArrayList<Integer> getGapOnTableIndices(IBaseGameState state, Pair<CardLevel, Integer> closestCardPair) {
        return getGapOnTableIndices(state, state.getTableCards(closestCardPair.getKey())[closestCardPair.getValue()]);
    }

    protected ArrayList<Integer> getGapOnTableIndices(IBaseGameState state, Integer handIndex) {
        return getGapOnTableIndices(state, state.getPlayerBookedCards(playerId)[handIndex]);
    }

    private ArrayList<Integer> getGapOnTableIndices(IBaseGameState state, Card card) {
        CoinSet tableCoins = state.getTableCoins();
        CoinSet gapCoins = getCoinsGap(state, card);

        ArrayList<Integer> nonEmptyTableCoins = getNonEmptyCoinsIndices(tableCoins);
        ArrayList<Integer> nonEmptyGapCoins = getNonEmptyCoinsIndices(gapCoins);
        ArrayList<Integer> gapOnTable = new ArrayList<Integer>();

        for (Integer gapIndex : nonEmptyGapCoins) {
            for (Integer tableIndex : nonEmptyTableCoins) {
                if (gapIndex.equals(tableIndex)) {
                    gapOnTable.add(tableIndex);
                    break;
                }
            }
        }

        return gapOnTable;
    }

    protected CoinSet getCoinsGap(IBaseGameState state, Pair<CardLevel, Integer> cardFromTable) {
        return getCoinsGap(state, state.getTableCards(cardFromTable.getKey())[cardFromTable.getValue()]);
    }

    protected CoinSet getCoinsGap(IBaseGameState state, int cardFromHandIndex) {
        return getCoinsGap(state, state.getPlayerBookedCards(playerId)[cardFromHandIndex]);
    }

    protected CoinSet getCoinsGap(IBaseGameState state, Card card) {
        CoinSet playersCoins = getPlayerResourcesAsCoins(state);

        return new CoinSet(
                Math.max(0, card.getRedPrice() - playersCoins.getRedCoins()),
                Math.max(0, card.getGreenPrice() - playersCoins.getGreenCoins()),
                Math.max(0, card.getBluePrice() - playersCoins.getBlueCoins()),
                Math.max(0, card.getBlackPrice() - playersCoins.getBlackCoins()),
                Math.max(0, card.getWhitePrice() - playersCoins.getWhiteCoins()),
                0);
    }

    protected int getGapInTurns(IBaseGameState state, Card card) {
        CoinSet coinGap = getCoinsGap(state, card);
        ArrayList<Integer> nonEmptyStacks = getNonEmptyCoinsIndices(coinGap);
        Integer[] coinGapArray = coinSetToArray(coinGap);
        int turnCount = 0;

        while (nonEmptyStacks.size() > 0) {
            int size = nonEmptyStacks.size();
            if (size > 1) {
                for (int i = Math.min(size, 3) - 1; i >= 0 ; i--) {
                    int index = nonEmptyStacks.get(i);
                    coinGapArray[index] -= 1;
                    if (coinGapArray[index] < 1) {
                        nonEmptyStacks.remove(i);
                    }
                }
            } else if (nonEmptyStacks.size() == 1) {
                if (coinGapArray[nonEmptyStacks.get(0)] > 2) {
                    coinGapArray[nonEmptyStacks.get(0)] -= 2;
                } else {
                    nonEmptyStacks.remove(0);
                }
            }

            turnCount++;
        }

        return turnCount;
    }

    protected CoinSet getPlayerResourcesAsCoins(IBaseGameState state) {
        CoinBonusSet playersBonuses = state.checkBonusesForPlayer(playerId);
        CoinSet playersCoins = state.getPlayerCoins(playerId);

        return new CoinSet(
                playersBonuses.getRedBonuses() + playersCoins.getRedCoins(),
                playersBonuses.getGreenBonuses() + playersCoins.getGreenCoins(),
                playersBonuses.getBlueBonuses() + playersCoins.getBlueCoins(),
                playersBonuses.getBlackBonuses() + playersCoins.getBlackCoins(),
                playersBonuses.getWhiteBonuses() + playersCoins.getWhiteCoins(),
                playersCoins.getJokers());
    }

    protected Integer[] coinSetToArray(CoinSet coinSet) {
        return new Integer[] {
                coinSet.getRedCoins(),
                coinSet.getGreenCoins(),
                coinSet.getBlueCoins(),
                coinSet.getBlackCoins(),
                coinSet.getWhiteCoins(),
                coinSet.getJokers()
        };
    }

    protected CoinSet arrayToCoinSet(Integer[] array) {
        return new CoinSet(
                array[0],
                array[1],
                array[2],
                array[3],
                array[4],
                array[5]
        );
    }

    protected Integer[] cardPriceToArray(Card card) {
        return new Integer[] {
                card.getRedPrice(),
                card.getGreenPrice(),
                card.getBluePrice(),
                card.getBlackPrice(),
                card.getWhitePrice(),
        };
    }

    protected CoinSet cardPriceToCoinSet(Card card) {
        return new CoinSet(
                card.getRedPrice(),
                card.getGreenPrice(),
                card.getBluePrice(),
                card.getBlackPrice(),
                card.getWhitePrice(),
                0);
    }
}
