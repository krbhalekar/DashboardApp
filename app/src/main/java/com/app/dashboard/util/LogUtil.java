package com.app.dashboard.util;

import android.util.Log;

import static com.app.dashboard.util.Constants.LOG_FLAG;

/**
 * Created by khushalb on 23/10/19.
 */

public class LogUtil {

    /*
    *
    * This method is used to show log
    * 1 =   Debug
    * 2 =   Error
    * 3 =   Info
    * 4 =   Verbose
    * 5 =   Warning
    *
    * @author Khushal Bhalekar
     */
    public static void showLog(String tag, String msg, int type) {
        if (LOG_FLAG) {
            switch (type) {
                case 1:
                    Log.d(tag, msg);
                    break;
                case 2:
                    Log.e(tag, msg);
                    break;
                case 3:
                    Log.i(tag, msg);
                    break;
                case 4:
                    Log.v(tag, msg);
                    break;
                case 5:
                    Log.w(tag, msg);
                    break;
            }
        }
    }

}
