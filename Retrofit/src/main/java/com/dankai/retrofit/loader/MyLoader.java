package com.dankai.retrofit.loader;
/*
 *  项目名:    Practice
 *  包名：     com.dankai.loader
 *  文件名:    MyLoader
 *  创建者:    WK
 *  时间：     2019/8/21 11:08
 *  描述：     显示Loader和停止Loader
 */

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatDialog;

import com.dankai.retrofit.R;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

public class MyLoader {

    // Loader根据屏幕的大小缩小为原来的1/8
    private static final int LOADER_SIZE_SCALE = 8;
    // 根据屏幕的上下有一个偏移量，为10
    private static final int LOADER_OFFSET_SCALE = 10;

    private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();

    // 默认的Loader
    private static final LoaderStyleEnum DEFAULT_LOADER = LoaderStyleEnum.BALL_CLIP_ROTATE_PULSE_INDICATOR;

    public static void showLoading(Context context, Enum<LoaderStyleEnum> type, @ColorInt int color) {
        //设置Dialog的Style，在style.xml添加dialog的Style
        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);

        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(type.name(), context);
        //设置加载器的颜色
        avLoadingIndicatorView.setIndicatorColor(color);
        dialog.setContentView(avLoadingIndicatorView);

        int deviceWidth = getScreenWidth(context);
        int deviceHeight = getScreenHeight(context);

        final Window dialogWindow = dialog.getWindow();

        if (dialogWindow != null) {
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = deviceWidth / LOADER_SIZE_SCALE;
            lp.height = deviceHeight / LOADER_SIZE_SCALE;
            lp.height = lp.height + deviceHeight / LOADER_OFFSET_SCALE;
            lp.gravity = Gravity.CENTER;
        }
        //不可手动取消
        dialog.setCancelable(false);
        LOADERS.add(dialog);
        //默认显示dialog
        dialog.show();
    }

    /**
     * 重载
     *
     * @param type Loader的类型
     */
    public static void showLoading(Context context, Enum<LoaderStyleEnum> type) {
        showLoading(context, type, Color.WHITE);
    }

    /**
     * 重载
     *
     * @param color Loader的颜色
     */
    public static void showLoading(Context context, @ColorInt int color) {
        //设置默认Loader类型
        showLoading(context, DEFAULT_LOADER, color);
    }

    /**
     * 重载，加载默认Loader
     */
    public static void showLoading(Context context) {
        //默认颜色为白色
        showLoading(context, DEFAULT_LOADER, Color.WHITE);
    }

    /**
     * 停止Dialog
     */
    public static void stopLoading() {
        for (AppCompatDialog dialog : LOADERS) {
            if (dialog != null) {
                if (dialog.isShowing()) {
                    dialog.cancel();
                }
            }
        }
    }

    /**
     * 获取屏幕宽度
     */
    private static int getScreenWidth(Context context) {
        final Resources resources = context.getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    /**
     * 获取屏幕高度
     */
    private static int getScreenHeight(Context context) {
        final Resources resources = context.getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}