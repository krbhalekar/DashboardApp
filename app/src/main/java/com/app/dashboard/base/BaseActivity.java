package com.app.dashboard.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.app.dashboard.R;
import com.app.dashboard.prefs.BaseSharedPrefs;

/**
 * Created by khushalb on 23/10/19.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected BaseApplication mApp;
    protected BaseSharedPrefs mSharedPrefUtils;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        initBase();
        initView();
        initListener();
        initMember();
        initData();
    }

    private void initBase() {
        mApp = (BaseApplication) getApplication();
        mSharedPrefUtils = mApp.getSharedPreference();
    }

    protected abstract void initView();
    protected abstract void initListener();
    protected abstract void initMember();
    protected abstract void initData();

    public void showProgress(String message) {
        dialog = new ProgressDialog(this);
        if (message == null)
            message = getString(R.string.loading);
        dialog.setMessage(message);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public void dismissProgress() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
