package me.qiufeng.www.Activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import me.qiufeng.www.LogicalLayer.DataModule.DataManager.NewsManager;
import me.qiufeng.www.LogicalLayer.DataModule.LocalModule.News;
import me.qiufeng.www.R;

public class NewsActivity extends ActionBarActivity {

    private ListView listView;
    ArrayList<HashMap<String, String>> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        ArrayList<News> newsData = NewsManager.sharedNewsManager().getAllNewsFromDatabase();

        NewsManager.sharedNewsManager().description(newsData);

        for (int i = 0; i < newsData.size(); i++) {
            HashMap<String, String> map = new HashMap<>();
            News news = newsData.get(i);
            map.put("a",news.getTitle());
            map.put("b",news.getPrecontent());
            map.put("c",news.getTitle());
            data.add(map);
        }

        listView = (ListView)findViewById(R.id.list_view);

        SimpleAdapter adapter = new SimpleAdapter(this,data,R.layout.news_cell,new String[] {"a","b","c"},
                new int[] {R.id.cell_title,R.id.cell_precontent,R.id.cell_time});
        listView.setAdapter(adapter);
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
}
