package com.wizeline.tungphan.wizelinealzheirmersurvey.common;

import android.os.Build;

/**
 * Created by tungphan on 3/23/17.
 */

public class Utils {

    public static boolean isHigherThanLolipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    public static int[] parseIntArrayFromString(String string) {
        string = string.replaceAll("[\\p{Z}\\s]+", "");
        String[] array = string.substring(1, string.length() - 1).split(",");
        int[] result = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = Integer.parseInt(array[i]);
        }
        return result;
    }
}
