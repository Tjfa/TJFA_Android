package me.qiufeng.www.Activity;

import android.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import me.qiufeng.www.LogicalLayer.DataModule.LocalModule.News;
import me.qiufeng.www.R;

public class NewsDetailActivity extends ActionBarActivity {

    private News news;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);


        news = (News)getIntent().getSerializableExtra("news");

       // ActionBar actionBar = getActionBar();
       // actionBar.setTitle(news.getTitle());

        setTitle(news.getTitle());

        webView = (WebView)findViewById(R.id.webview);
        webView.loadDataWithBaseURL(null,news.getContent(),"text/html", "utf-8",null);



        Log.i("",news.getTitle());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_news_detail, menu);
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
