package me.qiufeng.www.LogicalLayer.DataModule.DataManager;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.List;

import me.qiufeng.www.AppDelegate.AppDelegate;
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
        teamDao = DatabaseHelper.getHelper(AppDelegate.getAppContext()).getTeamRuntimeDao();
    }

    public void deleteAll() {
        List<Team> list = teamDao.queryForAll();
        teamDao.delete(list);
    }
}
