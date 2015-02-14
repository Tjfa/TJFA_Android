package me.qiufeng.www.LogicalLayer.DataModule.DataManager;

import me.qiufeng.www.AppDelegate.AppDelegate;

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

    public void clearAllTable() {
        DatabaseHelper.getHelper().clearAllTable();
    }

}
