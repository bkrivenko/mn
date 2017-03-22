package com.example.penyok.yandextestproject.models.preference;


import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

/**
 * Класс для работы с внутренней памятью приложения.
 */

public class PreferenceProvider {

    private static final String DATA = "DATA";

    private static final String APP_FIRST_LAUNCH = "APP_FIRST_LAUNCH";
    private static final String RECENTLY_USED_LANGUAGE = "RECENTLY_USED_LANGUAGE";

    private SharedPreferences mPreferences;

    public PreferenceProvider(Context context) {
        mPreferences = context.getSharedPreferences(DATA, Context.MODE_PRIVATE);
    }

    /**
     * Приложение запущено.
     */

    public void setFirstLaunch() {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putBoolean(APP_FIRST_LAUNCH, false);
        editor.apply();
    }

    /**
     * Определение произошел первый или не первый запуск приложения
     */

    public boolean isFirstLaunch() {
        return mPreferences.getBoolean(APP_FIRST_LAUNCH, true);
    }

    /**
     * Добавить недавно использованные языки
     * @param set Список языков
     */

    public void setRecentlyUsedList(Set<String> set) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putStringSet(RECENTLY_USED_LANGUAGE, set);
        editor.apply();
    }

    /**
     * Запросить недавно использованные языки
     * @return сет языков
     */
    public Set<String> getRecentlyUsedList() {
        return mPreferences.getStringSet(RECENTLY_USED_LANGUAGE, new HashSet<String>());
    }
}
