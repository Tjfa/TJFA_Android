package me.qiufeng.www.Activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.system.email.Email;
import me.qiufeng.www.Config.AppInfo;
import me.qiufeng.www.LogicalLayer.DataModule.ShareManager;
import me.qiufeng.www.R;

public class SettingAcvitity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_acvitity);

        View tell = findViewById(R.id.about_tell_friend);
        tell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareSDK.initSDK(SettingAcvitity.this);
                OnekeyShare oks = new OnekeyShare();
                oks.setNotification(R.drawable.about_logo, "同济足协App分享");
                oks.setText(AppInfo.sharedMessage());
                oks.show(SettingAcvitity.this);
            }
        });

        View contact = findViewById(R.id.about_contact);
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareSDK.initSDK(SettingAcvitity.this);
                Platform.ShareParams sp = new Platform.ShareParams();
                String text = AppInfo.deviceInfo();
                text += "          如果收信人没有自动填上，请您输入 tongjizuxie@gmail.com        请在下方填入您对我们的建议:－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－";
                sp.setText(text);
                sp.setAddress("tongjizuxie@gmail.com");
                Platform mail = ShareSDK.getPlatform(Email.NAME);
                mail.setPlatformActionListener(null);
                mail.share(sp);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_setting_acvitity, menu);
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
