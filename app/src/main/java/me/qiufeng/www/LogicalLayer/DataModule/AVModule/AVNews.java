package me.qiufeng.www.LogicalLayer.DataModule.AVModule;

import android.util.Log;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

import java.util.Date;

/**
 * Created by QiuFeng on 2/5/15.
 */

@AVClassName("News")
public class AVNews extends AVObject {

    public void setContent(String content) {
        put("content", content);
    }

    public String getContent() {
        return getString("content");
    }

    public void setDate(Date date) {
        put("date",date);
    }

    public Date getDate() {
        return getDate("date");
    }

    public void setNewsId(int newsId) {
        put("newsId",newsId);
    }

    public int getNewsId() {
        return getInt("newsId");
    }

    public void setPrecontent(String precontent) {
        put("precontent",precontent);
    }

    public String getPrecontent() {
        return getString("precontent");
    }

    public void setTitle(String title) {
        put("title",title);
    }

    public String getTitle() {
        return getString("title");
    }

    public void description() {
        Log.i("",getNewsId() + ":" + getTitle());
    }
}
