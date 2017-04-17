package com.wizeline.tungphan.wizelinealzheirmersurvey.common;

import android.os.Build;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tungphan on 3/23/17.
 */

public class Utils {

    public static boolean isHigherThanLolipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    public static List<Integer> parseListIntFromString(String string) {
        string = string.replaceAll("[\\p{Z}\\s]+", "");
        String[] array = string.substring(1, string.length() - 1).split(",");
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            result.add(Integer.parseInt(array[i]));
        }
        return result;
    }
}
