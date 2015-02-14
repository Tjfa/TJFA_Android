package me.qiufeng.www.LogicalLayer.DataModule.DataManager;

/**
 * Created by QiuFeng on 2/13/15.
 */
public class DatabaseManager {
    static DatabaseManager databaseManager  = null;

    static public DatabaseManager sharedDatabaseManager() {
        if (databaseManager == null) {
            databaseManager = new DatabaseManager();
        }
        return databaseManager;
    }

    private DatabaseManager() {

    }

    public void deleteAll() {
        MatchManager.sharedMatchManager().deleteAll();
        PlayerManager.sharedPlayerManager().deleteAll();
        TeamManager.sharedTeamManager().deleteAll();
        NewsManager.sharedNewsManager().deleteAll();
        CompetitionManager.sharedCompetitionManager().deleteAll();
    }

}
