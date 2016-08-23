package com.njupt.sniper.testretrofit.http;


import com.njupt.sniper.testretrofit.entity.HttpResult;
import com.njupt.sniper.testretrofit.entity.Subject;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * author：Zsl
 * date：2016/8/23
 */
public interface MovieService {

    @GET("top250")
    Observable<HttpResult<List<Subject>>> getTopMovie(@Query("start") int start, @Query("count") int count);

}
