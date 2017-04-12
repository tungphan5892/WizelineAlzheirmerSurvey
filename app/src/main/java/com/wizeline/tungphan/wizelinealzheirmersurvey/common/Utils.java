package com.wizeline.tungphan.wizelinealzheirmersurvey.common;

import android.os.Build;

/**
 * Created by tungphan on 3/23/17.
 */

public class Utils {

    public static boolean isHigherThanLolipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }
}
