package me.qiufeng.www.Fragment;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import me.qiufeng.www.LogicalLayer.DataModule.DataManager.FinishCallBack;
import me.qiufeng.www.LogicalLayer.DataModule.DataManager.MatchManager;
import me.qiufeng.www.LogicalLayer.DataModule.DataManager.TeamManager;
import me.qiufeng.www.LogicalLayer.DataModule.LocalModule.Match;
import me.qiufeng.www.LogicalLayer.DataModule.LocalModule.Team;
import me.qiufeng.www.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MatchFragment extends DetailFragment {


    public MatchFragment(Activity activity) {
        super(activity);
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View parentView = inflater.inflate(R.layout.fragment_match, container, false);
        setupView(parentView);
        return parentView;
    }

    private ArrayList<Match> converListToArrayList(List<Match> list) {
        ArrayList<Match> matches = new ArrayList<>();
        for (Match match : list) {
            matches.add(match);
        }
        return matches;
    }

    @Override
    protected ArrayList getAllDataFromDatabase() {
        List<Match> list =  MatchManager.sharedMatchManager().getAllMatchesFromDatabase(competitionId);
        return converListToArrayList(list);
    }

    @Override
    protected void getAllDataFromNetwork() {
        MatchManager.sharedMatchManager().getAllMatchesFromNetwork(competitionId, new FinishCallBack<Match>() {
            @Override
            public void done(ArrayList<Match> list, Exception e) {
                callbackDoneFinish(list, e);
            }
        });
    }

    protected void sort(ArrayList list) {
        Collections.sort(list, new Comparator<Match>() {
            @Override
            public int compare(Match lhs, Match rhs) {
                return rhs.getMatchId() - lhs.getMatchId();
            }
        });
    }

    @Override
    protected View getLayoutInflater(LayoutInflater inflater) {
        return inflater.inflate(R.layout.detail_match_cell,null);
    }

    @Override
    protected View getCell(LayoutInflater inflater, final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = getLayoutInflater(inflater);
            holder = new ViewHolder();

            /*得到各个控件的对象*/
            holder.image = (ImageView) convertView.findViewById(R.id.team_badge);
            holder.teamName = (TextView) convertView.findViewById(R.id.team_name);
            holder.goalCount = (TextView) convertView.findViewById(R.id.team_goalCount);
            holder.missCount = (TextView) convertView.findViewById(R.id.team_missCount);
            holder.rank  = (TextView) convertView.findViewById(R.id.team_rank);
            holder.group = (TextView) convertView.findViewById(R.id.team_group);
            convertView.setTag(holder);//绑定ViewHolder对象
        } else {
            holder = (ViewHolder)convertView.getTag();//取出ViewHolder对象
        }


        return convertView;
    }

    public class ViewHolder {
        ImageView image;
        TextView teamName;
        TextView goalCount;
        TextView missCount;
        TextView rank;
        TextView group;
    }
}
