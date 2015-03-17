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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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


    public MatchFragment(Activity activity, int competitionId) {
        super(activity, competitionId);
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

    private String getDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    private String getTime(Date time) {
        SimpleDateFormat format = new SimpleDateFormat("hh:mm");
        return format.format(time);
    }

    private String getIsStart(int isStart) {
        if (isStart == 0) return "未开始";
        else return "已结束";
    }

    private String getProperty(int property) {
        if (property == 0) return "小组赛";
        if (property == 100) return "附加赛";
        if (property == 1) return "决赛";
        if (property == 2) return "半决赛";
        if (property == 3) return "三四名";
        return "1/" + property + " 决赛";
    }

    @Override
    protected View getCell(LayoutInflater inflater, final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = getLayoutInflater(inflater);
            holder = new ViewHolder();

            /*得到各个控件的对象*/
            holder.teamAImageView = (ImageView) convertView.findViewById(R.id.match_teamA_badge);
            holder.teamBImageView = (ImageView) convertView.findViewById(R.id.match_teamB_badge);
            holder.teamAName = (TextView) convertView.findViewById(R.id.match_teamA_name);
            holder.teamBName = (TextView) convertView.findViewById(R.id.match_teamB_name);
            holder.date = (TextView) convertView.findViewById(R.id.match_date);
            holder.property = (TextView) convertView.findViewById(R.id.match_property);
            holder.time = (TextView) convertView.findViewById(R.id.match_time);
            holder.isStart = (TextView) convertView.findViewById(R.id.match_is_start);
            holder.goalA = (TextView) convertView.findViewById(R.id.match_goalA);
            holder.penaltyA = (TextView) convertView.findViewById(R.id.match_penaltyA);
            holder.goalB = (TextView) convertView.findViewById(R.id.match_goalB);
            holder.penaltyB = (TextView) convertView.findViewById(R.id.match_penaltyB);

            convertView.setTag(holder);//绑定ViewHolder对象
        } else {
            holder = (ViewHolder)convertView.getTag();//取出ViewHolder对象
        }

        Match match = (Match) data.get(position);
        Team teamA = TeamManager.sharedTeamManager().getTeamByTeamId(match.getTeamAId());
        Team teamB = TeamManager.sharedTeamManager().getTeamByTeamId(match.getTeamBId());

        if (teamA.getBadgeImage() != null && !teamA.getBadgeImage().isEmpty()) {
            Picasso.with(activity).load(teamA.getBadgeImage())
                    .placeholder(R.drawable.team_placeholder1)
                    .error(R.drawable.team_placeholder1)
                    .into(holder.teamAImageView);
        } else {
            holder.teamAImageView.setImageResource(R.drawable.team_placeholder1);
        }

        if (teamB.getBadgeImage() != null && !teamB.getBadgeImage().isEmpty()) {
            Picasso.with(activity).load(teamB.getBadgeImage())
                    .placeholder(R.drawable.team_placeholder2)
                    .error(R.drawable.team_placeholder2)
                    .into(holder.teamBImageView);
        } else {
           holder.teamBImageView.setImageResource(R.drawable.team_placeholder2);
        }

        holder.teamAName.setText(teamA.getName());
        holder.teamBName.setText(teamB.getName());
        holder.date.setText(getDate(match.getDate()));
        holder.property.setText(getProperty(match.getMatchProperty()));
        holder.time.setText(getTime(match.getDate()));
        holder.isStart.setText(getIsStart(match.getIsStart()));
        holder.goalA.setText("" + match.getScoreA());
        holder.goalB.setText("" + match.getScoreB());

        if (match.getPenaltyA() == 0 && match.getPenaltyB() == 0) {
            holder.penaltyA.setVisibility(View.INVISIBLE);
            holder.penaltyB.setVisibility(View.INVISIBLE);
        } else {
            holder.penaltyA.setVisibility(View.VISIBLE);
            holder.penaltyB.setVisibility(View.VISIBLE);
            holder.penaltyA.setText("(" + match.getPenaltyA() + ")");
            holder.penaltyB.setText("(" + match.getPenaltyB() + ")");
        }

        return convertView;
    }

    public class ViewHolder {
        ImageView teamAImageView;
        ImageView teamBImageView;
        TextView teamAName;
        TextView teamBName;
        TextView date;
        TextView property;
        TextView time;
        TextView isStart;
        TextView goalA;
        TextView penaltyA;
        TextView goalB;
        TextView penaltyB;
    }
}
