package com.njupt.sniper.app.model.service;

import com.njupt.sniper.app.model.entity.AudioEntity;

import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resources;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * author：Zsl
 * date：2016/9/7
 */
public interface AudioService{

    @GET("api/recruitmentVideos?projection=indexRecruitmentInfoVideo&type=home")
    Observable<Resources<AudioEntity>> getAudios();

    @GET("api/audios?projection=indexAudio")
    Observable<PagedResources<AudioEntity>> getPagedAudioList(@Query("page")  int page, @Query("size")  int size);
}
