package me.qiufeng.www.LogicalLayer.DataModule.DataManager;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.callback.Callback;

import me.qiufeng.www.AppDelegate.AppDelegate;
import me.qiufeng.www.LogicalLayer.DataModule.AVModule.AVPlayer;
import me.qiufeng.www.LogicalLayer.DataModule.AVModule.AVTeam;
import me.qiufeng.www.LogicalLayer.DataModule.LocalModule.Player;
import me.qiufeng.www.LogicalLayer.DataModule.LocalModule.Team;

/**
 * Created by QiuFeng on 2/13/15.
 */
public class PlayerManager {
    static PlayerManager playerManager = null;
    private RuntimeExceptionDao<Player, Integer> playerDao;

    public static PlayerManager sharedPlayerManager() {
        if (playerManager == null) {
            playerManager = new PlayerManager();
        }
        return playerManager;
    }

    private PlayerManager() {
        playerDao = DatabaseHelper.getHelper().getPlayerRuntimeDao();
    }


    public void getAllPlayersFromNetwork(final int competitionId, final FinishCallBack<Player> callback) {
        TeamManager.sharedTeamManager().getTeamFromNetwork(competitionId, new FinishCallBack<Team>() {
            @Override
            public void done(ArrayList<Team> list, Exception e) {
                if (e == null) {
                    AVQuery<AVPlayer> query = AVQuery.getQuery(AVPlayer.class);
                    query.limit(1000);
                    query.whereEqualTo("competitionId",competitionId);
                    query.findInBackground(new FindCallback<AVPlayer>() {
                        @Override
                        public void done(List<AVPlayer> avPlayers, AVException e) {
                            if (e == null) {
                                ArrayList<Player> result = updatePlayers(avPlayers);
                                if (callback != null) {
                                    callback.done(result, e);
                                }
                            } else {
                                if (callback != null) {
                                    callback.done(null, e);
                                }
                            }
                        }
                    });
                } else {
                    if (callback != null) {
                        callback.done(null, e);
                    }
                }
            }
        });
    }

    private ArrayList<Player> updatePlayers(List<AVPlayer> avplayers) {
        ArrayList<Player> players = new ArrayList<>();
        for (AVPlayer avPlayer : avplayers) {
            Player player = new Player();
            player.setPlayerId(avPlayer.getPlayerId());
            player.setName(avPlayer.getName());
            player.setGoalCount(avPlayer.getGoalCount());
            player.setYellowCard(avPlayer.getYellowCard());
            player.setCompetitionId(avPlayer.getCompetitionId());
            player.setTeamId(avPlayer.getTeamId());
            player.setRedCard(avPlayer.getRedCard());
            playerDao.createOrUpdate(player);
            players.add(player);
        }
        return players;
    }
}
