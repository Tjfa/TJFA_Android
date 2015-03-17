package me.qiufeng.www.LogicalLayer.DataModule.AVModule;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

import java.util.Date;

/**
 * Created by QiuFeng on 2015/1/3.
 */

@AVClassName("Match")
public class AVMatch extends AVObject {
    public int getMatchId() {
        return getInt("matchId");
    }

    public void setMatchId(int matchId) {
        put("matchId",matchId);
    }

    public int getIsStart() {
        return getInt("isStart");
    }

    public void setIsStart(int isStart) {
        put("isStart",isStart);
    }

    public int getMatchProperty() {
        return getInt("matchProperty");
    }

    public void setMatchProperty(int matchProperty) {
        put("matchProperty",matchProperty);
    }

    public int getScoreA() {
        return getInt("scoreA");
    }

    public void setScoreA(int scoreA) {
        put("scoreA",scoreA);
    }

    public int getScoreB() {
        return getInt("scoreB");
    }

    public void setScoreB(int scroeB) {
        put("scoreB",scroeB);
    }

    public int getPenaltyA() {
        return getInt("penaltyA");
    }

    public void setPenaltyA(int penaltyA) {
        put("penaltyA",penaltyA);
    }

    public int getPenaltyB() {
        return getInt("penaltyB");
    }

    public void setPenaltyB(int penaltyB) {
        put("penaltyB",penaltyB);
    }

    public int getCompetitionId() {
        return getInt("competitionId");
    }

    public void setCompetitionId(int competitionId) {
        put("competitionId",competitionId);
    }

    public int getTeamAId() {
        return getInt("teamAId");
    }

    public void setTeamAId(int teamAId) {
        put("teamAId",teamAId);
    }

    public int getTeamBId() {
        return getInt("teamBId");
    }

    public void setTeamBId(int teamBId) {
        put("teamBId",teamBId);
    }

    public String getHint() {
        if (getString("hint") == null) {
            return "";
        }
        return getString("hint");
    }

    public void setHint(String hint) {
        if (hint == null) {
            hint = "";
        }
        put("hint",hint);
    }

    public Date getDate() {
        return getDate("date");
    }

    public void setDate(Date date) {
        put("date",date);
    }
}
