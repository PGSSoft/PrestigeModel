package com.pgssoft.prestige.model.game;

import com.pgssoft.prestige.model.enums.MoveType;
import com.pgssoft.prestige.model.enums.Player;

import java.util.List;

/**
 * Created by bstokrocki on 2014-11-20.
 */
public class DebugUtils {
    private static CoinBonusSet getPlayerCoinBonusSet(IBaseGameState state, Player player) {
        CoinSet playerCoins = state.getPlayerCoins(player);

        int redBonuses = 0;
        int greenBonuses = 0;
        int blueBonuses = 0;
        int blackBonuses = 0;
        int whiteBonuses = 0;

        List<Card> playerCards = state.getPlayerTableCards(player);
        for (Card card : playerCards) {
            switch (card.getBonus()) {
                case RED:
                    redBonuses++;
                    break;
                case GREEN:
                    greenBonuses++;
                    break;
                case BLUE:
                    blueBonuses++;
                    break;
                case BLACK:
                    blackBonuses++;
                    break;
                case WHITE:
                    whiteBonuses++;
                    break;
            }
        }

        return new CoinBonusSet(redBonuses, greenBonuses, blueBonuses, blackBonuses,
                whiteBonuses, playerCoins.getRedCoins(), playerCoins.getGreenCoins(),
                playerCoins.getBlueCoins(), playerCoins.getBlackCoins(), playerCoins.getWhiteCoins(),
                playerCoins.getJokers());
    }

    public static String getDebugTurnStateString(IBaseGameState state, Player player, MoveType moveType, Card boughtCard) {
        CoinBonusSet pcbs = getPlayerCoinBonusSet(state, player);
        CoinSet tableCoins = state.getTableCoins();
        String result = String.format("Player: %s, Prestige: %d, Action: %s, Coins: R: %d, G: %d, BL: %d, BK: %d, W: %d, J: %d\n" +
                        "Player bonuses: R: %d, G: %d, BL: %d, BK: %d, W: %d\n" +
                        "Table: Coins: R: %d, G: %d, BL: %d, BK: %d, W: %d, J: %d\n",
                player.toString(), state.getPrestige(player), moveType.toString(), pcbs.getRedCoins(), pcbs.getGreenCoins(),
                pcbs.getBlueCoins(), pcbs.getBlackCoins(), pcbs.getWhiteCoins(),
                pcbs.getJokers(), pcbs.getRedBonuses(), pcbs.getGreenBonuses(), pcbs.getBlueBonuses(),
                pcbs.getBlackBonuses(), pcbs.getWhiteBonuses(), tableCoins.getRedCoins(),
                tableCoins.getGreenCoins(), tableCoins.getBlueCoins(), tableCoins.getBlackCoins(),
                tableCoins.getWhiteCoins(), tableCoins.getJokers()
        );

        if (boughtCard != null) {
            result += String.format("Card cost: R: %d, G: %d, BL: %d, BK: %d, W: %d\n",
                    boughtCard.getRedPrice(), boughtCard.getGreenPrice(), boughtCard.getBluePrice(),
                    boughtCard.getBlackPrice(), boughtCard.getWhitePrice());
        }

        return result;
    }
}
