package me.qiufeng.www.LogicalLayer.DataModule.LocalModule;

import com.j256.ormlite.field.DatabaseField;

import java.util.Date;

/**
 * Created by QiuFeng on 2015/1/10.
 */
public class Match {
    @DatabaseField(id = true)
    private int matchId;

    @DatabaseField()
    private int isStart;

    @DatabaseField()
    private int matchProperty;

    @DatabaseField()
    private int scoreA;

    @DatabaseField()
    private int scroeB;

    @DatabaseField()
    private int penaltyA;

    @DatabaseField()
    private int penaltyB;

    @DatabaseField()
    private int competitionId;

    @DatabaseField()
    private int teamAId;

    @DatabaseField()
    private int teamBId;

    @DatabaseField()
    private String hint;

    @DatabaseField()
    private Date date;

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getIsStart() {
        return isStart;
    }

    public void setIsStart(int isStart) {
        this.isStart = isStart;
    }

    public int getMatchProperty() {
        return matchProperty;
    }

    public void setMatchProperty(int matchProperty) {
        this.matchProperty = matchProperty;
    }

    public int getScoreA() {
        return scoreA;
    }

    public void setScoreA(int scoreA) {
        this.scoreA = scoreA;
    }

    public int getScroeB() {
        return scroeB;
    }

    public void setScroeB(int scroeB) {
        this.scroeB = scroeB;
    }

    public int getPenaltyA() {
        return penaltyA;
    }

    public void setPenaltyA(int penaltyA) {
        this.penaltyA = penaltyA;
    }

    public int getPenaltyB() {
        return penaltyB;
    }

    public void setPenaltyB(int penaltyB) {
        this.penaltyB = penaltyB;
    }

    public int getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(int competitionId) {
        this.competitionId = competitionId;
    }

    public int getTeamAId() {
        return teamAId;
    }

    public void setTeamAId(int teamAId) {
        this.teamAId = teamAId;
    }

    public int getTeamBId() {
        return teamBId;
    }

    public void setTeamBId(int teamBId) {
        this.teamBId = teamBId;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
