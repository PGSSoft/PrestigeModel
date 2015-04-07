package com.pgssoft.prestige.model.game;

import com.pgssoft.prestige.model.enums.CardLevel;
import com.pgssoft.prestige.model.enums.Player;

import java.util.List;

/**
 * Created by Tomek on 2014-11-15.
 */

/**
 * State of game as seen by player
 */
public interface IBaseGameState {

    /**
     * Get all cards in game
     * @return all cards in game
     */
    Card[] getAllCards();

    /**
     * Returns 4-element array with current table cards of given level
     * @param level
     * @return 4-element array, null entry is an empty place
     */
    Card[] getTableCards(CardLevel level);

    /**
     * Get number of cards left on given level (only hidden, excludes cards on table)
     * @param level
     * @return number of cards left on given level
     */
    int getCardsLeftNo(CardLevel level);

    /**
     * Get cards bought by player
     * @param player
     * @return list of cards owned by player
     */
    List<Card> getPlayerTableCards(Player player);

    /**
     * Get cards reserved by player
     * @param player
     * @return three element array of cards booked by player, null entry is an empty place
     */
    Card[] getPlayerBookedCards(Player player);

    /**
     * Get coins owned by player
     * @param player
     * @return coin set of given player
     */
    CoinSet getPlayerCoins(Player player);

    /**
     * Get coins from table
     * @return
     */
    CoinSet getTableCoins();

    /**
     * Get all visitor cards in game
     * @return all visitor cards in game
     */
    VisitorCard[] getAllVisitors();

    /**
     * Get visitors claimed by player
     * @param player
     * @return list of claimed visitors
     */
    List<VisitorCard> getPlayerVisitors(Player player);

    /**
     * List of unclaimed visitors
     * @return list of visitors on table
     */
    List<VisitorCard> getAvailableVisitors();

    /**
     * List of all visitors used in game
     * @return list of used visitors
     */
    List<VisitorCard> getTableVisitors();


    /**
     * Get list of winners
     * @return list of winners
     */
    List<Player> getWinners();

    /**
     * get number of players in game
     * @return number of players in game
     */
    int getPlayersNum();

    /**
     * Get prestige for given player
     * @param player
     * @return prestige for given player
     */
    int getPrestige(Player player);

    /**
     * Checks if player can buy given card from table.
     * @param player
     * @param cardLevel Level of a card
     * @param tableIndex Table position of a card
     * @return CoinSet needed to buy card or null if card cannot be bought
     */
    CoinSet canBuyCardFromTable(Player player, CardLevel cardLevel, int tableIndex);

    /**
     * Checks if player can buy given booked card
     * @param player
     * @param handIndex Hand position of a card
     * @return CoinSet needed to buy card or null if card cannot be bought
     */
    CoinSet canBuyCardFromHand(Player player, int handIndex);

    /**
     * Returns all bonuses of a given player
     * @param player
     * @return A CoinBonusSet made of all given player's bonuses
     */
    CoinBonusSet checkBonusesForPlayer(Player player);
}
