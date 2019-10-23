package com.app.dashboard.base;

import android.content.Context;

import com.app.dashboard.prefs.BaseSharedPrefs;
import com.orm.SugarApp;

/**
 * Created by khushalb on 23/10/19.
 */

public class BaseApplication extends SugarApp {

    private static BaseApplication application;
    private BaseSharedPrefs mSharedPreference;

    public static BaseApplication getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        application = this;
        initMembers();
    }

    private void initMembers() {
        mSharedPreference = BaseSharedPrefs.getInstance(this);
    }

    public BaseSharedPrefs getSharedPreference() {
        return mSharedPreference;
    }

    public void setSharedPreference(BaseSharedPrefs sharedPreference) {
        this.mSharedPreference = sharedPreference;
    }

    @Override
    public Context getApplicationContext() {
        return super.getApplicationContext();
    }

    @Override
    public Context getBaseContext() {
        return super.getBaseContext();
    }

}
