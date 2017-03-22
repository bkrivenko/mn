package com.example.penyok.yandextestproject.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.penyok.yandextestproject.IAppConstants;
import com.example.penyok.yandextestproject.R;
import com.example.penyok.yandextestproject.models.preference.PreferenceProvider;
import com.example.penyok.yandextestproject.server.Languages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * Класс выбора я зыка с последующим отпавлением его в основой класс.
 */

public class LanguageSelectionActivity extends AppCompatActivity implements IAppConstants {

    private PreferenceProvider pp; // Класс для хранения внутренних данных.

    private List<String> mRecentlyUsages; // Список недавно используемых языков.
    private List<String> mLanguages; // Список всех языков.

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_selection);

        init();

        ((Toolbar) findViewById(R.id.toolbar_language_selection)).setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ListView recentlyUsed = (ListView) findViewById(R.id.lv_language_recently_used);
        recentlyUsed.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mRecentlyUsages));

        ListView languageSelection = (ListView) findViewById(R.id.lv_language_selection);
        languageSelection.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mLanguages));

        recentlyUsed.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sendLanguage(mRecentlyUsages, position);
            }
        });

        languageSelection.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sendLanguage(mLanguages, position);
            }
        });
    }

    /**
     * Инициализация виджетов.
     */

    private void init() {
        pp = new PreferenceProvider(this);

        mRecentlyUsages = new ArrayList<>(pp.getRecentlyUsedList());
        mLanguages = new Languages().getLanguagesKeys();

        mLanguages.removeAll(mRecentlyUsages);

        Collections.sort(mLanguages);
    }

    /**
     * Отправить язык в основную активность.
     *
     * @param list     список языков.
     * @param position номер языка в списке, который нужно отправить.
     */

    private void sendLanguage(List<String> list, int position) {
        Intent intent = new Intent();
        intent.putExtra(RETURN_LANGUAGE, list.get(position));
        setResult(RESULT_OK, intent);

        if (mRecentlyUsages.size() == 4) {
            mRecentlyUsages.remove(3);
        }
        mRecentlyUsages.add(list.get(position));
        pp.setRecentlyUsedList(new HashSet<>(mRecentlyUsages));

        finish();
    }
}
