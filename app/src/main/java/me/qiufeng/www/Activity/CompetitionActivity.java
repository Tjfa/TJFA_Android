package me.qiufeng.www.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.walnutlabs.android.ProgressHUD;

import java.util.ArrayList;

import me.qiufeng.www.Category.TJFAProgressHUD;
import me.qiufeng.www.Const.AppConst;
import me.qiufeng.www.LogicalLayer.DataModule.DataManager.CompetitionManager;
import me.qiufeng.www.LogicalLayer.DataModule.DataManager.FinishCallBack;
import me.qiufeng.www.LogicalLayer.DataModule.LocalModule.Competition;
import me.qiufeng.www.LogicalLayer.DataModule.LocalModule.News;
import me.qiufeng.www.R;

public class CompetitionActivity extends ActionBarActivity {

    private ArrayList< ArrayList<Competition> > data = new ArrayList<>();
    private int type;
    private ListView listView;
    private CompetitionCellAdapter adapter;
    ProgressHUD loadingProgress;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition);

        type =(int) getIntent().getSerializableExtra("type");

        if (type == 1) {
            setTitle("本部");
        } else {
            setTitle("嘉定");
        }

        ArrayList<Competition> localData = CompetitionManager.sharedCompetitionManager().getCompetitionsFromDatabase(type);
        data = getDataListWithCompetitionList(localData);
        if (data ==  null || data.isEmpty()) {
            getLasterCompetitions(true);
        }

        listView =(ListView) findViewById(R.id.competition_list_view);
        adapter = new CompetitionCellAdapter(this);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CompetitionActivity.this, CompetitionDetailActivity.class);
                Bundle bundle = new Bundle();
                Competition competition = getCompetitionItem(position);
                bundle.putSerializable("competition", competition);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.competition_swipe_container);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getLasterCompetitions(false);
            }
        });
    }

    private void getLasterCompetitions(final boolean showProgress) {

        if (showProgress) {
            loadingProgress = TJFAProgressHUD.showLoadingProgress(this);
        }

        CompetitionManager.sharedCompetitionManager().getLastestCompetitionsFromNetwork(type, AppConst.defalultLimit,new FinishCallBack<Competition>() {
            @Override
            public void done(ArrayList<Competition> list, Exception e) {
                if (showProgress) {
                    loadingProgress.dismiss();
                }
                swipeRefreshLayout.setRefreshing(false);

                if (e == null) {
                    CompetitionManager.sharedCompetitionManager().description(list);
                    data = getDataListWithCompetitionList(list);
                    adapter.notifyDataSetChanged();
                } else {
                    TJFAProgressHUD.showErrorProgress(CompetitionActivity.this);
                }
            }
        });
    }

    private Competition getCompetitionItem(int position) {
        int section = 0;
        while (position >= data.get(section).size() + 1 ) {  //+1 是header
            position -= data.get(section++).size() + 1;
        }

        if (position == 0) {
            return data.get(section).get(0);
        } else {
            return data.get(section).get(position - 1);
        }
    }

    private ArrayList<ArrayList<Competition>> getDataListWithCompetitionList(ArrayList<Competition> list) {

        CompetitionManager.sharedCompetitionManager().sortWithTime(list);
        ArrayList<ArrayList<Competition>> result = new ArrayList<>();

        ArrayList<Competition> lastArray = null;
        for (Competition competition : list) {
            if (lastArray == null || !lastArray.get(0).getTime().equals(competition.getTime())) {
                lastArray = new ArrayList<>();
                result.add(lastArray);
            }
            lastArray.add(competition);
        }

        return result;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_competition, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class CompetitionCellAdapter extends BaseAdapter {

        static final int SectionHeader = 0;
        static final int CompetitionCell = 1;

        private LayoutInflater inflater;


        public CompetitionCellAdapter(Context context) {
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            int cellCount = 0;
            for (int i = 0; i < data.size(); i++) {
                 cellCount += data.get(i).size();
            }
            return cellCount + data.size();
        }

        @Override
        public Object getItem(int position) {
            return getCompetitionItem(position);
        }

        @Override
        public long getItemId(int position) {
            return  position;
        }


        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position) {

            int section = 0;
            while (position >= data.get(section).size() + 1 ) {  //+1 是header
                position -= data.get(section++).size() + 1;
            }

            if (position == 0) {
                return SectionHeader;
            } else {
                return CompetitionCell;
            }
        }


        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;

            int rowType = getItemViewType(position);

            if (convertView == null) {

                holder = new ViewHolder();
                switch (rowType) {
                    case SectionHeader:
                        convertView = inflater.inflate(R.layout.competition_section, null);
                        holder.title = (TextView) convertView.findViewById(R.id.competition_section_title);
                        break;
                    case CompetitionCell:
                        convertView = inflater.inflate(R.layout.competition_cell, null);
                        holder.title = (TextView) convertView.findViewById(R.id.competition_name);
                        holder.cupImage = (ImageView) convertView.findViewById(R.id.image_cup);
                        break;
                }
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder)convertView.getTag();//取出ViewHolder对象
            }

            Competition competition = (Competition)getItem(position);
            switch (rowType) {
                case SectionHeader:
                    holder.title.setText(getTitleTimeFromCompetition(competition.getTime()));
                    break;
                case CompetitionCell:
                    holder.title.setText(competition.getName());
                    break;
            }

            return convertView;
        }

        private String getTitleTimeFromCompetition(String time) {
            if (time == null || time.equals("")) {
                return "";
            }

            String term = time.substring(time.length() - 1,time.length());
            if (term.equals("1")) {
                return time.substring(0,time.length() - 1) + " 年上学期";
            } else {
                return time.substring(0,time.length() - 1) + " 年下学期";
            }
        }


        final class ViewHolder {
            ImageView cupImage;
            TextView title;
        }

    }
}
