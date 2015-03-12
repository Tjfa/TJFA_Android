package me.qiufeng.www.LogicalLayer.DataModule.LocalModule;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by QiuFeng on 2015/1/10.
 */

@DatabaseTable(tableName = "Player")
public class Player {

    @DatabaseField(id = true)
    int playerId;

    @DatabaseField()
    int goalCount;

    @DatabaseField()
    String name;

    @DatabaseField()
    int redCard;

    @DatabaseField()
    int yellowCard;

    @DatabaseField()
    int teamId;

    @DatabaseField()
    int competitionId;

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getGoalCount() {
        return goalCount;
    }

    public void setGoalCount(int goalCount) {
        this.goalCount = goalCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRedCard() {
        return redCard;
    }

    public void setRedCard(int redCard) {
        this.redCard = redCard;
    }

    public int getYellowCard() {
        return yellowCard;
    }

    public void setYellowCard(int yellowCard) {
        this.yellowCard = yellowCard;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(int competitionId) {
        this.competitionId = competitionId;
    }

}
