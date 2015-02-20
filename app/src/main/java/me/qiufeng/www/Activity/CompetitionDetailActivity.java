package me.qiufeng.www.Activity;

import android.app.ActivityManager;
import android.app.LocalActivityManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import me.qiufeng.www.R;
import me.tangke.slidemenu.SlideMenu;

public class CompetitionDetailActivity extends ActionBarActivity {

    public SlideMenu slideMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition_detail);
        slideMenu = (SlideMenu) findViewById(R.id.slideMenu);

        setSlideRole(R.layout.activity_red_card);
       // setSlideRole(R.layout.layout_primary_menu);
    }

    public void setSlideRole(int res) {
        if (null == slideMenu) {
            return;
        }
        getLayoutInflater().inflate(res,slideMenu,true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_competition_detail, menu);
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
