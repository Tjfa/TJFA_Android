package me.qiufeng.www.Config;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import me.qiufeng.www.AppDelegate.AppDelegate;

/**
 * Created by QiuFeng on 3/18/15.
 */
public class AppInfo {
    static public String sharedMessage() {
        return "hi~~我亲爱的小伙伴～～我发现了关于同济足球的一个很棒的App,叫做" + getApplicationName() + "现在已经升级到%@版本了,快去看看吧～～";
    }

    private static String getApplicationName() {
        PackageManager packageManager = null;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = AppDelegate.getAppContext().getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(AppDelegate.getAppContext().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            applicationInfo = null;
        }
        String applicationName =
                (String) packageManager.getApplicationLabel(applicationInfo);
        return applicationName;
    }
}
