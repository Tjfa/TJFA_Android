package me.qiufeng.www.Fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import me.qiufeng.www.LogicalLayer.DataModule.DataManager.FinishCallBack;
import me.qiufeng.www.LogicalLayer.DataModule.DataManager.PlayerManager;
import me.qiufeng.www.LogicalLayer.DataModule.LocalModule.Player;
import me.qiufeng.www.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class YellowCardFragment extends DetailFragment {

    public YellowCardFragment() {
        super();
    }

    public static YellowCardFragment newInstance(Activity activity, int competitionId) {
        YellowCardFragment fragment = new YellowCardFragment();
        fragment.activity = activity;
        fragment.competitionId = competitionId;
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View parentView = inflater.inflate(R.layout.fragment_yellow_card, container, false);
        setupView(parentView);
        return parentView;
    }



    private ArrayList<Player> converListToArrayList(List<Player> list) {
        ArrayList<Player> players = new ArrayList<>();
        for (Player player : list) {
            players.add(player);
        }
        return players;
    }

    @Override
    protected ArrayList getAllDataFromDatabase() {
        List<Player> list =  PlayerManager.sharedPlayerManager().getAllPlayersFromeDatabase(competitionId);
        return converListToArrayList(list);
    }

    @Override
    protected void getAllDataFromNetwork() {
        PlayerManager.sharedPlayerManager().getAllPlayersFromNetwork(competitionId, new FinishCallBack<Player>() {
            @Override
            public void done(ArrayList<Player> list, Exception e) {
                callbackDoneFinish(list, e);
            }
        });
    }


    @Override
    protected void setBaseCellDataCount(ViewHolder holder, int position) {
        Player player = (Player)data.get(position);
        holder.dataCount.setText("" + player.getYellowCard());
    }

    @Override
    protected void sort(ArrayList list) {
        Collections.sort(list, new Comparator<Player>() {
            @Override
            public int compare(Player lhs, Player rhs) {
                return rhs.getYellowCard() - lhs.getYellowCard();
            }
        });
    }
}
