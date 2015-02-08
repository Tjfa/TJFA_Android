package me.qiufeng.www.LogicalLayer.DataModule.AVModule;

import android.util.Log;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

/**
 * Created by QiuFeng on 2015/1/3.
 */

@AVClassName("Competition")
public class AVCompetition extends AVObject {

    public void setCompetitionId(int competitionId) {
        put("competitionId",competitionId);
    }

    public int getCompetitionId() {
        return getInt("competitionId");
    }

    public void setName(String name) {
        put("name",name);
    }

    public String getName() {
        return getString("name");
    }

    public void setNumber(int number) {
        put("number", number);
    }

    public int getNumber() {
        return getInt("number");
    }

    public void setTime(String time) {
        put("time",time);
    }

    public String getTime() {
        return getString("time");
    }

    public void setType(int type) {
        put("type",type);
    }

    public int getType() {
        return getInt("type");
    }

    public void setIsStart(int isStart) {
        put("isStart",isStart);
    }

    public int getIsStart() {
        return getInt("isStart");
    }

    public void description() {

    }
}
