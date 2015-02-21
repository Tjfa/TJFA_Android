package me.qiufeng.www.Activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;

import me.qiufeng.www.R;
import me.qiufeng.www.ResideMenu.ResideMenu;
import me.qiufeng.www.ResideMenu.ResideMenuItem;

public class CompetitionDetailActivity extends ActionBarActivity {

    private ResideMenu resideMenu;
    private ResideMenuItem redCardItem;
    private ResideMenuItem yellowCardItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition_detail);

        setupMenu();
    }

    private void setupMenu() {
        resideMenu = new ResideMenu(this);
        resideMenu.attachToActivity(this);
        resideMenu.setScaleValue(0.6f);

        redCardItem = new ResideMenuItem(this, R.drawable.abc_btn_radio_material, "红牌");
        yellowCardItem = new ResideMenuItem(this, R.drawable.abc_ab_share_pack_holo_dark, "黄牌");

        resideMenu.addMenuItem(redCardItem, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(yellowCardItem, ResideMenu.DIRECTION_RIGHT);
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

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }
}
