package com.njupt.sniper.testretrofit.entity;

import java.io.Serializable;

/**
 * author：Zsl
 * date：2016/8/24
 */
public class OAuthTokenEntity implements Serializable{
    public String access_token;
    public String token_type;
    public String refresh_token;
    public String expires_in;
    public String scope;
}
