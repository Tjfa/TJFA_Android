package me.qiufeng.www.LogicalLayer.DataModule.LocalModule;

import android.util.Log;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by QiuFeng on 2/12/15.
 */

@DatabaseTable(tableName = "Team")
public class Team {
    @DatabaseField(id = true)
    int teamId;

    @DatabaseField(defaultValue = "")
    String badgeImage;

    @DatabaseField()
    int groupGoalCount;

    @DatabaseField()
    int groupMissCount;

    @DatabaseField()
    int goalCount;

    @DatabaseField()
    int missCount;

    @DatabaseField()
    String groupNo;

    @DatabaseField()
    int groupWinCount;

    @DatabaseField()
    int groupDrawCount;

    @DatabaseField()
    int groupLostCount;

    @DatabaseField()
    int winCount;

    @DatabaseField()
    int lostCount;

    @DatabaseField()
    String name;

    @DatabaseField()
    int score;

    @DatabaseField()
    int rank;

    @DatabaseField()
    int competitionId;

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getBadgeImage() {
        return badgeImage;
    }

    public void setBadgeImage(String badgeImage) {
        this.badgeImage = badgeImage;
    }

    public int getGroupGoalCount() {
        return groupGoalCount;
    }

    public void setGroupGoalCount(int groupGoalCount) {
        this.groupGoalCount = groupGoalCount;
    }

    public int getGroupMissCount() {
        return groupMissCount;
    }

    public void setGroupMissCount(int groupMissCount) {
        this.groupMissCount = groupMissCount;
    }

    public int getGoalCount() {
        return goalCount;
    }

    public void setGoalCount(int goalCount) {
        this.goalCount = goalCount;
    }

    public int getMissCount() {
        return missCount;
    }

    public void setMissCount(int missCount) {
        this.missCount = missCount;
    }

    public String getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo;
    }

    public int getGroupWinCount() {
        return groupWinCount;
    }

    public void setGroupWinCount(int groupWinCount) {
        this.groupWinCount = groupWinCount;
    }

    public int getGroupDrawCount() {
        return groupDrawCount;
    }

    public void setGroupDrawCount(int groupDrawCount) {
        this.groupDrawCount = groupDrawCount;
    }

    public int getGroupLostCount() {
        return groupLostCount;
    }

    public void setGroupLostCount(int groupLostCount) {
        this.groupLostCount = groupLostCount;
    }

    public int getWinCount() {
        return winCount;
    }

    public void setWinCount(int winCount) {
        this.winCount = winCount;
    }

    public int getLostCount() {
        return lostCount;
    }

    public void setLostCount(int lostCount) {
        this.lostCount = lostCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getComeptitionId() {
        return competitionId;
    }

    public void setComeptitionId(int competitionId) {
        this.competitionId = competitionId;
    }
}
