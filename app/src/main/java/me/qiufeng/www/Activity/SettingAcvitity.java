package me.qiufeng.www.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
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
import me.qiufeng.www.Config.Config;
import me.qiufeng.www.LogicalLayer.DataModule.ShareManager;
import me.qiufeng.www.R;

public class SettingAcvitity extends ActionBarActivity implements IWXAPIEventHandler{

    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        api = WXAPIFactory.createWXAPI(this, Config.getWeChatAppId(), false);
        api.handleIntent(getIntent(), this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_acvitity);

        View tell = findViewById(R.id.about_tell_friend);
        tell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder =new AlertDialog.Builder(SettingAcvitity.this);
                builder.setTitle("分享给我的好友");



                builder.setNegativeButton("取消",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.setPositiveButton("朋友圈", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sharedToWeChat();
                    }
                });

//                ShareSDK.
//
//                ShareSDK.initSDK(context);
//                OnekeyShare oks = new OnekeyShare();
//                // 分享时Notification的图标和文字
//                oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
//                oks.setText("我是分享文本");
//                oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/05/21/oESpJ78_533x800.jpg");
//                // 启动分享GUI
//                oks.show(context);
//               // builder.show();
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


    @Override
    public void onResp(BaseResp resp) {
        String result = "";

        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                result = "分享成功";
                Toast.makeText(AppDelegate.getAppContext(), result, Toast.LENGTH_LONG).show();
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                result = "已取消";
                Toast.makeText(AppDelegate.getAppContext(), result, Toast.LENGTH_LONG).show();
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:

                break;
            default:

                break;
        }
    }

    @Override
    public void onReq(BaseReq req) {
        switch (req.getType()) {
            case ConstantsAPI.COMMAND_GETMESSAGE_FROM_WX:

                break;
            case ConstantsAPI.COMMAND_SHOWMESSAGE_FROM_WX:

                break;
            default:
                break;
        }
    }

    public void sharedToWeChat() {

        WXTextObject textObj = new WXTextObject();
        textObj.text = "123";

        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = textObj;
        msg.description = "123";
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("text");
        req.message = msg;
        req.scene = SendMessageToWX.Req.WXSceneTimeline;

        api.sendReq(req);

    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

}
