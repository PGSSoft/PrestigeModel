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
 * Created by bstokrocki on 2014-11-18.
 */
public class RandomBot extends Bot {

    public RandomBot(Player player) {
        super(player);
    }

    @Override
    public String getName() {
        return "James Bot";
    }

    @Override
    public Move makeMove(IBaseGameState state) {
        Integer[] reachableFromHand = getReachableCardsFromHand(state);
        if (reachableFromHand.length > 0) {
            return buyCardFromHand(state, reachableFromHand[0]);
        }

        Integer[] reachableLvl1 = getReachableCardsFromTable(state, CardLevel.THREE);
        if (reachableLvl1.length > 0) {
            return buyCardFromTable(state, CardLevel.THREE, reachableLvl1[0]);
        }

        Integer[] reachableLvl2 = getReachableCardsFromTable(state, CardLevel.TWO);
        if (reachableLvl2.length > 0) {
            return buyCardFromTable(state, CardLevel.TWO, reachableLvl2[0]);
        }

        Integer[] reachableLvl3 = getReachableCardsFromTable(state, CardLevel.ONE);
        if (reachableLvl3.length > 0) {
            return buyCardFromTable(state, CardLevel.ONE, reachableLvl3[0]);
        }

        CoinSet coinsToTake = selectCoinsToTake(state);
        if (coinsToTake != null) {
            return takeCoins(state);
        } else {
            return bookCardFromTable(state);
        }
    }

    protected CoinSet selectCoinsToTake(IBaseGameState state) {
        CoinSet tableCoins = state.getTableCoins();
        ArrayList<Integer> nonEmptyColors = getNonEmptyCoinsIndices(tableCoins);

        CoinSet coinSet = null;
        if (nonEmptyColors.size() >= 3) {
            Integer[] pickedCoins = new Integer[3];
            for (int i = 0; i < 3; i++) {
                pickedCoins[i] = nonEmptyColors.remove(Arena.RANDOM.nextInt(nonEmptyColors.size()));
            }
            coinSet = updateCoinsArrayToSet(pickedCoins, 1);
        } else if (nonEmptyColors.size() == 2) {
            if (getCoinCountByIndex(tableCoins, nonEmptyColors.get(0)) > 3) {
                coinSet = updateCoinsArrayToSet(new Integer[]{nonEmptyColors.get(0)}, 2);
            } else if (getCoinCountByIndex(tableCoins, nonEmptyColors.get(1)) > 3) {
                coinSet = updateCoinsArrayToSet(new Integer[]{nonEmptyColors.get(1)}, 2);
            } else {
                coinSet = updateCoinsArrayToSet(new Integer[]{nonEmptyColors.get(0), nonEmptyColors.get(1)}, 1);
            }
        } else if (nonEmptyColors.size() == 1) {
            coinSet = updateCoinsArrayToSet(new Integer[]{nonEmptyColors.get(0)}, 1);
        }

        return coinSet;
    }

    protected CoinSet selectCoinsToGiveBack(IBaseGameState state, CoinSet coinsToTake) {
        CoinSet playersCoins = state.getPlayerCoins(playerId);

        playersCoins.setRedCoins(playersCoins.getRedCoins() + coinsToTake.getRedCoins());
        playersCoins.setGreenCoins(playersCoins.getGreenCoins() + coinsToTake.getGreenCoins());
        playersCoins.setBlueCoins(playersCoins.getBlueCoins() + coinsToTake.getBlueCoins());
        playersCoins.setWhiteCoins(playersCoins.getWhiteCoins() + coinsToTake.getWhiteCoins());
        playersCoins.setBlackCoins(playersCoins.getBlackCoins() + coinsToTake.getBlackCoins());
        playersCoins.setJokers(playersCoins.getJokers() + coinsToTake.getJokers());

        int coinsToRemoveCount = getCoinsCount(playersCoins) - 10;
        ArrayList<Integer> nonEmptyColors = new ArrayList<Integer>();

        if (playersCoins.getRedCoins() > 0) {
            nonEmptyColors.add(0);
        }
        if (playersCoins.getGreenCoins() > 0) {
            nonEmptyColors.add(1);
        }
        if (playersCoins.getBlueCoins() > 0) {
            nonEmptyColors.add(2);
        }
        if (playersCoins.getBlackCoins() > 0) {
            nonEmptyColors.add(3);
        }
        if (playersCoins.getWhiteCoins() > 0) {
            nonEmptyColors.add(4);
        }

        CoinSet coinsToRemove = new CoinSet();
        while (coinsToRemoveCount > 0) {
            int removeIndex = Arena.RANDOM.nextInt(nonEmptyColors.size());
            int colorToRemove = nonEmptyColors.get(removeIndex);

            switch (colorToRemove) {
                case 0:
                    coinsToRemove.setRedCoins(coinsToRemove.getRedCoins() + 1);
                    if (playersCoins.getRedCoins() - 1 <= 0) {
                        nonEmptyColors.remove(removeIndex);
                    } else {
                        playersCoins.setRedCoins(playersCoins.getRedCoins() - 1);
                    }

                    break;

                case 1:
                    coinsToRemove.setGreenCoins(coinsToRemove.getGreenCoins() + 1);
                    if (playersCoins.getGreenCoins() - 1 <= 0) {
                        nonEmptyColors.remove(removeIndex);
                    } else {
                        playersCoins.setGreenCoins(playersCoins.getGreenCoins() - 1);
                    }

                    break;

                case 2:
                    coinsToRemove.setBlueCoins(coinsToRemove.getBlueCoins() + 1);
                    if (playersCoins.getBlueCoins() - 1 <= 0) {
                        nonEmptyColors.remove(removeIndex);
                    } else {
                        playersCoins.setBlueCoins(playersCoins.getBlueCoins() - 1);
                    }

                    break;

                case 3:
                    coinsToRemove.setBlackCoins(coinsToRemove.getBlackCoins() + 1);
                    if (playersCoins.getBlackCoins() - 1 <= 0) {
                        nonEmptyColors.remove(removeIndex);
                    } else {
                        playersCoins.setBlackCoins(playersCoins.getBlackCoins() - 1);
                    }

                    break;

                case 4:
                    coinsToRemove.setWhiteCoins(coinsToRemove.getWhiteCoins() + 1);
                    if (playersCoins.getWhiteCoins() - 1 <= 0) {
                        nonEmptyColors.remove(removeIndex);
                    } else {
                        playersCoins.setWhiteCoins(playersCoins.getWhiteCoins() - 1);
                    }

                    break;
            }

            coinsToRemoveCount--;
        }

        return coinsToRemove;
    }

    private Pair<CardLevel, Integer> getCardFromTable(IBaseGameState state) {
        CardLevel cheapestCardLvl = null;
        Integer cheapestCardIndex = null;
        Integer cheapestCardPrice = Integer.MAX_VALUE;

        Card[] lvlOne = state.getTableCards(CardLevel.ONE);
        for (int i = 0; i < lvlOne.length; i++) {
            if (lvlOne[i] != null) {
                int cardPrice = getCardCoinsCount(lvlOne[i]);
                if (cardPrice < cheapestCardPrice) {
                    cheapestCardLvl = CardLevel.ONE;
                    cheapestCardIndex = i;
                    cheapestCardPrice = cardPrice;
                }
            }
        }

        if (cheapestCardPrice == Integer.MAX_VALUE) {
            Card[] lvlTwo = state.getTableCards(CardLevel.TWO);
            for (int i = 0; i < lvlTwo.length; i++) {
                if (lvlTwo[i] != null) {
                    int cardPrice = getCardCoinsCount(lvlTwo[i]);
                    if (cardPrice < cheapestCardPrice) {
                        cheapestCardLvl = CardLevel.TWO;
                        cheapestCardIndex = i;
                        cheapestCardPrice = cardPrice;
                    }
                }
            }
        }

        if (cheapestCardPrice == Integer.MAX_VALUE) {
            Card[] lvlThree = state.getTableCards(CardLevel.THREE);
            for (int i = 0; i < lvlThree.length; i++) {
                if (lvlThree[i] != null) {
                    int cardPrice = getCardCoinsCount(lvlThree[i]);
                    if (cardPrice < cheapestCardPrice) {
                        cheapestCardLvl = CardLevel.THREE;
                        cheapestCardIndex = i;
                        cheapestCardPrice = cardPrice;
                    }
                }
            }
        }

        return new Pair<CardLevel, Integer>(cheapestCardLvl, cheapestCardIndex);
    }

    @Override
    protected Pair<CardLevel, Integer> selectCardToBook(IBaseGameState state) {
        return getCardFromTable(state);
    }
}
