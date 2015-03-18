package me.qiufeng.www.AppDelegate;

import android.app.Application;
import android.content.Context;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import cn.sharesdk.framework.ShareSDK;
import me.qiufeng.www.LogicalLayer.DataModule.AVModule.AVCompetition;
import me.qiufeng.www.LogicalLayer.DataModule.AVModule.AVMatch;
import me.qiufeng.www.Config.Config;
import me.qiufeng.www.LogicalLayer.DataModule.AVModule.AVNews;
import me.qiufeng.www.LogicalLayer.DataModule.AVModule.AVPlayer;
import me.qiufeng.www.LogicalLayer.DataModule.AVModule.AVTeam;

/**
 * Created by QiuFeng on 2015/1/3.
 */
public class AppDelegate extends Application {

    static private Context context = null;

    public void onCreate() {
        super.onCreate();

        AppDelegate.context = getApplicationContext();

        AVOSCloud.useAVCloudCN();
        AVOSCloud.initialize(getApplicationContext(), Config.getAvosCloudAppId(), Config.getAvosCloudAppKey());
        AVObject.registerSubclass(AVCompetition.class);
        AVObject.registerSubclass(AVMatch.class);
        AVObject.registerSubclass(AVTeam.class);
        AVObject.registerSubclass(AVNews.class);
        AVObject.registerSubclass(AVPlayer.class);

    }

    static public Context getAppContext () {
        return AppDelegate.context;
    }

}
