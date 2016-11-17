package com.it5.facebookdemo;

import android.app.Application;

/**
 * Created by IT5 on 2016/11/17.
 */

public class MApplication extends Application{
    private static MApplication mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this;
    }
    public static MApplication getContext() {
        return mContext;
    }
}
