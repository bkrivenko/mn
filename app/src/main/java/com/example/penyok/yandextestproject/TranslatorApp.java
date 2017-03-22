package com.example.penyok.yandextestproject;

import android.app.Application;

import com.example.penyok.yandextestproject.server.IDataLoad;
import com.example.penyok.yandextestproject.server.ServerLoader;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TranslatorApp extends Application {

    IDataLoad mService;

    @Override
    public void onCreate() {
        super.onCreate();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.retrofit_base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mService = retrofit.create(IDataLoad.class);
    }

    public ServerLoader getServerLoader() {
        return new ServerLoader(mService);
    }

}
