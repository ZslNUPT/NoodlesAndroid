package com.njupt.sniper.testretrofit.http;

import com.njupt.sniper.testretrofit.entity.StaticsEntity;

import retrofit2.http.GET;
import rx.Observable;

/**
 * author：Zsl
 * date：2016/8/25
 */
public interface TestService {
    @GET("api/homes/statistics")
    Observable<StaticsEntity> getStatics();
}
