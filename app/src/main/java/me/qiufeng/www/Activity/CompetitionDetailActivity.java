package me.qiufeng.www.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.umeng.analytics.MobclickAgent;

import me.qiufeng.www.Fragment.GoalFragment;
import me.qiufeng.www.Fragment.MatchFragment;
import me.qiufeng.www.Fragment.RedCardFragment;
import me.qiufeng.www.Fragment.ScoreFragment;
import me.qiufeng.www.Fragment.TeamFragment;
import me.qiufeng.www.Fragment.YellowCardFragment;
import me.qiufeng.www.LogicalLayer.DataModule.LocalModule.Competition;
import me.qiufeng.www.LogicalLayer.DataModule.LocalModule.News;
import me.qiufeng.www.R;
import me.qiufeng.www.ResideMenu.ResideMenu;
import me.qiufeng.www.ResideMenu.ResideMenuItem;

public class CompetitionDetailActivity extends ActionBarActivity implements View.OnClickListener {

    private ResideMenu resideMenu;

    private ResideMenuItem matchItem;
    private ResideMenuItem topGoalItem;
    private ResideMenuItem redCardItem;
    private ResideMenuItem yellowCardItem;
    private ResideMenuItem scoreItem;
    private ResideMenuItem teamItem;

    private Competition competition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition_detail);

        setupMenu();

        competition = (Competition)getIntent().getSerializableExtra("competition");

        if (savedInstanceState == null) {
            changeFragment(MatchFragment.newInstance(this, competition.getCompetitionId()));
            setTitle("比赛");
        }
    }

    private void setupMenu() {
        resideMenu = new ResideMenu(this);
        resideMenu.attachToActivity(this);
        resideMenu.setScaleValue(0.6f);


        matchItem = new ResideMenuItem(this, R.drawable.menu_match, "比 赛");
        topGoalItem = new ResideMenuItem(this, R.drawable.menu_top_list, "射手榜");
        redCardItem = new ResideMenuItem(this, R.drawable.menu_red_card, "红 牌");
        yellowCardItem = new ResideMenuItem(this, R.drawable.menu_yellow_card, "黄 牌");
        scoreItem = new ResideMenuItem(this, R.drawable.menu_group, "积 分");
        teamItem = new ResideMenuItem(this, R.drawable.menu_team, "球 队");

        matchItem.setOnClickListener(this);
        topGoalItem.setOnClickListener(this);
        redCardItem.setOnClickListener(this);
        yellowCardItem.setOnClickListener(this);
        scoreItem.setOnClickListener(this);
        teamItem.setOnClickListener(this);

        resideMenu.addMenuItem(matchItem, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(topGoalItem, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(redCardItem, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(yellowCardItem, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(scoreItem, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(teamItem, ResideMenu.DIRECTION_RIGHT);

        resideMenu.setBackground(R.drawable.detail_bg);
        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_LEFT);
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
            resideMenu.openMenu(ResideMenu.DIRECTION_RIGHT);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View view) {
        if (view == redCardItem) {
            setTitle("红牌");
            changeFragment(RedCardFragment.newInstance(this, competition.getCompetitionId()));
        } else if (view == yellowCardItem) {
            setTitle("黄牌");
            changeFragment(YellowCardFragment.newInstance(this, competition.getCompetitionId()));
        } else if (view == topGoalItem) {
            setTitle("射手榜");
            changeFragment(GoalFragment.newInstance(this, competition.getCompetitionId()));
        } else if (view == scoreItem) {
            setTitle("积分");
            changeFragment(ScoreFragment.newInstance(this, competition.getCompetitionId()));
        } else if (view == teamItem) {
            setTitle("球队");
            changeFragment(TeamFragment.newInstance(this, competition.getCompetitionId()));
        } else if (view == matchItem) {
            setTitle("比赛");
            changeFragment(MatchFragment.newInstance(this, competition.getCompetitionId()));
        }
        resideMenu.closeMenu();
    }

    private void changeFragment(Fragment targetFragment){
        resideMenu.clearIgnoredViewList();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment, targetFragment, "fragment")
                .commit();
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
