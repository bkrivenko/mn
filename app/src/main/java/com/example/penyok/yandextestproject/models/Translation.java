package com.example.penyok.yandextestproject.models;


import java.util.List;

/**
 * Модель переведенного слова.
 */

public class Translation {

    private List<String> text;

    public List<String> getText() {
        return text;
    }

    public void setText(List<String> text) {
        this.text = text;
    }
}
