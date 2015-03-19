package me.qiufeng.www.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.system.email.Email;
import cn.sharesdk.system.text.ShortMessage;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;
import me.qiufeng.www.Config.AppInfo;
import me.qiufeng.www.Config.Config;
import me.qiufeng.www.LogicalLayer.DataModule.DataManager.DatabaseManager;
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
                ShareSDK.initSDK(SettingAcvitity.this, Config.getShareSDKAppKey());

                HashMap<String, Object> wechatMomentMap = new HashMap<String, Object>();
                wechatMomentMap.put("SortId","1");
                wechatMomentMap.put("AppId", Config.getWechatAppKey());
                wechatMomentMap.put("AppSecret", Config.getWechatAppSecret());
                wechatMomentMap.put("ShareByAppClient","true");
                wechatMomentMap.put("Enable","true");
                ShareSDK.setPlatformDevInfo(WechatMoments.NAME, wechatMomentMap);

                HashMap<String, Object> wechatFriendMap = new HashMap<String, Object>();
                wechatFriendMap.put("SortId","2");
                wechatFriendMap.put("AppId", Config.getWechatAppKey());
                wechatFriendMap.put("AppSecret", Config.getWechatAppSecret());
                wechatFriendMap.put("ShareByAppClient","true");
                wechatFriendMap.put("Enable","true");
                ShareSDK.setPlatformDevInfo(Wechat.NAME,wechatFriendMap);

                HashMap<String, Object> messageMap = new HashMap<String, Object>();
                messageMap.put("SortId","3");
                messageMap.put("ShareByAppClient","true");
                messageMap.put("Enable","true");
                ShareSDK.setPlatformDevInfo(ShortMessage.NAME, messageMap);


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
                ShareSDK.initSDK(SettingAcvitity.this, Config.getShareSDKAppKey());
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

        View deleteData = findViewById(R.id.about_delete_data);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder =new AlertDialog.Builder(SettingAcvitity.this);
                builder.setTitle("童鞋！你要知道你在干嘛!");
                builder.setNegativeButton("我流量多，任性！", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseManager.sharedDatabaseManager().clearAllTable();
                        Toast.makeText(SettingAcvitity.this, "删除成功",Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setPositiveButton("我错了T_T", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
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

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
