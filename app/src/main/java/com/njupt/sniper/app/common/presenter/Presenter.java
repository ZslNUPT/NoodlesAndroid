package com.njupt.sniper.app.common.presenter;

/**
 * Created by Arron on 16/7/26.
 */
public interface Presenter {
    void inject();

    void resume();

    void pause();

    void destroy();

    void requestData(Object... o);

}
