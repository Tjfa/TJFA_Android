package me.qiufeng.www.Category;

import android.content.Context;
import android.os.Looper;

import com.walnutlabs.android.ProgressHUD;

/**
 * Created by QiuFeng on 2/11/15.
 */
public class TJFAProgressHUD {
    static ProgressHUD loadingProgress;
    static ProgressHUD errorProgress;


    public static void showErrorProgress(Context context) {
        errorProgress = ProgressHUD.show(context, "网络错误", true, false, null);

        new Thread(new Runnable(){
            public void run(){
                try {
                    Thread.sleep(1500);
                } catch (Exception e) {

                }
                finally {
                   errorProgress.dismiss();
                }
            }
        }).start();


    }
}
