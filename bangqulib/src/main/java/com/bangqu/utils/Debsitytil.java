package com.bangqu.utils;

import android.content.Context;

/**
 * Created by coach on 16/9/22.
 */
public class Debsitytil {
    /**
     * 根据手机的分辨率从dp单位转换成px
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从pxp单位转换成dp
     */
    public static int px2pdip(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue / scale + 0.5f);
    }
}
