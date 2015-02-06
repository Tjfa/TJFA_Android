package me.qiufeng.www.LogicalLayer.DataModule.DataManager;

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
       // competitionDao = DataAccess.sharedDatabaseHelper().getCompetitionRuntimeDao();
    }

    public void updateCompetitionWithAVCompetition(AVCompetition avCompetition) {
        Competition competition = new Competition();
        //competition.setCompetitionId(avCompetition.getInt(""));
    }



    public void getEarlierCompetitionsFromNetwork(int competitionId, int type, int limit,final FinishCallBack<Competition> callback) {
        AVQuery<AVCompetition> query = AVObject.getQuery(AVCompetition.class);
        query.whereEqualTo("type", type);
        query.whereEqualTo("competitionId",competitionId);
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

    public void getLastestCompetitionsFormNetwork(int type, int limit, final FinishCallBack<Competition> callBack) {

    }

    public ArrayList<Competition> insertCompetitionsAVCompetition(List<AVCompetition> list) {
        ArrayList<Competition> result = new ArrayList<>();
        for (AVCompetition avCompetition : list) {
            Competition competition=Competition.updateCompetitionWithAVCompetition(avCompetition);
            result.add(competition);
        }

        Collections.sort(result, new Comparator<Competition>() {
            @Override
            public int compare(Competition lhs, Competition rhs) {
                return rhs.getCompetitionId() - lhs.getCompetitionId();
            }
        });
        return  result;
    }

}
