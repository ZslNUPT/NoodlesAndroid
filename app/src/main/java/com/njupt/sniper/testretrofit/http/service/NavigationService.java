package com.njupt.sniper.testretrofit.http.service;

import com.njupt.sniper.testretrofit.entity.AudioEntity;
import com.njupt.sniper.testretrofit.entity.StaticsEntity;

import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resources;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * author：Zsl
 * date：2016/8/25
 */
public interface NavigationService {
    @GET("api/homes/statistics")
    Observable<StaticsEntity> getStatics();

    @GET("api/recruitmentVideos?projection=indexRecruitmentInfoVideo&type=home")
    Observable<Resources<AudioEntity>> getAudios();

    @GET("api/audios?projection=indexAudio")
    Observable<PagedResources<AudioEntity>> getPagedAudioList(@Query("page")  int page, @Query("size")  int size);
}
