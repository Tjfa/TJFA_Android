package me.qiufeng.www.LogicalLayer.DataModule.DataManager;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;

/**
 * Created by QiuFeng on 2/3/15.
 */
public class DataAccess {

    static private Context context;
    static private DatabaseHelper databaseHelper;


    static public DatabaseHelper sharedDatabaseHelper() {
        if (databaseHelper == null) {
            databaseHelper =  OpenHelperManager.getHelper(context, DatabaseHelper.class);
        }
        return databaseHelper;
    }

}
