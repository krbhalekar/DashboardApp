package com.app.dashboard.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.Log;

import java.net.HttpURLConnection;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Khushal on 8/3/2018.
 */

public class EncryptionUtil {
    private static final String TAG = EncryptionUtil.class.getSimpleName();
    private final static String DATE_FORMAT = "EEE, d MMM yyyy HH:mm:ss z";
    private final static String HMAC_SHA256_ALGORITHM = "HmacSHA256";
    private final static String SECRET = "7a4fbeb4b338484e966c6302db31a2e7";
    private static EncryptionUtil cypher;
    private Context mContext;


    private EncryptionUtil() {
    }

    private EncryptionUtil(Context context) {
        this.mContext = context;
    }

    public static EncryptionUtil getInstace(Context context) {

        if (cypher == null) {
            cypher = new EncryptionUtil(context);
        }
        return cypher;
    }

    public String encrypt(String text, byte[] key) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] keyBytes = new byte[16];
            int len = key.length;
            if (len > keyBytes.length)
                len = keyBytes.length;
            System.arraycopy(key, 0, keyBytes, 0, len);
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            byte[] results = cipher.doFinal(text.getBytes("UTF-8"));
            return Base64.encodeToString(results, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String decrypt(String text, byte[] key) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] keyBytes = new byte[16];
            int len = key.length;
            if (len > keyBytes.length)
                len = keyBytes.length;
            System.arraycopy(key, 0, keyBytes, 0, len);
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

            byte[] results = cipher
                    .doFinal(Base64.decode(text, Base64.DEFAULT));
            return new String(results, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public byte[] generateUniqueKey() {

        String androidID = Settings.Secure.getString(mContext.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        @SuppressLint("MissingPermission") String deviceId = tm.getDeviceId();
        StringBuilder combineString = new StringBuilder();
        combineString.append(androidID);
        combineString.append(deviceId);
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        messageDigest.update(combineString.reverse().toString().getBytes());
        String encryptedString = new String(messageDigest.digest());
        return encryptedString.toString().getBytes();
    }

    public String calculateHMAC(String url, String object, HttpURLConnection httpConn) {

        try {
//			String verb = httpConn.getRequestMethod();
//			String contentMd5 = calculateMD5(object);
//			String currentDate = new SimpleDateFormat(DATE_FORMAT).format(new Date());
//			String data = verb + "\n" + contentMd5 + "\n" +object+"\n"+ currentDate + "\n"
//					+ url;

            SecretKeySpec signingKey = new SecretKeySpec(SECRET.getBytes(), HMAC_SHA256_ALGORITHM);
            Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(object.getBytes());
            String result = new String(Base64.encode(rawHmac, Base64.DEFAULT));

            httpConn.setRequestProperty("hmac", result);
//			httpConn.setRequestProperty("Date", currentDate);
//			httpConn.setRequestProperty("Content-Md5", contentMd5);

            return result;
        } catch (GeneralSecurityException e) {
            Log.e(TAG, "Unexpected error while creating hash: " + e.getMessage());
            throw new IllegalArgumentException();
        }
    }


    public String calculateMD5(String contentToEncode) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        digest.update(contentToEncode.getBytes());
        String result = new String(Base64.encode(digest.digest(), Base64.DEFAULT));
        return result;
    }
}
