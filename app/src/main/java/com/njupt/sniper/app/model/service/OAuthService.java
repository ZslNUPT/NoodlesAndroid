package com.njupt.sniper.app.model.service;


import com.njupt.sniper.app.model.entity.OAuthTokenEntity;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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
                                       @Field("grant_type") String grant_type,@Field("refresh_token") String refresh_token);


}
