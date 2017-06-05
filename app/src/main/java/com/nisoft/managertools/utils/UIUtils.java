package com.nisoft.managertools.utils;

import android.util.DisplayMetrics;

/**
 * Created by NewIdeaSoft on 2017/5/18.
 */

public class UIUtils {
    public static int getPixelsFromDp(int dp){
        DisplayMetrics metrics = new DisplayMetrics();
        return (dp*metrics.densityDpi)/DisplayMetrics.DENSITY_DEFAULT;
    }
}
