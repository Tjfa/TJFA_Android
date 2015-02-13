package me.qiufeng.www.LogicalLayer.DataModule.DataManager;

import me.qiufeng.www.LogicalLayer.DataModule.LocalModule.Player;
import me.qiufeng.www.LogicalLayer.DataModule.LocalModule.Team;

/**
 * Created by QiuFeng on 2/13/15.
 */
public class DatabaseManager {
    static DatabaseManager databaseManager  = null;

    public DatabaseManager sharedDatabaseManager() {
        if (databaseManager == null) {
            databaseManager = new DatabaseManager();
        }
        return databaseManager;
    }

    private DatabaseManager() {

    }

    public void clearAllData() {
        MatchManager.sharedMatchManager().deleteAll();
        PlayerManager.sharedPlayerManager().deleteAll();
        TeamManager.sharedTeamManager().deleteAll();
        NewsManager.sharedNewsManager().deleteAll();
        CompetitionManager.sharedCompetitionManager().deleteAll();
    }

}
