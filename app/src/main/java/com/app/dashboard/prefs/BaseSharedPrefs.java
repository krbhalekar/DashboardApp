package com.app.dashboard.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.app.dashboard.util.EncryptionUtil;

/**
 * Created by khushalb on 23/10/19.
 */

public class BaseSharedPrefs {
    private static final String DEVICE_ID = "fcm_token";
    private final static String PREF = "PREF";
    private static BaseSharedPrefs baseSharedPrefs;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private Context mContext;

    private BaseSharedPrefs(Context context) {
        mContext = context;
        sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPref.edit();
    }

    private BaseSharedPrefs() {

    }

    public static BaseSharedPrefs getInstance(Context context) {
        if (baseSharedPrefs == null) {
            baseSharedPrefs = new BaseSharedPrefs(context);
        }
        return baseSharedPrefs;

    }

    public static void saveFCMToken(Context context, String token) {
        SharedPreferences sp = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        ed.putString(DEVICE_ID, token);
        ed.commit();
    }

    public static String getFCMToken(Context context) {
        SharedPreferences sp = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        return sp.getString(DEVICE_ID, null);
    }

    // for fetching data from shared pref.
    public String fetchStringPrefernce(String key, String defaultValue) {

        String value = sharedPref.getString(key, "");
        return TextUtils.isEmpty(value) ? defaultValue : EncryptionUtil.getInstace(
                mContext).decrypt(
                value,
                EncryptionUtil.getInstace(mContext).generateUniqueKey());
    }

    // for save data in to shared perf.
    public void saveStringPrefernce(String key, String value) {
        value = EncryptionUtil.getInstace(mContext).encrypt(
                value,
                EncryptionUtil.getInstace(mContext).generateUniqueKey());

        editor.putString(key, value);
        editor.commit();

    }

    // for fetching data from shared pref.
    public boolean fetchBooleanPrefernce(String key, Boolean defaultValue) {

        return sharedPref.getBoolean(key, defaultValue);
    }

    // for save data in to shared perf.
    public void saveBooleanPrefernce(String key, Boolean value) {

        editor.putBoolean(key, value);
        editor.commit();

    }

    // for save long data in to shared perf.
    public void saveLongPrefernce(String key, long value) {
        editor.putLong(key, value);
        editor.commit();

    }

    public long fetchLongPrefernce(String key, long defaultValue) {
        return sharedPref.getLong(key, defaultValue);
    }

    // for fetching data from shared pref.
    public int fetchIntegerPrefernce(String key, int defaultValue) {
        return sharedPref.getInt(key, defaultValue);
    }

    // for save data in to shared perf.
    public void saveIntegerPrefernce(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public void removeFromSharedPreference(String key) {
        editor.remove(key);
        editor.commit();
    }

    public void clearAllData() {
        editor = sharedPref.edit().clear();
        editor.commit();
    }

}