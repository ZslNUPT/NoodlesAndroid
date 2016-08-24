package com.njupt.sniper.testretrofit.http;


import com.njupt.sniper.testretrofit.entity.GankBeautyResult;
import com.njupt.sniper.testretrofit.entity.HttpResult;
import com.njupt.sniper.testretrofit.entity.StaticesEntity;
import com.njupt.sniper.testretrofit.entity.Subject;
import com.njupt.sniper.testretrofit.entity.Token;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * author：Zsl
 * date：2016/8/23
 */
public interface MovieService {

    @GET("top250")
    Observable<HttpResult<List<Subject>>> getTopMovie(@Query("start") int start, @Query("count") int count);

    @GET("data/福利/{number}/{page}")
    Observable<GankBeautyResult> getBeauties(@Path("number") int number, @Path("page") int page);

    @FormUrlEncoded
    @POST("oauth/token")
    Observable<Token> getToken(@Field("client_id") String client_id, @Field("client_secret") String client_secret,
                               @Field("username") String username, @Field("password") String password, @Field("grant_type") String grant_type);

    @GET("api/homes/statistics")
    Observable<StaticesEntity> getStatics(@Header("Authorization") String token);
}
