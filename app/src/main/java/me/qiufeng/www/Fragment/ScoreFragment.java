package me.qiufeng.www.Fragment;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import me.qiufeng.www.LogicalLayer.DataModule.DataManager.FinishCallBack;
import me.qiufeng.www.LogicalLayer.DataModule.DataManager.TeamManager;
import me.qiufeng.www.LogicalLayer.DataModule.LocalModule.Competition;
import me.qiufeng.www.LogicalLayer.DataModule.LocalModule.Team;
import me.qiufeng.www.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScoreFragment extends DetailFragment {


    public ScoreFragment() {

    }

    public static ScoreFragment newInstance(Activity activity, int competitionId) {
        ScoreFragment fragment = new ScoreFragment();
        fragment.activity = activity;
        fragment.competitionId = competitionId;
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View parentView = inflater.inflate(R.layout.fragment_score, container, false);
        setupView(parentView);
        return parentView;
    }

    protected BaseAdapter getDetailAdapter(Activity activity) {
        return new ScoreAdapter(activity);
    }

    private ArrayList<ArrayList<Team>> converListToArrayList(List<Team> list) {
        HashMap<String, ArrayList<Team>> hashTeam = new HashMap<>();
        for (Team team : list) {
            ArrayList<Team> teams = hashTeam.get(team.getGroupNo());
            if (teams == null) {
                teams = new ArrayList<Team>();
                hashTeam.put(team.getGroupNo(), teams);
            }
            teams.add(team);
        }

        ArrayList<ArrayList<Team>> arrayTeam = new ArrayList<>();
        arrayTeam.addAll(hashTeam.values());

        return arrayTeam;
    }

    @Override
    protected ArrayList getAllDataFromDatabase() {
        List<Team> list = TeamManager.sharedTeamManager().getTeamsFromDatabase(competitionId);
        return converListToArrayList(list);
    }

    @Override
    protected void getAllDataFromNetwork() {
        TeamManager.sharedTeamManager().getTeamsFromNetwork(competitionId, new FinishCallBack<Team>() {
            @Override
            public void done(ArrayList<Team> list, Exception e) {
                callbackDoneFinish(converListToArrayList(list), e);
            }
        });
    }

    protected void sort(ArrayList list) {
        Collections.sort(list, new Comparator<ArrayList<Team>>() {
            @Override
            public int compare(ArrayList<Team> lhs, ArrayList<Team> rhs) {
                if (lhs.isEmpty()) return -1;
                if (rhs.isEmpty()) return 1;
                Team a = lhs.get(0);
                Team b = rhs.get(1);
                return a.getGroupNo().compareTo(b.getGroupNo());
            }
        });

        for (ArrayList<Team> teams : (ArrayList<ArrayList<Team>>) list) {
            Collections.sort(teams, new Comparator<Team>() {
                @Override
                public int compare(Team lhs, Team rhs) {
                    if (lhs.getScore() != rhs.getScore()) {
                        return rhs.getScore() - lhs.getScore();
                    } else {
                        int goalA = lhs.getGroupGoalCount();
                        int missA = lhs.getGroupMissCount();
                        int goalB = rhs.getGroupGoalCount();
                        int missB = rhs.getGroupMissCount();
                        if (goalA - missA != goalB - missB) {
                            int winA = goalA - missA;
                            int winB = goalB - missB;
                            return winB - winA;
                        } else {
                            return goalB - goalA;
                        }
                    }
                }
            });
        }

    }

    protected class ScoreAdapter extends BaseAdapter {

        static final int SectionHeader = 0;
        static final int ScoreCell = 1;

        private LayoutInflater inflater;

        public ScoreAdapter(Context context) {
            inflater = LayoutInflater.from(context);
        }


        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position) {

            int section = 0;
            while (position >= ((ArrayList) data.get(section)).size() + 1) {  //+1 是header
                position -= ((ArrayList) data.get(section++)).size() + 1;
            }

            if (position == 0) {
                return SectionHeader;
            } else {
                return ScoreCell;
            }
        }

        @Override
        public int getCount() {
            int cellCount = 0;
            for (int i = 0; i < data.size(); i++) {
                cellCount += ((ArrayList) data.get(i)).size();
            }
            return cellCount + data.size();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public Object getItem(int position) {
            int section = 0;
            while (position >= ((ArrayList) data.get(section)).size() + 1) {  //+1 是header
                position -= ((ArrayList) data.get(section++)).size() + 1;
            }

            if (position == 0) {
                return ((ArrayList) data.get(section)).get(0);
            } else {
                return ((ArrayList) data.get(section)).get(position - 1);
            }
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;

            int rowType = getItemViewType(position);

            if (convertView == null) {

                holder = new ViewHolder();
                switch (rowType) {
                    case SectionHeader:
                        convertView = inflater.inflate(R.layout.detail_score_section, null);
                        holder.group = (TextView) convertView.findViewById(R.id.score_section_title);
                        break;
                    case ScoreCell:
                        convertView = inflater.inflate(R.layout.detail_score_cell, null);

                        holder.teamName = (TextView) convertView.findViewById(R.id.score_team_name);
                        holder.score =(TextView) convertView.findViewById(R.id.score_score);
                        holder.winCount = (TextView) convertView.findViewById(R.id.score_win_count);
                        holder.drawCount = (TextView) convertView.findViewById(R.id.score_draw_count);
                        holder.lostCount = (TextView) convertView.findViewById(R.id.score_lost_count);
                        holder.goalCount = (TextView) convertView.findViewById(R.id.score_goal_count);
                        holder.goalMissCount = (TextView) convertView.findViewById(R.id.score_goal_miss_count);
                        break;
                }
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();//取出ViewHolder对象
            }

            Team team = (Team) getItem(position);
            switch (rowType) {
                case SectionHeader:
                    holder.group.setText(team.getGroupNo());
                    break;
                case ScoreCell:
                    holder.teamName.setText(team.getName());
                    holder.score.setText("" + team.getScore());
                    holder.winCount.setText("" + team.getGroupWinCount());
                    holder.drawCount.setText("" + team.getGroupDrawCount());
                    holder.lostCount.setText("" + team.getGroupLostCount());
                    holder.goalCount.setText("" + team.getGroupWinCount());
                    holder.goalMissCount.setText("" + (team.getGroupGoalCount() - team.getGroupMissCount()));
                    break;
            }

            return convertView;
        }

        class ViewHolder {
            TextView group;
            TextView teamName;
            TextView score;
            TextView winCount;
            TextView drawCount;
            TextView lostCount;
            TextView goalCount;
            TextView goalMissCount;
        }

    }
}
