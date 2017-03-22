package com.example.penyok.yandextestproject.server;


import com.example.penyok.yandextestproject.models.MyError;
import com.example.penyok.yandextestproject.models.Translation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Загрузчик данных с сервера.
 */
public class ServerLoader {

    private static final String API_KEY = "trnsl.1.1.20170319T201952Z.cf3dd35775447932.6e27556ca1d41e05d9bc73398cf393c348e5a97e"; // ключ яндекса

    public interface ServerCallback<T> {
        void onComplete(T result);

        void onError(MyError error);
    }

    private final IDataLoad service;

    public ServerLoader(IDataLoad service) {
        this.service = service;
    }


    /**
     * Перевести текст
     *
     * @param lang     направление перевода
     * @param text     слово, которое необходимо перевести
     * @param callback ответ перевода
     */
    public void translateText(String lang, String text, final ServerCallback<Translation> callback) {
        service.translateText(lang, API_KEY, text).enqueue(new Callback<Translation>() {
            @Override
            public void onResponse(Call<Translation> call, Response<Translation> response) {
                if (response.isSuccessful()) {
                    callback.onComplete(response.body());
                } else {
                    callback.onError(MyError.SERVER_ERROR);
                }
            }

            @Override
            public void onFailure(Call<Translation> call, Throwable t) {
                callback.onError(MyError.SERVER_ERROR);
            }
        });
    }
}
