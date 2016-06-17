package com.github.ArnorDorian.cp.gameplay;

import net.minecraft.entity.player.EntityPlayer;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by arnor on 2016/6/17 0017.
 */
public class GamePlayerList extends ArrayList<GamePlayer> {

    public GamePlayerList getFrozenList(boolean isFrozen) {
        GamePlayerList freezeList = new GamePlayerList();
        freezeList.clear();

        for (GamePlayer gamePlayer : this) {
            if (isFrozen == gamePlayer.isFrozen()) {
                freezeList.add(gamePlayer);
            }
        }

        return freezeList;
    }

    public GamePlayerList getOnlineList(boolean isOnline) {
        GamePlayerList onlineList = new GamePlayerList();
        onlineList.clear();

        for (GamePlayer gamePlayer : this) {
            EntityPlayer player = gamePlayer.getPlayer();
            if ((player.getServer().getPlayerList().getPlayerByUsername(player.getName()) != null) == isOnline) {
                onlineList.add(gamePlayer);
            }
        }

        return onlineList;
    }

    public GamePlayer getRandomOne() {
        GamePlayer randomOne = null;
        if (!this.isEmpty()) {
            randomOne = this.get(new Random().nextInt(this.size()));
        }

        return randomOne;
    }

    public GamePlayer getGamePlayer(EntityPlayer player) {
        for (GamePlayer gamePlayer : this) {
            if (gamePlayer.getPlayer() == player) {
                return gamePlayer;
            }
        }

        return null;
    }

}
