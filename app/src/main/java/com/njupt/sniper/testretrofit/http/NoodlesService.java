package com.njupt.sniper.testretrofit.http;

import com.njupt.sniper.testretrofit.entity.AudioEntity;

import org.springframework.hateoas.Resources;

import retrofit2.http.GET;
import rx.Observable;

/**
 * author：Zsl
 * date：2016/8/23
 */
public interface NoodlesService {
    @GET
    Observable<Resources<AudioEntity>> getAudios();
}
