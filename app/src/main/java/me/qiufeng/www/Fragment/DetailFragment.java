package me.qiufeng.www.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.walnutlabs.android.ProgressHUD;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import me.qiufeng.www.LogicalLayer.DataModule.DataManager.DatabaseManager;
import me.qiufeng.www.LogicalLayer.DataModule.LocalModule.News;
import me.qiufeng.www.LogicalLayer.DataModule.LocalModule.Player;
import me.qiufeng.www.R;

/**
 * A simple {@link Fragment} subclass.
 */
abstract public class DetailFragment extends Fragment {

    abstract protected ArrayList getAllDataFromDatabase();
    abstract protected void getAllDataFromNetwork();

    SwipeRefreshLayout swipeLayout;
    protected ListView listView;
    protected DetailAdapter adapter;
    protected ArrayList data;
    protected int competitionId;

    ProgressHUD progressHUD;

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        listView =(ListView) getView().findViewById(R.id.list_view);

        data = getAllDataFromDatabase();

        DetailAdapter detailAdapter = new DetailAdapter(this.getActivity());
        listView.setAdapter(detailAdapter);

        return inflater.inflate(R.layout.fragment_red_card_fragment, container, false);
    }

    public void callbackDoneFinish() {
        adapter.notifyDataSetChanged();
    }

    class DetailAdapter extends BaseAdapter {

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
            ViewHolder holder;

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.detail_base_cell,null);
                holder = new ViewHolder();

                /*得到各个控件的对象*/
                holder.playerName = (TextView) convertView.findViewById(R.id.player_name);
                holder.teamName = (TextView) convertView.findViewById(R.id.team_name);
                holder.dataCount = (TextView) convertView.findViewById(R.id.data_count);
                convertView.setTag(holder);//绑定ViewHolder对象
            } else {
                holder = (ViewHolder)convertView.getTag();//取出ViewHolder对象
            }

            if (position == data.size()-1) {
                //loadEarlierData();
            }

            News news = (News)getItem(position);
            /*设置TextView显示的内容，即我们存放在动态数组中的数据*/
            //holder.title.setText(news.getTitle());
            //holder.preContent.setText(news.getPrecontent());

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            //holder.time.setText(format.format(news.getDate()));
            return convertView;
        }


        public final class ViewHolder {
            public TextView playerName;
            public TextView teamName;
            public TextView dataCount;
        }
    }

}
