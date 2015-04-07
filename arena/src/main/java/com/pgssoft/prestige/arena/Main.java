package com.pgssoft.prestige.arena;

import com.pgssoft.prestige.arena.bot.Bot;
import com.pgssoft.prestige.arena.bot.BuyerBot;
import com.pgssoft.prestige.arena.bot.RandomBot;
import com.pgssoft.prestige.model.enums.Player;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

/**
 * Created by bstokrocki on 2014-11-18.
 */
public class Main {
    public static void main(String[] args) {
        try {
            Arena.testGame(new Bot[]{
                    new RandomBot(Player.PLAYER0),
                    new BuyerBot(Player.PLAYER1),
                    new BuyerBot(Player.PLAYER2),
                    new RandomBot(Player.PLAYER3)
            }, 10000);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
