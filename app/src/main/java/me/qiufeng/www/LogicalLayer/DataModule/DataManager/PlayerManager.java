package me.qiufeng.www.LogicalLayer.DataModule.DataManager;

import com.j256.ormlite.dao.RuntimeExceptionDao;

import me.qiufeng.www.AppDelegate.AppDelegate;
import me.qiufeng.www.LogicalLayer.DataModule.LocalModule.Player;

/**
 * Created by QiuFeng on 2/13/15.
 */
public class PlayerManager {
    static PlayerManager playerManager = null;
    private RuntimeExceptionDao<Player, Integer> playerDao;

    static PlayerManager sharedPlayerManager() {
        if (playerManager == null) {
            playerManager = new PlayerManager();
        }
        return playerManager;
    }

    private PlayerManager() {
        playerDao = DatabaseHelper.getHelper(AppDelegate.getAppContext()).getPlayerRuntimeDao();
    }
}
