package me.qiufeng.www.LogicalLayer.DataModule.DataManager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import org.w3c.dom.UserDataHandler;

import java.sql.SQLException;

import me.qiufeng.www.LogicalLayer.DataModule.LocalModule.Competition;
import me.qiufeng.www.LogicalLayer.DataModule.LocalModule.News;

/**
 * Created by QiuFeng on 2/3/15.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private DatabaseHelper databaseHelper = null;

    private static final String TAG =  "DatabaseHelper";
    private static final String DATABASE_NAME = "TJFA.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<Competition, Integer> competitionDao = null;
    private RuntimeExceptionDao<Competition, Integer> competitionRuntimeDao = null;

    private Dao<News, Integer> newsDao = null;
    private RuntimeExceptionDao<News, Integer> newsRuntimeDao = null;

    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public DatabaseHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
        super(context,databaseName,factory,databaseVersion);
    }

    private static DatabaseHelper instance;
    public static DatabaseHelper getHelper(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context);
        }
        return instance;
    }

    private Dao<Competition, Integer> getCompetitionDao() throws  SQLException {
        if (competitionDao == null) {
            competitionDao = getDao(Competition.class);
        }
        return competitionDao;
    }

    public RuntimeExceptionDao<Competition, Integer> getCompetitionRuntimeDao() {
        if (competitionRuntimeDao == null) {
            competitionRuntimeDao = getRuntimeExceptionDao(Competition.class);
        }
        return  competitionRuntimeDao;
    }

    private Dao<News, Integer> getNewsDao() throws SQLException {
        if (newsDao == null) {
            newsDao = getDao(News.class);
        }
        return newsDao;
    }

    public RuntimeExceptionDao<News, Integer> getNewsRuntimeDao() {
        if (newsRuntimeDao == null) {
            newsRuntimeDao =  getRuntimeExceptionDao(News.class);
        }
        return newsRuntimeDao;
    }

    @Override
    public void close() {
        super.close();
        competitionRuntimeDao = null;
        newsDao = null;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Competition.class);
            competitionDao = getCompetitionDao();
            competitionRuntimeDao = getCompetitionRuntimeDao();

            TableUtils.createTable(connectionSource, News.class);
            newsDao = getNewsDao();
            newsRuntimeDao = getNewsRuntimeDao();
        }
        catch (SQLException e) {
            Log.e(TAG,e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i2) {
        try {
            TableUtils.dropTable(connectionSource, UserDataHandler.class, true);
        }
        catch (SQLException e) {
            Log.e(TAG, e.toString());
            e.printStackTrace();
        }
    }


}
