package me.qiufeng.www.LogicalLayer.DataModule.AVModule;

import android.util.Log;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

/**
 * Created by QiuFeng on 2/12/15.
 */

@AVClassName("Team")
public class AVTeam extends AVObject {
    public int getTeamId() {
        return getInt("teamId");
    }

    public void setTeamId(int teamId) {
        put("teamId",teamId);
    }

    public String getEmblemPath() {
        if (getString("emblemPath") == null) {
            return "";
        } else {
            return getString("emblemPath");
        }
    }

    public void setEmblemPath(String emblemPath) {
        if (emblemPath == null) {
            emblemPath = "";
        }
        put("emblemPath",emblemPath);
    }

    public int getGroupGoalCount() {
        return getInt("groupGoalCount");
    }

    public void setGroupGoalCount(int groupGoalCount) {
        put("groupGoalCount",groupGoalCount);
    }

    public int getGroupMissCount() {
        return getInt("groupMissCount");
    }

    public void setGroupMissCount(int groupMissCount) {
        put("groupMissCount",groupMissCount);
    }

    public int getGoalCount() {
        return getInt("goalCount");
    }

    public void setGoalCount(int goalCount) {
        put("goalCount",goalCount);
    }

    public int getMissCount() {
        return getInt("missCount");
    }

    public void setMissCount(int missCount) {
        put("missCount",missCount);
    }

    public String getGroupNo() {
        return getString("groupNo");
    }

    public void setGroupNo(String groupNo) {
        put("groupNo",groupNo);
    }

    public int getGroupWinCount() {
        return getInt("groupWinCount");
    }

    public void setGroupWinCount(int groupWinCount) {
        put("groupWinCount",groupWinCount);
    }

    public int getGroupDrawCount() {
        return getInt("groupDrawCount");
    }

    public void setGroupDrawCount(int groupDrawCount) {
        put("groupDrawCount",groupDrawCount);
    }

    public int getGroupLostCount() {
        return getInt("groupLostCount");
    }

    public void setGroupLostCount(int groupLostCount) {
        put("groupLostCount",groupLostCount);
    }

    public int getWinCount() {
        return getInt("winCount");
    }

    public void setWinCount(int winCount) {
        put("winCount",winCount);
    }

    public int getLostCount() {
        return getInt("lostCount");
    }

    public void setLostCount(int lostCount) {
        put("lostCount",lostCount);
    }

    public String getName() {
        return getString("name");
    }

    public void setName(String name) {
        put("name",name);
    }

    public int getScore() {
        return getInt("score");
    }

    public void setScore(int score) {
        put("score",score);
    }

    public int getRank() {
        return getInt("rank");
    }

    public void setRank(int rank) {
        put("rank",rank);
    }

    public void setCompetitionId(int competitionId) {
        put("competitionId", competitionId);
    }

    public int getCompetitionId() {
        return getInt("competitionId");
    }
}
