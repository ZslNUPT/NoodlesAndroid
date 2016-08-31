package com.njupt.sniper.app.utils;

import android.content.Context;

import com.njupt.sniper.app.model.entity.OAuthTokenEntity;
import com.njupt.sniper.mylibrary.utils.PrefUtils;
import com.njupt.sniper.app.MyApplication;

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

    public static void setIsLogin(boolean isLogin) {
        PrefUtils.putBoolean(getContext(), "isLogin", isLogin);
    }

    public static boolean getIsLogin() {
        return PrefUtils.getBoolean(getContext(), "isLogin", false);
    }

    public static void setAuthToken(OAuthTokenEntity token) {
        PrefUtils.setObject(getContext(), "oauthToken", token);
    }

    public static OAuthTokenEntity getAuthToken() {
        OAuthTokenEntity authTokenEntity = (OAuthTokenEntity) PrefUtils.getObject(getContext(), "oauthToken");
        return  authTokenEntity==null?new OAuthTokenEntity():authTokenEntity;
    }

}
