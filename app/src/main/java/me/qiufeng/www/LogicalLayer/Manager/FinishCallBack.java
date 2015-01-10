package me.qiufeng.www.LogicalLayer.Manager;

import java.util.ArrayList;

/**
 * Created by QiuFeng on 2015/1/10.
 */
public abstract class FinishCallBack <T>  {
    public void FindCallback() { /* compiled code */ }

    public abstract void done(ArrayList<T> list, Exception e);

    final void internalDone0(ArrayList<T> returnValue, Exception e) { /* compiled code */ }
}
