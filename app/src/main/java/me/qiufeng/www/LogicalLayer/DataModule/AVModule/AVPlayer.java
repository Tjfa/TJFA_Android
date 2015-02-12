package me.qiufeng.www.LogicalLayer.DataModule.AVModule;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

/**
 * Created by QiuFeng on 2/12/15.
 */

@AVClassName("Player")
public class AVPlayer extends AVObject {
    public int getPlayerId() {
        return getInt("playerId");
    }

    public void setPlayerId(int playerId) {
        put("playerId",playerId);
    }

    public int getGoalCount() {
        return getInt("goalCount");
    }

    public void setGoalCount(int goalCount) {
        put("goalCount",goalCount);
    }

    public String getName() {
        return getString("name");
    }

    public void setName(String name) {
        put("name",name);
    }

    public int getRedCard() {
        return getInt("redCard");
    }

    public void setRedCard(int redCard) {
        put("redCard",redCard);
    }

    public int getYellowCard() {
        return getInt("yellowCard");
    }

    public void setYellowCard(int yellowCard) {
        put("yellowCard",yellowCard);
    }

    public int getTeamId() {
        return getInt("teamId");
    }

    public void setTeamId(int teamId) {
        put("teamId",teamId);
    }

    public int getCompetitionId() {
        return getInt("competitionId");
    }

    public void setCompetitionId(int competitionId) {
        put("competitionId",competitionId);
    }

}
