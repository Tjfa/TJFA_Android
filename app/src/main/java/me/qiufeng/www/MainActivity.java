package me.qiufeng.www;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;

import java.util.List;

import me.qiufeng.www.LogicalLayer.DataModule.AVModule.AVCompetition;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AVQuery<AVCompetition> query = AVObject.getQuery(AVCompetition.class);
        query.whereEqualTo("type", 1);
        query.findInBackground(new FindCallback<AVCompetition>() {
            @Override
            public void done(List<AVCompetition> avQueries, AVException e) {
                if (e==null) {
                    for (AVCompetition competition : avQueries) {
                        Log.i("","b");
                    }
                } else {
                    e.getMessage();
                }
            }
        });


        setContentView(R.layout.activity_main);
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
