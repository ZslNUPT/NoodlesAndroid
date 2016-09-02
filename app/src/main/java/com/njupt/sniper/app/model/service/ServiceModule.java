package com.njupt.sniper.app.model.service;

import com.njupt.sniper.app.model.http.ObjectMapperBuilder;
import com.njupt.sniper.app.utils.AuthorityUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * author：Zsl
 * date：2016/8/25
 */

@Module
public class ServiceModule {

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient okHttpClient, String baseUrl) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create(ObjectMapperBuilder.build()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient);
        return builder.build();
    }

    @Provides
    public String getApiBaseUrl() {
        return "http://192.168.1.7:8315/";
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {

                Request original = chain.request();

                Request.Builder requestBuilder = original.newBuilder()
                        .header("Authorization", "Bearer " + AuthorityUtils.getAuthToken().access_token)
                        .method(original.method(), original.body());

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        httpClient.connectTimeout(60 * 1000, TimeUnit.MILLISECONDS);
        httpClient.readTimeout(60 * 1000, TimeUnit.MILLISECONDS);

        return httpClient.build();
    }

    @Provides
    OAuthService oAuthService(Retrofit retrofit) {
        return retrofit.create(OAuthService.class);
    }

    @Provides
    NavigationService navigationService(Retrofit retrofit) {
        return retrofit.create(NavigationService.class);
    }

}