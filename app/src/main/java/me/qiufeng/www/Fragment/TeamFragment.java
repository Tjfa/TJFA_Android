package me.qiufeng.www.Fragment;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
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
import me.qiufeng.www.LogicalLayer.DataModule.DataManager.TeamManager;
import me.qiufeng.www.LogicalLayer.DataModule.LocalModule.Team;
import me.qiufeng.www.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamFragment extends DetailFragment {

    public TeamFragment() {
        super();
    }

    public static TeamFragment newInstance(Activity activity, int competitionId) {
        TeamFragment fragment = new TeamFragment();
        fragment.activity = activity;
        fragment.competitionId = competitionId;
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View parentView = inflater.inflate(R.layout.fragment_team, container, false);
        setupView(parentView);
        return parentView;
    }

    private ArrayList<Team> converListToArrayList(List<Team> list) {
        ArrayList<Team> teams = new ArrayList<>();
        for (Team team : list) {
            teams.add(team);
        }
        return teams;
    }

    @Override
    protected ArrayList getAllDataFromDatabase() {
        List<Team> list =  TeamManager.sharedTeamManager().getTeamsFromDatabase(competitionId);
        return converListToArrayList(list);
    }

    @Override
    protected void getAllDataFromNetwork() {
        TeamManager.sharedTeamManager().getTeamsFromNetwork(competitionId, new FinishCallBack<Team>() {
            @Override
            public void done(ArrayList<Team> list, Exception e) {
                callbackDoneFinish(list, e);
            }
        });
    }

    @Override
    protected void sort(ArrayList list) {
        Collections.sort(list, new Comparator<Team>() {
            @Override
            public int compare(Team lhs, Team rhs) {
                if (lhs.getRank() == rhs.getRank()) {
                    return lhs.getTeamId() - rhs.getTeamId(); //为了配合iOS版本的排序算法
                } else {
                    if (lhs.getRank() == 100) {
                        return 1;
                    } else if (rhs.getRank() == 100) {
                        return -1;
                    } else if (lhs.getRank() == 0) {
                        return 1;
                    } else if (rhs.getRank() == 0) {
                        return -1;
                    } else {
                        return lhs.getRank() - rhs.getRank();
                    }
                }
            }
        });
    }


    @Override
    protected View getLayoutInflater(LayoutInflater inflater) {
        return inflater.inflate(R.layout.detail_team_cell,null);
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

        Team team = (Team)data.get(position);
        if (team.getBadgeImage() != null && !team.getBadgeImage().isEmpty()) {
            Picasso.with(activity).load(team.getBadgeImage())
                    .placeholder(R.drawable.team_placeholder1)
                    .error(R.drawable.team_placeholder1)
                    .into(holder.image);
        } else {
            holder.image.setImageResource(R.drawable.team_placeholder1);
        }

        holder.teamName.setText(team.getName());
        holder.goalCount.setText("进 " + team.getGoalCount());
        holder.missCount.setText("失 " + team.getMissCount());
        holder.group.setText("组 " + team.getGroupNo());
        holder.rank.setText(TeamManager.sharedTeamManager().getRankString(team.getRank()));
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
