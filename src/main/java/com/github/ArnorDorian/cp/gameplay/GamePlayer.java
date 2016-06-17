package com.github.ArnorDorian.cp.gameplay;

import com.github.ArnorDorian.cp.heroes.Hero;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.text.TextFormatting;

import java.util.UUID;

/**
 * Created by arnor on 2016/6/17 0017.
 */
public class GamePlayer {

    private Team team;
    private EntityPlayer player;
    private boolean isFrozen = false;
    private boolean isInGame = true;
    private int fireCount = 2;
    private int CPtimeMax = 90;
    private int saveTime = 0;
    private int spawnTime = 0;
    private int saveTimeMax = 60;
    private int spawnTimeMax = 15;
    private Hero hero;
    private float CPHealthLine = 8.0F;
    private UUID Uid;

    public static GamePlayerList playerList = new GamePlayerList();
    public static GamePlayerList heroList = new GamePlayerList();
    public static GamePlayerList redTeamList = new GamePlayerList();
    public static GamePlayerList blueTeamList = new GamePlayerList();

    public GamePlayer(EntityPlayer player) {
        this.player = player;
        this.Uid = this.player.getUniqueID();
        playerList.add(this);

        if (player.getTeam().getChatFormat() == TextFormatting.RED) {
            redTeamList.add(this);
        }
        if (player.getTeam().getChatFormat() == TextFormatting.BLUE) {
            blueTeamList.add(this);
        }
    }

    public EntityPlayer getPlayer() {
        return this.player;
    }

    public GamePlayer getGamePlayerFromPlayer(EntityPlayer player) {
        if (this.player == player) {
            return this;
        }
        return null;
    }

    public void setPlayer(EntityPlayer player) {
        this.player = player;
    }

    public boolean isFrozen() {
        return this.isFrozen;
    }

    public void setFrozen(boolean isFrozen) {
        this.isFrozen = isFrozen;
    }

    public int getFireCount() {
        return this.fireCount;
    }

    public void setFireCount(int count) {
        this.fireCount = count;
    }

    public int getCPtimeMax() {
        return CPtimeMax;
    }

    public void setCPtimeMax(int time) {
        this.CPtimeMax = time;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public int getSavetime() {
        return saveTime;
    }

    public int getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(int time) {
        this.saveTime = time;
    }

    public boolean isInGame() {
        return isInGame;
    }

    public void setInGame(boolean isTnGame) {
        isInGame = isTnGame;
    }

    public int getSaveTimeMax() {
        return saveTimeMax;
    }

    public void setSaveTimeMax(int saveTimeMax) {
        this.saveTimeMax = saveTimeMax;
    }

    public float getCPHealthLine() {
        return CPHealthLine;
    }

    public void setCPHealthLine(float healthLine) {
        this.CPHealthLine = healthLine;
    }

    public int getSpawnTime() {
        return spawnTime;
    }

    public void setSpawnTime(int time) {
        this.spawnTime = time;
    }

    public int getSpawnTimeMax() {
        return spawnTimeMax;
    }

    public void setSpawnTimeMax(int time) {
        this.spawnTimeMax = time;
    }

    public UUID getUid() {
        return Uid;
    }

    public void setUid(UUID uid) {
        Uid = uid;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

}
