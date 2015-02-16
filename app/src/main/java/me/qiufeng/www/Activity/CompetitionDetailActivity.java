package me.qiufeng.www.Activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import me.qiufeng.www.R;
import me.tangke.slidemenu.SlideMenu;

public class CompetitionDetailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Setup the content
        SlideMenu slideMenu = new SlideMenu(this);
        setContentView(slideMenu);
        View contentView = new View(this);
        slideMenu.addView(contentView,new SlideMenu.LayoutParams(SlideMenu.LayoutParams.MATCH_PARENT,
                SlideMenu.LayoutParams.MATCH_PARENT,
                SlideMenu.LayoutParams.ROLE_CONTENT));

        // Setup the primary menu
        View primaryMenu = new View(this);
        slideMenu.addView(primaryMenu, new SlideMenu.LayoutParams(300,
                SlideMenu.LayoutParams.MATCH_PARENT, SlideMenu.LayoutParams.ROLE_PRIMARY_MENU));

        // Setup the secondary menu
        View secondaryMenu = new View(this);
        slideMenu.addView(secondaryMenu, new SlideMenu.LayoutParams(300,
                SlideMenu.LayoutParams.MATCH_PARENT, SlideMenu.LayoutParams.ROLE_SECONDARY_MENU));

        //setContentView(R.layout.activity_competition_detail);
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
