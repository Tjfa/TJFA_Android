package me.qiufeng.www.LogicalLayer.DataModule.DataManager;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.ArrayList;
import java.util.List;

import me.qiufeng.www.AppDelegate.AppDelegate;
import me.qiufeng.www.LogicalLayer.DataModule.AVModule.AVMatch;
import me.qiufeng.www.LogicalLayer.DataModule.LocalModule.Match;
import me.qiufeng.www.LogicalLayer.DataModule.LocalModule.Team;

/**
 * Created by QiuFeng on 2/13/15.
 */
public class MatchManager {
    private RuntimeExceptionDao<Match,Integer> matchDao = null;
    static public MatchManager sharedMatchManager = null;

    static public MatchManager sharedMatchManager() {
        if (sharedMatchManager == null) {
            sharedMatchManager = new MatchManager();
        }
        return sharedMatchManager;
    }

    private MatchManager() {
        matchDao = DatabaseHelper.getHelper().getMatchRuntimeDao();
    }

    public List<Match> getAllMatchesFromDatabase(int compeititonId) {
        return matchDao.queryForEq("competitionId", compeititonId);
    }

    public void getMatchesFromNetwork(final int competitionId, final FinishCallBack<Match> callBack) {
        TeamManager.sharedTeamManager().getTeamsFromNetwork(competitionId, new FinishCallBack<Team>() {
            @Override
            public void done(ArrayList<Team> list, Exception e) {
                if (e == null) {
                    AVQuery<AVMatch> query = AVQuery.getQuery(AVMatch.class);
                    query.limit(1000);
                    query.whereEqualTo("competitionId",competitionId);
                    query.findInBackground(new FindCallback<AVMatch>() {
                        @Override
                        public void done(List<AVMatch> avMatches, AVException e) {
                            if (e == null) {
                                ArrayList<Match> matches = updateMatches(avMatches);
                                if (callBack != null) {
                                    callBack.done(matches,null);
                                }
                            } else {
                                if (callBack != null) {
                                    callBack.done(null, e);
                                }
                            }
                        }
                    });
                } else {
                    if (callBack != null) {
                        callBack.done(null, e);
                    }
                }
            }
        });
    }

    public ArrayList<Match> updateMatches(List<AVMatch> avMatches) {
        ArrayList<Match> matches = new ArrayList<>();
        for (AVMatch avMatch : avMatches) {
            Match match = new Match();
            match.setMatchId(avMatch.getMatchId());
            match.setDate(avMatch.getDate());
            match.setCompetitionId(avMatch.getCompetitionId());
            match.setHint(avMatch.getHint());
            match.setIsStart(avMatch.getIsStart());
            match.setMatchProperty(avMatch.getMatchProperty());
            match.setScoreA(avMatch.getScoreA());
            match.setScoreB(avMatch.getScoreB());
            match.setTeamAId(avMatch.getTeamAId());
            match.setTeamBId(avMatch.getTeamBId());
            match.setPenaltyA(avMatch.getPenaltyA());
            match.setPenaltyB(avMatch.getPenaltyB());
            matchDao.createOrUpdate(match);
            matches.add(match);
        }
        return matches;
    }
}
