package com.app.dashboard.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.app.dashboard.R;
import com.app.dashboard.base.BaseActivity;
import com.app.dashboard.beans.CaseBean;
import com.app.dashboard.beans.ComponentBean;
import com.app.dashboard.file.FileParser;
import com.app.dashboard.listener.FileCallback;
import com.app.dashboard.util.Constants;
import com.app.dashboard.util.LogUtil;

import java.util.List;

import static com.app.dashboard.util.Constants.SPLASH_TIME;
import static com.app.dashboard.util.Constants.TAG;

public class SplashActivity extends BaseActivity implements FileCallback {

    private FileParser fileParser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_splash);

        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initMember() {
        checkRunTimePermission();
    }

    @Override
    protected void initData() {

    }

    private void showSplashTimer() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                new LoadAsync().execute();
            }
        }, SPLASH_TIME);
    }

    @Override
    public void onLoading() {
    }

    @Override
    public void onCompleted(List<ComponentBean> c1, List<CaseBean> c2) {
        if(mApp.getSharedPreference().fetchIntegerPrefernce(Constants.DB_STORE_FLAG,0)==0){
            for(ComponentBean com : c1)
            {
                List<ComponentBean> componentList = ComponentBean.find(ComponentBean.class, "name = ?", com.getName());
                if (componentList.size() > 0) {
                    System.out.println(mApp.getResources().getString(R.string.component_record_exists));
                    LogUtil.showLog(TAG, mApp.getResources().getString(R.string.component_record_exists), 4);
                } else {
                    com.save();
                    System.out.println(mApp.getResources().getString(R.string.component_record_inserted));
                    LogUtil.showLog(TAG, mApp.getResources().getString(R.string.component_record_inserted), 4);
                }
            }

            for(CaseBean c : c2){
                List<CaseBean> caseList = CaseBean.find(CaseBean.class, "short_id = ?", c.getShortId());
                if (caseList.size() > 0) {
                    System.out.println(mApp.getResources().getString(R.string.case_record_exists));
                    LogUtil.showLog(TAG, mApp.getResources().getString(R.string.case_record_exists), 4);
                } else {
                    c.save();
                    System.out.println(mApp.getResources().getString(R.string.case_record_inserted));
                    LogUtil.showLog(TAG, mApp.getResources().getString(R.string.case_record_inserted), 4);
                }
            }
        }
    }

    @Override
    public void onDbStoreCompleted() {
        mApp.getSharedPreference().saveIntegerPrefernce(Constants.DB_STORE_FLAG,1);
    }

    private class LoadAsync extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgress(mApp.getResources().getString(R.string.loading));
        }

        @Override
        protected Void doInBackground(Void... voids) {
            fileParser = new FileParser(SplashActivity.this);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dismissProgress();
            Intent i = new Intent(mApp, HomeActivity.class);
            startActivity(i);
            finish();
        }
    }


    public void checkRunTimePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, Constants.ASK_MULTIPLE_PERMISSION_REQUEST_CODE);
        } else {
            showSplashTimer();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case Constants.ASK_MULTIPLE_PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED ||
                        grantResults.length > 0 && grantResults[1] != PackageManager.PERMISSION_GRANTED) {
                    finish();
                } else {
                    showSplashTimer();
                }

        }
    }
}
