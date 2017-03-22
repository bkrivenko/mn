package com.example.penyok.yandextestproject;


import com.example.penyok.yandextestproject.models.MyError;

public interface ResultCallback<T> {

    void onComplete(T result);
    void onError(MyError error);
}
