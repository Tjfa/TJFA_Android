package me.qiufeng.www.LogicalLayer.DataModule.DataManager;

import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.ArrayList;
import java.util.List;

import me.qiufeng.www.AppDelegate.AppDelegate;
import me.qiufeng.www.LogicalLayer.DataModule.AVModule.AVTeam;
import me.qiufeng.www.LogicalLayer.DataModule.LocalModule.Team;

/**
 * Created by QiuFeng on 2/13/15.
 */
public class TeamManager {
    static TeamManager teamManager = null;
    private RuntimeExceptionDao<Team,Integer> teamDao = null;

    public static TeamManager sharedTeamManager() {
        if (teamManager == null) {
            teamManager = new TeamManager();
        }
        return teamManager;
    }

    private TeamManager() {
        teamDao = DatabaseHelper.getHelper().getTeamRuntimeDao();
    }

    public Team getTeamByTeamId(int teamId) {
        return teamDao.queryForId(teamId);
    }

    public List<Team> getTeamsFromDatabase(int competitionId) {
        return teamDao.queryForEq("competitionId", competitionId);
    }

    public String getRankString(int rank) {
        if (rank == 100) return "附加赛";
        if (rank == 0) return "小组赛";
        if (rank == 1) return "冠军";
        if (rank == 2) return "亚军";
        if (rank == 3) return "季军";
        if (rank == 4) return "四强";
        if (rank == 8) return "八强";
        if (rank == 16) return "十六强";
        if (rank == 32) return "三十二强";
        return "";
    }

    public void getTeamsFromNetwork(int competitionId, final FinishCallBack<Team> callBack) {
        AVQuery<AVTeam> teamQuery = AVQuery.getQuery(AVTeam.class);
        teamQuery.limit(1000);
        teamQuery.whereEqualTo("competitionId", competitionId);
        teamQuery.findInBackground(new FindCallback<AVTeam>() {
            @Override
            public void done(List<AVTeam> avTeams, AVException e) {

                if (e == null) {
                    ArrayList<Team> result = insertAVTeams(avTeams);
                    if (callBack != null) {
                        callBack.done(result, null);
                    }
                } else {
                    if (callBack != null) {
                        callBack.done(null, e);
                    }
                }
            }
        });
    }
    private ArrayList<Team> insertAVTeams(List<AVTeam> avTeams) {
        ArrayList<Team> teams = new ArrayList<>();
        for (AVTeam avTeam : avTeams) {
            Team team = new Team();
            team.setTeamId(avTeam.getTeamId());
            team.setBadgeImage(avTeam.getEmblemPath());
            team.setGoalCount(avTeam.getGoalCount());
            team.setMissCount(avTeam.getMissCount());
            team.setGroupGoalCount(avTeam.getGroupGoalCount());
            team.setGroupMissCount(avTeam.getGroupMissCount());
            team.setGroupWinCount(avTeam.getGroupWinCount());
            team.setGroupDrawCount(avTeam.getGroupDrawCount());
            team.setGroupLostCount(avTeam.getGroupLostCount());
            team.setWinCount(avTeam.getWinCount());
            team.setLostCount(avTeam.getLostCount());
            team.setGroupNo(avTeam.getGroupNo());
            team.setName(avTeam.getName());
            team.setScore(avTeam.getScore());
            team.setRank(avTeam.getRank());
            team.setComeptitionId(avTeam.getCompetitionId());
            teamDao.createOrUpdate(team);
            teams.add(team);
        }

        return teams;
    }
}
