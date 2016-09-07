package com.njupt.sniper.app.model.service;

import com.njupt.sniper.app.model.entity.StaticsEntity;

import retrofit2.http.GET;
import rx.Observable;

/**
 * author：Zsl
 * date：2016/8/25
 */
public interface NavigationService{
    @GET("api/homes/statistics")
    Observable<StaticsEntity> getStatics();

}
