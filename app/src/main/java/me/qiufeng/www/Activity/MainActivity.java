package me.qiufeng.www.Activity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import me.qiufeng.www.LogicalLayer.DataModule.DataManager.FinishCallBack;
import me.qiufeng.www.LogicalLayer.DataModule.DataManager.MatchManager;
import me.qiufeng.www.LogicalLayer.DataModule.DataManager.PlayerManager;
import me.qiufeng.www.LogicalLayer.DataModule.DataManager.TeamManager;
import me.qiufeng.www.LogicalLayer.DataModule.LocalModule.Match;
import me.qiufeng.www.LogicalLayer.DataModule.LocalModule.Player;
import me.qiufeng.www.LogicalLayer.DataModule.LocalModule.Team;
import me.qiufeng.www.R;


public class MainActivity extends ActionBarActivity {

    private void jumpToBenbu() {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("type", 1);
        intent.putExtras(bundle);
        intent.setClass(MainActivity.this,CompetitionActivity.class);
        startActivity(intent);
    }

    private void jumpToJiading() {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("type", 2);
        intent.putExtras(bundle);
        intent.setClass(MainActivity.this,CompetitionActivity.class);
        startActivity(intent);
    }

    private void jumpToNews() {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this,NewsActivity.class);
        startActivity(intent);
    }

    private void jumpToAbout() {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this,SettingAcvitity.class);
        startActivity(intent);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button newsImageButton = (Button)findViewById(R.id.competition_news_image);
        newsImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpToNews();
            }
        });

        Button newsTitleButton = (Button)findViewById(R.id.competition_news_title);
        newsTitleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpToNews();
            }
        });

        Button aboutTitleButton = (Button)findViewById(R.id.competition_about_title);
        aboutTitleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpToAbout();
            }
        });

        Button aboutImageButton = (Button)findViewById(R.id.competition_about_image);
        aboutImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpToAbout();
            }
        });

        Button jiadingTitleButton = (Button)findViewById(R.id.competition_jiading_title);
        jiadingTitleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpToJiading();
            }
        });

        Button jiadingImageButton = (Button)findViewById(R.id.competition_jiading_image);
        jiadingImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpToJiading();
            }
        });

        Button benbuTitleButton = (Button)findViewById(R.id.competition_benbu_title);
        benbuTitleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpToBenbu();
            }
        });

        Button benbuImageButton = (Button)findViewById(R.id.competition_benbu_image);
        benbuImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpToBenbu();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
