package me.qiufeng.www.Fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import me.qiufeng.www.LogicalLayer.DataModule.DataManager.FinishCallBack;
import me.qiufeng.www.LogicalLayer.DataModule.DataManager.PlayerManager;
import me.qiufeng.www.LogicalLayer.DataModule.LocalModule.Competition;
import me.qiufeng.www.LogicalLayer.DataModule.LocalModule.Player;
import me.qiufeng.www.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RedCardFragment extends DetailFragment {


    public RedCardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_red_card_fragment, container, false);
    }

    private ArrayList<Player> converListToArrayList(List<Player> list) {
        ArrayList<Player> players = new ArrayList<>();
        for (Player player : list) {
            players.add(player);
        }

        Collections.sort(players, new Comparator<Player>() {
            @Override
            public int compare(Player lhs, Player rhs) {
                return rhs.getRedCard() - lhs.getYellowCard();
            }
        });
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
                callbackDoneFinish();
            }
        });
    }
}
