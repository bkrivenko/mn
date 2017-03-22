package com.example.penyok.yandextestproject.models;


public class TranslatedWord {

    private String id;
    private String lang;
    private String word;
    private String translation;
    private String is_favorite;

    public TranslatedWord() {

    }

    public TranslatedWord(String id, String lang, String word, String translation, String is_favorite) {
        this.id = id;
        this.lang = lang;
        this.word = word;
        this.translation = translation;
        this.is_favorite = is_favorite;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public boolean is_favorite() {
        if (is_favorite.equals("0")) {
            return false;
        } else {
            return true;
        }
    }

    public void setIs_favorite(String is_favorite) {
        this.is_favorite = is_favorite;
    }
}
