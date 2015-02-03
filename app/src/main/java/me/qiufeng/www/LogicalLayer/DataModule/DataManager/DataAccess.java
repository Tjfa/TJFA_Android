package me.qiufeng.www.LogicalLayer.DataModule.DataManager;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import me.qiufeng.www.LogicalLayer.DataModule.DataManager.DatabaseHelper;

/**
 * Created by QiuFeng on 2/3/15.
 */
public class DataAccess {

    private Context context;
    private DatabaseHelper databaseHelper;

    public DatabaseHelper getDatabaseHelper() {
        if (databaseHelper == null) {
            databaseHelper =  OpenHelperManager.getHelper(context, DatabaseHelper.class);
        }
        return databaseHelper;
    }

}
