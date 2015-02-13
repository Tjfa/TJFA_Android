package me.qiufeng.www.LogicalLayer.DataModule.DataManager;

import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.List;

import me.qiufeng.www.AppDelegate.AppDelegate;
import me.qiufeng.www.LogicalLayer.DataModule.LocalModule.Match;

/**
 * Created by QiuFeng on 2/13/15.
 */
public class MatchManager {
    private RuntimeExceptionDao<Match,Integer> matchDao = null;
    static public MatchManager sharedMatchManager = null;

    static public MatchManager sharedMatchManager() {
        if (sharedMatchManager == null) {
            sharedMatchManager = new MatchManager();
        }
        return sharedMatchManager;
    }

    private MatchManager() {
        matchDao = DatabaseHelper.getHelper(AppDelegate.getAppContext()).getMatchRuntimeDao();
    }

    public void deleteAll() {
        List<Match> list = matchDao.queryForAll();
        matchDao.delete(list);
    }
}
