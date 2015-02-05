package me.qiufeng.www.AppDelegate;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;

import me.qiufeng.www.LogicalLayer.DataModule.AVModule.AVCompetition;
import me.qiufeng.www.LogicalLayer.DataModule.AVModule.AVMatch;
import me.qiufeng.www.Config.Config;
import me.qiufeng.www.LogicalLayer.DataModule.AVModule.AVNews;

/**
 * Created by QiuFeng on 2015/1/3.
 */
public class AppDelegate extends Application {
    public void onCreate() {
        super.onCreate();
        AVOSCloud.useAVCloudCN();
        AVOSCloud.initialize(getApplicationContext(),Config.getAvosCloudAppId(),Config.getAvosCloudAppKey());
        AVObject.registerSubclass(AVCompetition.class);
        AVObject.registerSubclass(AVMatch.class);
        AVObject.registerSubclass(AVNews.class);
    }
}
