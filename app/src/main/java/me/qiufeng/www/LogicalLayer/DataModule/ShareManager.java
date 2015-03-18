package me.qiufeng.www.LogicalLayer.DataModule;

import android.widget.Toast;

import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import me.qiufeng.www.AppDelegate.AppDelegate;
import me.qiufeng.www.Config.AppInfo;
import me.qiufeng.www.Config.Config;

/**
 * Created by QiuFeng on 3/18/15.
 */
public class ShareManager
{
    private IWXAPI api;
    static ShareManager manager = null;

    public static ShareManager sharedManager() {
        if (manager == null) {
            manager = new ShareManager();
        }
        return manager;
    }




}
