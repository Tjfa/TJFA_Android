package me.qiufeng.www.LogicalLayer.DataModule.DataManager;

import com.j256.ormlite.dao.RuntimeExceptionDao;

import me.qiufeng.www.LogicalLayer.DataModule.LocalModule.Competition;

/**
 * Created by QiuFeng on 2/3/15.
 */
public class CompetitionManager {

    private RuntimeExceptionDao<Competition, Integer> competitionDao = null;
    private DataAccess dataAccess = null;

    static private CompetitionManager competitionManager = null;

    static public CompetitionManager sharedCompetitionManager() {
        if (competitionManager == null) {
            competitionManager = new CompetitionManager();
        }
        return competitionManager;
    }

    private CompetitionManager() {
        dataAccess = new DataAccess();
        competitionDao = dataAccess.getDatabaseHelper().getCompetitionRuntimeDao();
    }

}
