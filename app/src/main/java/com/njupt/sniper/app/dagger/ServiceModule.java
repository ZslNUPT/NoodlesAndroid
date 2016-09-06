package com.njupt.sniper.app.dagger;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.njupt.sniper.app.model.service.NavigationService;
import com.njupt.sniper.app.model.service.OAuthService;
import com.njupt.sniper.app.utils.AuthorityUtils;

import org.springframework.hateoas.hal.Jackson2HalModule;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

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
    @AppScope
    protected Retrofit provideRetrofit(OkHttpClient okHttpClient, String baseUrl) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create(ObjectMapperBuilder.build()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient);
        return builder.build();
    }

    @Provides
    protected String getApiBaseUrl() {
        return "http://develop.hithinksoft.com:8787";
    }

    @Provides
    @AppScope
    protected OkHttpClient provideOkHttpClient() {
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
    @AppScope
    protected  OAuthService oAuthService(Retrofit retrofit) {
        return retrofit.create(OAuthService.class);
    }

    @Provides
    @AppScope
    protected NavigationService navigationService(Retrofit retrofit) {
        return retrofit.create(NavigationService.class);
    }

    /**
     * author：Zsl
     * date：2016/8/30
     */
    public abstract static class ObjectMapperBuilder {

        public static ObjectMapper build() {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setDateFormat(new ISO8601DateFormat());
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
            mapper.registerModule(new Jackson2HalModule());
            return mapper;
        }
    }
}