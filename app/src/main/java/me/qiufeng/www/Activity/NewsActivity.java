package me.qiufeng.www.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.walnutlabs.android.ProgressHUD;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import me.qiufeng.www.Category.TJFAProgressHUD;
import me.qiufeng.www.Const.AppConst;
import me.qiufeng.www.LogicalLayer.DataModule.DataManager.FinishCallBack;
import me.qiufeng.www.LogicalLayer.DataModule.DataManager.NewsManager;
import me.qiufeng.www.LogicalLayer.DataModule.LocalModule.News;
import me.qiufeng.www.R;

public class NewsActivity extends ActionBarActivity implements SwipeRefreshLayout.OnRefreshListener{

    SwipeRefreshLayout swipeLayout;
    private ListView listView;
    private NewsCellAdapter adapter;
    ArrayList<News> data;
    ProgressHUD progressHUD;
    boolean hasMore = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);


        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.news_swipe_container);
        swipeLayout.setOnRefreshListener(this);

        data = NewsManager.sharedNewsManager().getAllNewsFromDatabase();
        if (data == null || data.isEmpty()) {
            loadLastestData(true);
        } else {
            onRefresh();
            swipeLayout.setProgressViewOffset(false, 0, 100);
            swipeLayout.setRefreshing(true);
        }

        listView = (ListView)findViewById(R.id.news_list_view);
        adapter = new NewsCellAdapter(this);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(NewsActivity.this, NewsDetailActivity.class);
                Bundle bundle = new Bundle();
                News news = data.get(position);
                NewsManager.sharedNewsManager().updateNewsToRead(news);
                adapter.notifyDataSetChanged();
                bundle.putSerializable("news", news);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final News news = data.get(position);
                AlertDialog.Builder builder =new AlertDialog.Builder(NewsActivity.this);

                if (news.getIsRead()) {
                    builder.setTitle("标记未读");
                } else {
                    builder.setTitle("标记已读");
                }

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        NewsManager.sharedNewsManager().updateNewsStatus(news);
                        adapter.notifyDataSetChanged();
                    }
                });

                builder.setNegativeButton("取消",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
                return true;
            }
        });
    }

    @Override
    public void onRefresh() {
        loadLastestData(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_news, menu);
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

    private void loadEarlierData() {
        if (data.isEmpty()) {
            return;
        }
        News lastNews = data.get(data.size()-1);
        NewsManager.sharedNewsManager().getEarlierDataFromNetwork(AppConst.defalultLimit,lastNews.getNewsId(), new FinishCallBack<News>() {
            @Override
            public void done(ArrayList<News> list, Exception e) {
                if (e == null) {
                    if (list.isEmpty()) {
                        hasMore = false;
                    } else {
                        data.addAll(list);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    private void loadLastestData(final boolean showProgressHUD) {
        if (showProgressHUD) {
            progressHUD = ProgressHUD.show(NewsActivity.this, "载入中", true, false, null);
        }

        NewsManager.sharedNewsManager.getLastestDataFromNetwork(AppConst.defalultLimit, new FinishCallBack<News>() {
            @Override
            public void done(ArrayList<News> list, Exception e) {
                if (showProgressHUD) {
                    progressHUD.dismiss();
                }

                if (e == null) {
                    data = list;
                    adapter.notifyDataSetChanged();
                } else {
                    TJFAProgressHUD.showErrorProgress(NewsActivity.this);
                }

               swipeLayout.setRefreshing(false);
            }
        });
    }

    class NewsCellAdapter extends BaseAdapter {

        private LayoutInflater inflater;

        public NewsCellAdapter(Context context) {
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
                convertView = inflater.inflate(R.layout.news_cell,null);
                holder = new ViewHolder();

                /*得到各个控件的对象*/
                holder.isRead = convertView.findViewById(R.id.is_read);
                holder.title = (TextView) convertView.findViewById(R.id.cell_title);
                holder.preContent = (TextView) convertView.findViewById(R.id.cell_precontent);
                holder.time = (TextView) convertView.findViewById(R.id.cell_time);
                convertView.setTag(holder);//绑定ViewHolder对象
            } else {
                holder = (ViewHolder)convertView.getTag();//取出ViewHolder对象
            }

            if (position == data.size() - 1 && hasMore) {
                loadEarlierData();
            }

            News news = (News)getItem(position);
            /*设置TextView显示的内容，即我们存放在动态数组中的数据*/

            if (news.getIsRead()) {
                holder.isRead.setVisibility(View.INVISIBLE);
            } else {
                holder.isRead.setVisibility(View.VISIBLE);
            }

            holder.title.setText(news.getTitle());
            holder.preContent.setText(news.getPrecontent());

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            holder.time.setText(format.format(news.getDate()));
            return convertView;
        }


        public final class ViewHolder {
            public View isRead;
            public TextView title;
            public TextView preContent;
            public TextView time;
        }
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}

