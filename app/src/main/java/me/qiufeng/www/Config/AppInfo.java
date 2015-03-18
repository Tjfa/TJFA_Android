package me.qiufeng.www.Config;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.util.HashMap;

import me.qiufeng.www.AppDelegate.AppDelegate;

/**
 * Created by QiuFeng on 3/18/15.
 */
public class AppInfo {
    static public String sharedMessage() {
        return "hi~~我亲爱的小伙伴～～我发现了关于同济足球的一个很棒的App,叫做" + getApplicationName() + "现在已经升级到%@版本了,快去看看吧～～";
    }

    private static String getApplicationName() {
        PackageManager packageManager = getPackageManager();
        ApplicationInfo applicationInfo = getApplicationInfo();
        String applicationName =
                (String) packageManager.getApplicationLabel(applicationInfo);
        return applicationName;
    }

    private static Context getContext() {
        return AppDelegate.getAppContext();
    }

    private static PackageManager getPackageManager() {
        return getContext().getPackageManager();
    }

    private static String getPackageName() {
        return getContext().getPackageName();
    }

    private static  ApplicationInfo getApplicationInfo() {
        try {
            return getPackageManager().getApplicationInfo(getContext().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static  String getVersion() {
        try {
            return  getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return "";
        }
    }

    public static String deviceInfo() {
        Build bd = new Build();
        HashMap<String, String> map= new HashMap<>();
        map.put("model",bd.MODEL);
        map.put("version", getVersion());
        return map.toString();
    }


}
