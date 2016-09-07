package com.njupt.sniper.app.di.module;

import com.njupt.sniper.app.MyApplication;
import com.njupt.sniper.app.common.property.AssetsProperties;
import com.njupt.sniper.app.common.property.Property;

import javax.inject.Inject;

import dagger.Module;

@Module
public class AccountModule extends AssetsProperties {

    @Inject
    public AccountModule(MyApplication myApplication) {
        super(myApplication, "account_config");
    }

    @Property("client_id")
    String clientId;

    @Property("client_secret")
    String clientSecret;

    @Property("grant_type_password")
    String grantTypePassword;

    @Property("grant_type_refreshToken")
    String grantTypeRefreshToken;

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getGrantTypePassword() {
        return grantTypePassword;
    }

    public String getGrantTypeRefreshToken() {
        return grantTypeRefreshToken;
    }
}
