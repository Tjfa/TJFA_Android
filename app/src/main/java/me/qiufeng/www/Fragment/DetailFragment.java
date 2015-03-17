package me.qiufeng.www.Fragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.walnutlabs.android.ProgressHUD;

import java.util.ArrayList;

import me.qiufeng.www.Category.TJFAProgressHUD;
import me.qiufeng.www.LogicalLayer.DataModule.DataManager.TeamManager;
import me.qiufeng.www.LogicalLayer.DataModule.LocalModule.Competition;
import me.qiufeng.www.LogicalLayer.DataModule.LocalModule.Player;
import me.qiufeng.www.LogicalLayer.DataModule.LocalModule.Team;
import me.qiufeng.www.R;

/**
 * A simple {@link Fragment} subclass.
 */
abstract public class DetailFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    abstract protected ArrayList getAllDataFromDatabase();
    abstract protected void getAllDataFromNetwork();
    abstract protected void sort(ArrayList data);
    SwipeRefreshLayout swipeLayout;
    protected ListView listView;
    protected BaseAdapter detailAdapter;
    protected ArrayList data;
    protected int competitionId;
    Activity activity;
    ProgressHUD progressHUD;

    public DetailFragment(Activity activity, int competitionId) {
        // Required empty public constructor
        this.activity = activity;
        this.competitionId = competitionId;
    }

    protected void setupView(View parentView) {
        listView =(ListView) parentView.findViewById(R.id.list_view);

        data = getAllDataFromDatabase();
        sort(data);
        if (data == null || data.isEmpty()) {
            progressHUD = TJFAProgressHUD.showLoadingProgress(activity);
            getAllDataFromNetwork();
        }

        detailAdapter = getDetailAdapter(activity);
        listView.setAdapter(detailAdapter);

        swipeLayout =(SwipeRefreshLayout) parentView.findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(this);
    }

    protected BaseAdapter getDetailAdapter(Activity activity) {
        return new DetailAdapter(activity);
    }

    @Override
    public void onRefresh() {
        getAllDataFromNetwork();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_red_card, container, false);
    }

    public void callbackDoneFinish(ArrayList result, Exception e) {
        if (progressHUD  != null) {
            progressHUD.dismiss();
        }
        swipeLayout.setRefreshing(false);

        if (e == null) {
            data = result;
            sort(data);
            detailAdapter.notifyDataSetChanged();
        } else {
            TJFAProgressHUD.showErrorProgress(this.getActivity());
        }
    }

    public final class ViewHolder {
        public TextView playerName;
        public TextView teamName;
        public TextView dataCount;
        public TextView rank;
    }

    protected void setBaseCellDataCount(ViewHolder holder, int position) {
        Player player = (Player)data.get(position);
        holder.dataCount.setText("" + player.getGoalCount());
    }

    protected void setBaseCellName(ViewHolder holder, int position) {
        Player player = (Player)data.get(position);
        holder.playerName.setText(player.getName());
    }

    protected void setBaseCellTeamName(ViewHolder holder, int position) {
        Player player = (Player)data.get(position);
        int teamId = player.getTeamId();
        Team team =  TeamManager.sharedTeamManager().getTeamByTeamId(teamId);
        holder.teamName.setText(team.getName());
    }

    protected View getLayoutInflater(LayoutInflater inflater) {
        return inflater.inflate(R.layout.detail_base_cell,null);
    }

    protected View getCell(LayoutInflater inflater, final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = getLayoutInflater(inflater);
            holder = new ViewHolder();

                /*得到各个控件的对象*/
            holder.playerName = (TextView) convertView.findViewById(R.id.player_name);
            holder.teamName = (TextView) convertView.findViewById(R.id.team_name);
            holder.dataCount = (TextView) convertView.findViewById(R.id.data_count);
            holder.rank  = (TextView) convertView.findViewById(R.id.player_rank);
            convertView.setTag(holder);//绑定ViewHolder对象
        } else {
            holder = (ViewHolder)convertView.getTag();//取出ViewHolder对象
        }

        setBaseCellDataCount(holder, position);
        setBaseCellName(holder, position);
        setBaseCellTeamName(holder, position);
        holder.rank.setText("" + (position + 1) );

        return convertView;
    }

    protected class DetailAdapter extends BaseAdapter {

        private LayoutInflater inflater;

        public DetailAdapter(Context context) {
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return  position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            return getCell(inflater,position, convertView, parent);

        }
    }

}
