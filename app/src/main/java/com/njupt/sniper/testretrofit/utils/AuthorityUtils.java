package com.njupt.sniper.testretrofit.utils;

import android.content.Context;

import com.njupt.sniper.mylibrary.utils.PrefUtils;
import com.njupt.sniper.testretrofit.MyApplication;
import com.njupt.sniper.testretrofit.entity.OAuthTokenEntity;

/**
 * Created by _SOLID
 * Date:2016/5/19
 * Time:14:49
 */
public class AuthorityUtils {


    private static Context getContext() {
        return MyApplication.getInstance();
    }

    public static boolean isLogin() {
        return PrefUtils.getBoolean(getContext(), "isLogin", false);
    }

    public static void login() {
        setIsLogin(true);
    }

    public static void logout() {
        setIsLogin(false);
    }

    private static void setIsLogin(boolean isLogin) {
        PrefUtils.putBoolean(getContext(), "isLogin", isLogin);
    }

    private static boolean getIsLogin() {
        return PrefUtils.getBoolean(getContext(), "isLogin", false);
    }

    public static void setAuthToken(OAuthTokenEntity token) {
        PrefUtils.setObject(getContext(), "oauthToken", token);
    }

    public static OAuthTokenEntity getAuthToken() {
        return (OAuthTokenEntity) PrefUtils.getObject(getContext(), "oauthToken");
    }

}
