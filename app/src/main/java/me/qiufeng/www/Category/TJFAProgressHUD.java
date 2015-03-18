package me.qiufeng.www.Category;

import android.content.Context;
import android.os.Looper;
import android.widget.TabHost;
import android.widget.Toast;

import com.walnutlabs.android.ProgressHUD;

/**
 * Created by QiuFeng on 2/11/15.
 */
public class TJFAProgressHUD {
    static ProgressHUD loadingProgress;
//     static ProgressHUD errorProgress;



//    public static void showErrorProgress(Context context, String message) {
//        errorProgress = ProgressHUD.show(context, message, true, false, null);
//
//        new Thread(new Runnable(){
//            public void run(){
//                try {
//                    Thread.sleep(1500);
//                } catch (Exception e) {
//
//                }
//                finally {
//                   errorProgress.dismiss();
//                }
//            }
//        }).start();
//    }

//    public static void showErrorProgress(Context context) {
//        showErrorProgress(context,"网络错误");
//    }

    public static void showErrorProgress(Context context, String message) {
        Toast.makeText(context, message ,Toast.LENGTH_SHORT).show();
    }

    public static void showErrorProgress(Context context) {
        showErrorProgress(context,"网络错误");
    }


    public static ProgressHUD showLoadingProgress(Context context, String message) {
        loadingProgress = ProgressHUD.show(context, message, true, false, null);
        return loadingProgress;
    }

    public static ProgressHUD showLoadingProgress(Context context) {
        return showLoadingProgress(context, "载入中..");
    }
}
