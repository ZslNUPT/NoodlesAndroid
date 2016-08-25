package com.njupt.sniper.testretrofit.http;


import com.njupt.sniper.testretrofit.entity.StaticsEntity;
import com.njupt.sniper.testretrofit.entity.OAuthTokenEntity;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

/**
 * author：Zsl
 * date：2016/8/23
 */
public interface OAuthService {

    @FormUrlEncoded
    @POST("oauth/token")
    Observable<OAuthTokenEntity> getTokenByPassword(@Field("client_id") String client_id, @Field("client_secret") String client_secret,
                                                    @Field("username") String username, @Field("password") String password, @Field("grant_type") String grant_type);

    @FormUrlEncoded
    @POST("oauth/token")
    Observable<OAuthTokenEntity> getTokenByRefreshToken(@Field("client_id") String client_id, @Field("client_secret") String client_secret,
                                          @Field("username") String username, @Field("password") String password, @Field("grant_type") String grant_type,@Field("refresh_token") String refresh_token);


}
