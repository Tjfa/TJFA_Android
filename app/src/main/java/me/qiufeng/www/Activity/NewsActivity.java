package me.qiufeng.www.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import me.qiufeng.www.LogicalLayer.DataModule.DataManager.NewsManager;
import me.qiufeng.www.LogicalLayer.DataModule.LocalModule.News;
import me.qiufeng.www.R;

public class NewsActivity extends ActionBarActivity {

    private ListView listView;
    private NewsCellAdapter adapter;
    ArrayList<News> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        listView = (ListView)findViewById(R.id.list_view);
        adapter = new NewsCellAdapter(this);

        data = NewsManager.sharedNewsManager.getAllNewsFromDatabase();
        NewsManager.sharedNewsManager().description(data);

        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("click at item","" + position);

                Intent intent = new Intent(NewsActivity.this, NewsDetailActivity.class);
                Bundle bundle = new Bundle();
                News news = data.get(position);
                NewsManager.sharedNewsManager().updateNewsToRead(news);
                bundle.putSerializable("news", news);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
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

            Log.v("MyListViewBase", "getView " + position + " " + convertView);

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.news_cell,null);
                holder = new ViewHolder();
                    /*得到各个控件的对象*/
                holder.title = (TextView) convertView.findViewById(R.id.cell_title);
                holder.preContent = (TextView) convertView.findViewById(R.id.cell_precontent);
                holder.time = (TextView) convertView.findViewById(R.id.cell_time);
                convertView.setTag(holder);//绑定ViewHolder对象
            } else {
                holder = (ViewHolder)convertView.getTag();//取出ViewHolder对象
            }


            News news = (News)getItem(position);

            /*设置TextView显示的内容，即我们存放在动态数组中的数据*/
            holder.title.setText(news.getTitle());
            holder.preContent.setText(news.getPrecontent());

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            holder.time.setText(format.format(news.getDate()));
           // holder.time.setText(news.getNewsId());

            return convertView;
        }


        public final class ViewHolder {
            public TextView title;
            public TextView preContent;
            public TextView time;
        }
    }
}

