package com.njupt.sniper.app.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * author：Zsl
 * date：2016/9/6
 */
public class IntentStartUtils {
    /**
     * startActivity
     *
     * @param clazz
     */
    protected static void readyGo(Activity activity,Class<?> clazz) {
        Intent intent = new Intent(activity, clazz);
        activity.startActivity(intent);
    }

    /**
     * startActivity with bundle
     *
     * @param clazz
     * @param bundle
     */
    protected static void readyGo(Activity activity,Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(activity, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        activity.startActivity(intent);
    }

    /**
     * startActivity then finish
     *
     * @param clazz
     */
    protected static void readyGoThenKill(Activity activity,Class<?> clazz) {
        Intent intent = new Intent(activity, clazz);
        activity.startActivity(intent);
        activity.finish();
    }

    /**
     * startActivity with bundle then finish
     *
     * @param clazz
     * @param bundle
     */
    protected static void readyGoThenKill(Activity activity,Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(activity, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        activity.startActivity(intent);
        activity.finish();
    }

    /**
     * startActivityForResult
     *
     * @param clazz
     * @param requestCode
     */
    protected static void readyGoForResult(Activity activity,Class<?> clazz, int requestCode) {
        Intent intent = new Intent(activity, clazz);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * startActivityForResult with bundle
     *
     * @param clazz
     * @param requestCode
     * @param bundle
     */
    protected static void readyGoForResult(Activity activity,Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(activity, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        activity.startActivityForResult(intent, requestCode);
    }
}
