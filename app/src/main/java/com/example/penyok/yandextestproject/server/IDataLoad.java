package com.example.penyok.yandextestproject.server;


import com.example.penyok.yandextestproject.models.Translation;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 *  Запросы к базе "Яндекса"
 */

public interface IDataLoad {

    @FormUrlEncoded
    @POST("tr.json/translate?")
    Call<Translation> translateText(@Field("lang") String lang, @Field("key") String key, @Field("text") String text);
}
