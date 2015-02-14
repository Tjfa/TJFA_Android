package me.qiufeng.www.LogicalLayer.DataModule.DataManager;

import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import me.qiufeng.www.LogicalLayer.DataModule.AVModule.AVCompetition;
import me.qiufeng.www.LogicalLayer.DataModule.LocalModule.Competition;

/**
 * Created by QiuFeng on 2/3/15.
 */
public class CompetitionManager {

    private RuntimeExceptionDao<Competition, Integer> competitionDao = null;

    static private CompetitionManager competitionManager = null;

    static public CompetitionManager sharedCompetitionManager() {
        if (competitionManager == null) {
            competitionManager = new CompetitionManager();
        }
        return competitionManager;
    }

    private CompetitionManager() {
        competitionDao = DatabaseHelper.getHelper().getCompetitionRuntimeDao();
    }

    public void updateCompetitionWithAVCompetition(AVCompetition avCompetition) {
        Competition competition = new Competition();
        //competition.setCompetitionId(avCompetition.getInt(""));
    }

    public void getEarlierCompetitionsFromNetwork(int competitionId, int type, int limit,final FinishCallBack<Competition> callback) {
        AVQuery<AVCompetition> query = AVObject.getQuery(AVCompetition.class);
        query.whereEqualTo("type", type);
       // query.whereGreaterThanOrEqualTo()
        query.limit(limit);

        query.findInBackground(new FindCallback<AVCompetition>() {
            @Override
            public void done(List<AVCompetition> avCompetitions, AVException e) {
                if (e != null) {
                    callback.done(null,e);
                } else {
                    ArrayList<Competition> result = insertCompetitionsAVCompetition(avCompetitions);
                    callback.done(result, null);
                }
            }
        });
    }

    public void getLastestCompetitionsFromNetwork(int type, int limit, final FinishCallBack<Competition> callBack) {
        AVQuery<AVCompetition> query = AVObject.getQuery(AVCompetition.class);
        query.whereEqualTo("type",type);
        query.limit(limit);


        query.findInBackground(new FindCallback<AVCompetition>() {
            @Override
            public void done(List<AVCompetition> avCompetitions, AVException e) {
                if (e != null) {
                    callBack.done(null,e);
                } else {
                    ArrayList<Competition> result = insertCompetitionsAVCompetition(avCompetitions);
                    callBack.done(result,null);
                }
            }
        });
    }

    public ArrayList<Competition> insertCompetitionsAVCompetition(List<AVCompetition> list) {
        ArrayList<Competition> result = new ArrayList<>();
        for (AVCompetition avCompetition : list) {
            Competition competition = new Competition();
            competition.setCompetitionId(avCompetition.getCompetitionId());
            competition.setName(avCompetition.getName());
            competition.setIsStart(avCompetition.getIsStart());
            competition.setType(avCompetition.getType());
            competition.setNumber(avCompetition.getNumber());
            competition.setTime(avCompetition.getTime());
            competitionDao.createOrUpdate(competition);
            result.add(competition);
        }
        return competitionsSort(result);
    }

    public void sortWithTime(ArrayList<Competition> list) {
        Collections.sort(list, new Comparator<Competition>() {
            @Override
            public int compare(Competition lhs, Competition rhs) {
                return  rhs.getTime().compareTo(lhs.getTime());
            }
        });
    }

    private ArrayList<Competition> competitionsSort(List<Competition> result) {
        ArrayList<Competition> competitions = new ArrayList<>();
        for (Competition competition : result) {
            competitions.add(competition);
        }

        Collections.sort(competitions, new Comparator<Competition>() {
            @Override
            public int compare(Competition lhs, Competition rhs) {
                return rhs.getCompetitionId() - lhs.getCompetitionId();
            }
        });
        return competitions;
    }


    //for debug
    public void description(List<Competition> competitions) {
        for (Competition competition : competitions) {
            Log.i("",competition.getCompetitionId() + ":" + competition.getName() );
        }
    }

}
