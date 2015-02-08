package me.qiufeng.www.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import me.qiufeng.www.R;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        NewsManager.sharedNewsManager().getNewsesFromNetwork(10,new FinishCallBack<News>() {
//            @Override
//            public void done(ArrayList<News> list, Exception e) {
//                if (e == null) {
//                    NewsManager.sharedNewsManager().description(list);
//                } else {
//                   Log.e("network error",e.getMessage());
//                }
//            }
//        });
//        List<News> list = NewsManager.sharedNewsManager().getAllNewsFromDatabase();
//        NewsManager.sharedNewsManager().description(list);

        setContentView(R.layout.activity_main);

        Button newsButton = (Button)findViewById(R.id.news_button);
        newsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,NewsActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
