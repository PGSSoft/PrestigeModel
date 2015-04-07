package com.pgssoft.prestige.arena.bot;

import com.pgssoft.prestige.arena.Arena;
import com.pgssoft.prestige.arena.utils.Pair;
import com.pgssoft.prestige.model.enums.CardLevel;
import com.pgssoft.prestige.model.enums.Player;
import com.pgssoft.prestige.model.game.Card;
import com.pgssoft.prestige.model.game.CoinSet;
import com.pgssoft.prestige.model.game.IBaseGameState;
import com.pgssoft.prestige.model.game.Move;

import java.util.ArrayList;

/**
 * Created by bstokrocki on 2014-11-19.
 */
public class BuyerBot extends Bot {

    public BuyerBot(Player player) {
        super(player);
    }

    @Override
    public String getName() {
        return "James Bot";
    }

    int turnsTaken = 0;

    @Override
    public Move makeMove(IBaseGameState state) {
        turnsTaken++;
        Integer closestFromHand = getClosestReachableCardFromHand(state);
        Pair<CardLevel, Integer> closestFromTable = getBestReachableCardFromTable(state);

        if (closestFromHand != null) {
            if (closestFromTable == null || (closestFromTable != null &&
                    getCardCoinsCount(state.getPlayerBookedCards(playerId)[closestFromHand])
                            <= getCardCoinsCount(state.getTableCards(closestFromTable.getKey())[closestFromTable.getValue()]))) {
                return buyCardFromHand(state, closestFromHand);
            }
        }

        if (closestFromTable != null) {
            return buyCardFromTable(state, closestFromTable.getKey(), closestFromTable.getValue());
        }

        CoinSet coinsToTake = selectCoinsToTake(state);
        if (coinsToTake != null) {
            return takeCoins(state);
        } else {
            return bookCardFromTable(state);
        }
    }

    @Override
    protected CoinSet selectCoinsToTake(IBaseGameState state) {
        CoinSet tableCoins = state.getTableCoins();
        Pair<CardLevel, Integer> closestCardPair = getCardFromTable(state);

        ArrayList<Integer> nonEmptyTableCoins = getNonEmptyCoinsIndices(tableCoins);
        ArrayList<Integer> gapOnTable = getGapOnTableIndices(state, closestCardPair);
        int gapOnTableSize = gapOnTable.size();

        if (gapOnTableSize > 2) {
            while (gapOnTable.size() > 3) {
                gapOnTable.remove(Arena.RANDOM.nextInt(gapOnTable.size()));
            }
            return updateCoinsArrayToSet(gapOnTable.toArray(new Integer[3]), 1);

        } else if (gapOnTableSize == 2) {
            if (nonEmptyTableCoins.size() > 2) {
                nonEmptyTableCoins.remove(gapOnTable.get(0));
                nonEmptyTableCoins.remove(gapOnTable.get(1));
                gapOnTable.add(nonEmptyTableCoins.get(Arena.RANDOM.nextInt(nonEmptyTableCoins.size())));
                return updateCoinsArrayToSet(gapOnTable.toArray(new Integer[3]), 1);
            }

            if (nonEmptyTableCoins.size() == 2) {
                if (getCoinCountByIndex(tableCoins, nonEmptyTableCoins.get(0)) > 3) {
                    return updateCoinsArrayToSet(new Integer[]{nonEmptyTableCoins.get(0)}, 2);
                }

                if (getCoinCountByIndex(tableCoins, nonEmptyTableCoins.get(1)) > 3) {
                    return updateCoinsArrayToSet(new Integer[]{nonEmptyTableCoins.get(1)}, 2);
                }
                return updateCoinsArrayToSet(gapOnTable.toArray(new Integer[2]), 1);
            }
        }

        if (gapOnTableSize == 1) {
            if (nonEmptyTableCoins.size() > 2) {
                nonEmptyTableCoins.remove(gapOnTable.get(0));
                gapOnTable.add(nonEmptyTableCoins.remove(Arena.RANDOM.nextInt(nonEmptyTableCoins.size())));
                gapOnTable.add(nonEmptyTableCoins.remove(Arena.RANDOM.nextInt(nonEmptyTableCoins.size())));

                return updateCoinsArrayToSet(gapOnTable.toArray(new Integer[3]), 1);
            }

            if (nonEmptyTableCoins.size() == 2) {
                if (getCoinCountByIndex(tableCoins, nonEmptyTableCoins.get(0)) > 3) {
                    return updateCoinsArrayToSet(new Integer[]{nonEmptyTableCoins.get(0)}, 2);
                }

                if (getCoinCountByIndex(tableCoins, nonEmptyTableCoins.get(1)) > 3) {
                    return updateCoinsArrayToSet(new Integer[]{nonEmptyTableCoins.get(1)}, 2);
                }

                return updateCoinsArrayToSet(nonEmptyTableCoins.toArray(new Integer[2]), 1);
            }

            if (nonEmptyTableCoins.size() == 1) {
                return updateCoinsArrayToSet(new Integer[]{gapOnTable.get(0)}, 1);
            }
        }

        if(gapOnTableSize == 0) {
            if (nonEmptyTableCoins.size() > 3) {
                nonEmptyTableCoins.remove(0);

                return updateCoinsArrayToSet(nonEmptyTableCoins.toArray(new Integer[3]), 1);
            }

            if(nonEmptyTableCoins.size() == 3) {
                return updateCoinsArrayToSet(nonEmptyTableCoins.toArray(new Integer[3]), 1);
            }

            if (nonEmptyTableCoins.size() == 2) {
                if (getCoinCountByIndex(tableCoins, nonEmptyTableCoins.get(0)) > 3) {
                    return updateCoinsArrayToSet(new Integer[]{nonEmptyTableCoins.get(0)}, 2);
                }

                if (getCoinCountByIndex(tableCoins, nonEmptyTableCoins.get(1)) > 3) {
                    return updateCoinsArrayToSet(new Integer[]{nonEmptyTableCoins.get(1)}, 2);
                }

                return updateCoinsArrayToSet(nonEmptyTableCoins.toArray(new Integer[2]), 1);
            }

            if (nonEmptyTableCoins.size() == 1) {
                return updateCoinsArrayToSet(new Integer[]{nonEmptyTableCoins.get(0)}, 1);
            }
        }

        return null;
    }

    @Override
    protected CoinSet selectCoinsToGiveBack(IBaseGameState state, CoinSet coinsToTake) {
        CoinSet playerCoins = state.getPlayerCoins(playerId);
        int coinsToGiveBackAmount = getCoinsCount(playerCoins) + getCoinsCount(coinsToTake) - 10;
        Pair<CardLevel, Integer> closestFromTable = getCardFromTable(state);
        Integer[] closestCardCost = cardPriceToArray(state.getTableCards(closestFromTable.getKey())[closestFromTable.getValue()]);

        Integer[] result = coinSetToArray(new CoinSet());

        CoinSet sum = new CoinSet();
        sum.add(coinsToTake);
        sum.add(getPlayerResourcesAsCoins(state));
        Integer[] playerAllResourcesArray = coinSetToArray(sum);

        CoinSet sum2 = new CoinSet();
        sum2.add(coinsToTake);
        sum2.add(playerCoins);
        Integer[] playerCoinsResourcesArray = coinSetToArray(sum2);


        for (int i = 0; i < coinsToGiveBackAmount; i++) {
            for (int j = 0; j < closestCardCost.length; j++) {
                if (playerAllResourcesArray[j] > closestCardCost[j] && playerCoinsResourcesArray[j] > 0) {
                    result[j] += 1;
                    playerAllResourcesArray[j] -= 1;
                    playerCoinsResourcesArray[j] -= 1;
                    coinsToGiveBackAmount -= 1;
                    break;
                }
            }
        }

        for(int i = 0; i < coinsToGiveBackAmount; i++) {
            for(int j = 0; j < closestCardCost.length; j++) {
                if(playerCoinsResourcesArray[j] > 0) {
                    result[j] += 1;
                    playerAllResourcesArray[j] -= 1;
                    playerCoinsResourcesArray[j] -= 1;
                    coinsToGiveBackAmount -= 1;
                    break;
                }
            }
        }

        return arrayToCoinSet(result);
    }

    @Override
    protected Pair<CardLevel, Integer> selectCardToBook(IBaseGameState state) {
        return getCardFromTable(state);
    }

    private Pair<CardLevel, Integer> getBestReachableCardFromTable(IBaseGameState state) {
        Integer[] reachableLvlThree = getReachableCardsFromTable(state, CardLevel.THREE);
        for (Integer index : reachableLvlThree) {
            CoinSet coinsGap = state.canBuyCardFromTable(playerId, CardLevel.THREE, index);
            if (coinsGap != null) {
                return new Pair<CardLevel, Integer>(CardLevel.THREE, index);
            }
        }

        Integer[] reachableLvlTwo = getReachableCardsFromTable(state, CardLevel.TWO);
        for (Integer index : reachableLvlTwo) {
            CoinSet coinsGap = state.canBuyCardFromTable(playerId, CardLevel.TWO, index);
            if (coinsGap != null) {
                return new Pair<CardLevel, Integer>(CardLevel.TWO, index);
            }
        }

        Integer[] reachableLvlOne = getReachableCardsFromTable(state, CardLevel.ONE);
        for (Integer index : reachableLvlOne) {
            CoinSet coinsGap = state.canBuyCardFromTable(playerId, CardLevel.ONE, index);
            if (coinsGap != null) {
                return new Pair<CardLevel, Integer>(CardLevel.ONE, index);
            }
        }

        return null;
    }

    private Integer getClosestCardFromHand(IBaseGameState state) {
        Card[] closestFromHand = state.getPlayerBookedCards(playerId);
        return closestFromHand != null ? 0 : null;
    }

    private Pair<CardLevel, Integer> getCardFromTable(IBaseGameState state) {
        CardLevel cheapestCardLvl = null;
        Integer cheapestCardIndex = null;
        Integer cheapestCardPrice = Integer.MAX_VALUE;

        Card[] lvlOne = state.getTableCards(CardLevel.ONE);
        Card[] lvlTwo = state.getTableCards(CardLevel.TWO);
        Card[] lvlThree = state.getTableCards(CardLevel.THREE);

        for (int i = 0; i < lvlOne.length; i++) {
            if (lvlOne[i] != null) {
                int cardPrice = getCoinsCount(getCoinsGap(state, lvlOne[i]));

                if (cardPrice < cheapestCardPrice) {
                    cheapestCardLvl = CardLevel.ONE;
                    cheapestCardIndex = i;
                    cheapestCardPrice = cardPrice;
                }
            }
        }

        for (int i = 0; i < lvlTwo.length; i++) {
            if (lvlTwo[i] != null) {
                int cardPrice = getCoinsCount(getCoinsGap(state, lvlTwo[i]));

                if (cardPrice < cheapestCardPrice) {
                    cheapestCardLvl = CardLevel.TWO;
                    cheapestCardIndex = i;
                    cheapestCardPrice = cardPrice;
                }
            }
        }

        for (int i = 0; i < lvlThree.length; i++) {
            if (lvlThree[i] != null) {
                int cardPrice = getCoinsCount(getCoinsGap(state, lvlThree[i]));

                if (cardPrice < cheapestCardPrice) {
                    cheapestCardLvl = CardLevel.THREE;
                    cheapestCardIndex = i;
                    cheapestCardPrice = cardPrice;
                }
            }
        }

        return new Pair<CardLevel, Integer>(cheapestCardLvl, cheapestCardIndex);
    }

    private Integer getClosestReachableCardFromHand(IBaseGameState state) {
        Integer handClosestIndex = null;
        Integer handCheapestCardGap = Integer.MAX_VALUE;

        Integer[] reachableFromHand = getReachableCardsFromHand(state);
        for (Integer index : reachableFromHand) {
            CoinSet coinsGap = state.canBuyCardFromHand(playerId, index);
            int gap = getCoinsCount(coinsGap);
            if (gap < handCheapestCardGap) {
                handClosestIndex = index;
                handCheapestCardGap = gap;
            }
        }

        return handClosestIndex;
    }
}
