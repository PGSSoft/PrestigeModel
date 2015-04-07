package com.pgssoft.prestige.arena;

import com.pgssoft.prestige.arena.bot.Bot;
import com.pgssoft.prestige.model.enums.CardLevel;
import com.pgssoft.prestige.model.enums.Player;
import com.pgssoft.prestige.model.exceptions.InvalidMoveException;
import com.pgssoft.prestige.model.game.BaseGameState;
import com.pgssoft.prestige.model.game.Card;
import com.pgssoft.prestige.model.game.DebugUtils;
import com.pgssoft.prestige.model.game.Move;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Arena {

    private static final boolean DEBUG = false;
    private static final boolean TRACK_MATCH = true;
    private static final String FILENAME = "output.txt";
    public static Random RANDOM = new Random();

    Bot[] players;
    int lastPlayerIndex;
    BaseGameState gameState;
    int turnToken = 0;
    int turnsTaken = 0;
    int firstLvl2CardBoughtTurn = -1;

    public static void testGame(Bot[] players, int gamesCount) throws FileNotFoundException, UnsupportedEncodingException {
        HashMap<Player, Integer> wins = new HashMap<Player, Integer>();

        for (Bot bot : players) {
            wins.put(bot.getPlayerId(), 0);
        }

        PrintWriter writer = new PrintWriter(FILENAME, "UTF-8");

        long timeStart = System.currentTimeMillis();
        for (int i = 0; i < gamesCount; i++) {
            Arena arena = new Arena(players);
            List<Player> winners = arena.start();
            writer.println(arena.getMatchLog());
            for (Player winner : winners) {
                Integer winsCount = wins.get(winner);
                wins.put(winner, winsCount + 1);
            }
        }

        writer.close();
        System.out.println("==== Game over ====");
        for (Player p : wins.keySet()) {
            System.out.printf("%s wins: %d\n", p.toString(), wins.get(p));
        }
        System.out.printf("Time: %d ms", (System.currentTimeMillis() - timeStart));
    }

    public Arena(Bot[] players) {
        this.players = players;
        lastPlayerIndex = players.length - 1;
        gameState = new BaseGameState(players.length);
    }

    public List<Player> start() {
        while (true) {
            if (DEBUG && turnToken == 0) {
                System.out.println("");
                System.out.println("==========================================================");
                System.out.println("");
            }

            Move move = players[turnToken].makeMove(gameState);
            if (move != null) {
                switch (move.getMoveType()) {
                    case TAKE_COINS:
                        gameState.makeMove(move);
                        /*
                        gameState.makeMoveGetCoins(move.getPlayer(), move.getGetRed(), move.getGetGreen(), move.getGetBlue(),
                                move.getGetBlack(), move.getGetWhite(), move.getRemoveRed(), move.getRemoveGreen(), move.getRemoveBlue(),
                                move.getRemoveBlack(), move.getRemoveWhite(), move.getRemoveJokers());
                        */
                        if (DEBUG) {
                            System.out.println(DebugUtils.getDebugTurnStateString(gameState, players[turnToken].getPlayerId(), move.getMoveType(), null));
                        }
                        break;

                    case BUY_CARD_FROM_TABLE:
                        Card tableCard = gameState.getTableCards(move.getCardLevel())[move.getTableOrHandIndex()];
                        if (move.getCardLevel() == CardLevel.TWO && firstLvl2CardBoughtTurn == -1) {
                            firstLvl2CardBoughtTurn = turnsTaken;
                        }/*
                        gameState.makeMoveBuyCardFromTable(move.getPlayer(), move.getCardLevel(), move.getTableOrHandIndex());
                        */
                        gameState.makeMove(move);
                        if (DEBUG) {
                            System.out.println(DebugUtils.getDebugTurnStateString(gameState, players[turnToken].getPlayerId(), move.getMoveType(), tableCard));
                        }
                        break;

                    case BUY_CARD_FROM_HAND:
                        Card handCard = gameState.getPlayerBookedCards(players[turnToken].getPlayerId())[move.getTableOrHandIndex()];
                        if (move.getCardLevel() == CardLevel.TWO && firstLvl2CardBoughtTurn == -1) {
                            firstLvl2CardBoughtTurn = turnsTaken;
                        }
                        //gameState.makeMoveBuyCardFromHand(move.getPlayer(), move.getTableOrHandIndex());
                        gameState.makeMove(move);
                        if (DEBUG) {
                            System.out.println(DebugUtils.getDebugTurnStateString(gameState, players[turnToken].getPlayerId(), move.getMoveType(), handCard));
                        }
                        break;

                    case BOOK_CARD_FROM_TABLE:
                        /*
                        gameState.makeMoveBookCardFromTable(move.getPlayer(), move.getCardLevel(), move.getTableOrHandIndex(),
                                move.getRemoveRed(), move.getRemoveGreen(), move.getRemoveBlue(), move.getRemoveBlack(),
                                move.getRemoveWhite(), move.getRemoveJokers());
                        */
                        gameState.makeMove(move);
                        if (DEBUG) {
                            System.out.println(DebugUtils.getDebugTurnStateString(gameState, players[turnToken].getPlayerId(), move.getMoveType(), null));
                        }
                        break;
                }
            } else {
                throw new InvalidMoveException("No move specified");
            }

            if (turnToken == lastPlayerIndex) {
                List<Player> winners = gameState.getWinners();
                if (winners != null) {
                    return winners;
                }
            }

            takeTurn();
        }
    }

    public String getMatchLog() {
        return String.format("%d;%d;%d;%d", turnsTaken, firstLvl2CardBoughtTurn,
                gameState.getPlayerTableCards(players[0].getPlayerId()).size(),
                gameState.getPlayerTableCards(players[1].getPlayerId()).size()
        );
    }

    private void takeTurn() {
        if (turnToken + 1 < players.length) {
            turnToken++;
        } else {
            turnToken = 0;
            turnsTaken++;
        }
    }
}
