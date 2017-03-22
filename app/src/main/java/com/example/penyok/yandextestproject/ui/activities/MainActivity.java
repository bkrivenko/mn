package com.example.penyok.yandextestproject.ui.activities;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.penyok.yandextestproject.IAppConstants;
import com.example.penyok.yandextestproject.R;
import com.example.penyok.yandextestproject.models.preference.PreferenceProvider;
import com.example.penyok.yandextestproject.server.DbHelper;
import com.example.penyok.yandextestproject.server.Languages;
import com.example.penyok.yandextestproject.ui.adapters.AppMainAdapter;

import java.util.HashSet;
import java.util.Set;

/**
 * Основной класс, в котором отображаются фрагменты.
 */

public class MainActivity extends AppCompatActivity implements IAppConstants {

    private static final int FIRST_LANGUAGE_CONSTANT = 1010; // код ответа первого языка.
    private static final int SECOND_LANGUAGE_CONSTANT = 10101; // код ответа второго языка.

    private Languages mLanguages; // Класс, в котором хранятся все языки.

    private Toolbar mToolbar;
    private TabLayout mTabLayout;

    private ViewPager mViewPager;

    private View mLanguage; //Элемент, в котором размещены виджеты для работы с языками.
    private TextView mFirstLanguage, mSecondLanguage; // Первый и второй языки переводаю

    private AppMainAdapter mAppMainAdapter; //Основной адаптер, в котором распологаются фрагменты.

    private ImageView mLanguageChange; // Анимированная картинка смены языков.

    private DbHelper mDbHelper; // Класс для работы с базой SQLite.

    private MenuItem mMenuDelete; // Кнопка "Очистить историю"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        mLanguage.setVisibility(View.VISIBLE);
                        mToolbar.setTitle("");
                        break;
                    case 1:
                        mLanguage.setVisibility(View.GONE);
                        mToolbar.setTitle(R.string.history);
                        break;
                    case 2:
                        mLanguage.setVisibility(View.GONE);
                        mToolbar.setTitle(R.string.favourites);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mMenuDelete.setVisible(false);
                        break;
                    case 1:
                        mAppMainAdapter.getHistoryFragment().update();
                        mMenuDelete.setVisible(true);
                        break;
                    case 2:
                        mAppMainAdapter.getFavoritesFragment().update();
                        mMenuDelete.setVisible(false);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mLanguageChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimateChangeButton();
            }
        });

        mFirstLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, LanguageSelectionActivity.class),
                        FIRST_LANGUAGE_CONSTANT);
            }
        });

        mSecondLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, LanguageSelectionActivity.class),
                        SECOND_LANGUAGE_CONSTANT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FIRST_LANGUAGE_CONSTANT) {
            if (resultCode == RESULT_OK) {
                String s = data.getStringExtra(RETURN_LANGUAGE);
                if (s.equals(mSecondLanguage.getText().toString())) {
                    mSecondLanguage.setText(mFirstLanguage.getText().toString());
                }
                mFirstLanguage.setText(s);
            }
        }

        if (requestCode == SECOND_LANGUAGE_CONSTANT) {
            if (resultCode == RESULT_OK) {
                String s = data.getStringExtra(RETURN_LANGUAGE);
                if (s.equals(mFirstLanguage.getText().toString())) {
                    mFirstLanguage.setText(mSecondLanguage.getText().toString());
                }
                mSecondLanguage.setText(data.getStringExtra(RETURN_LANGUAGE));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        mMenuDelete = menu.findItem(R.id.menu_delete);
        mMenuDelete.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_delete:
                mDbHelper.deleteWords();
                mAppMainAdapter.getHistoryFragment().update();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Инициализация виджетов.
     */

    private void init() {
        mDbHelper = new DbHelper(this);

        mLanguages = new Languages();
        mToolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(mToolbar);

        mLanguage = findViewById(R.id.layout_main_language);

        mLanguageChange = (ImageView) findViewById(R.id.iv_main_language_change);
        mLanguageChange.setBackgroundResource(R.drawable.ic_switch_animation);

        mFirstLanguage = (TextView) findViewById(R.id.tv_main_first_language);
        mSecondLanguage = (TextView) findViewById(R.id.tv_main_second_language);

        mViewPager = (ViewPager) findViewById(R.id.vp_main);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mAppMainAdapter = new AppMainAdapter(getSupportFragmentManager()));

        mTabLayout = (TabLayout) findViewById(R.id.tl_main);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setIcon(R.drawable.ic_translater);
        mTabLayout.getTabAt(1).setIcon(R.drawable.ic_history);
        mTabLayout.getTabAt(2).setIcon(R.drawable.ic_star_border);

        PreferenceProvider pp = new PreferenceProvider(this);
        if (pp.isFirstLaunch()) {
            Set<String> words = new HashSet<>();
            words.add(mFirstLanguage.getText().toString());
            words.add(mSecondLanguage.getText().toString());
            pp.setFirstLaunch();
            pp.setRecentlyUsedList(words);
        }
    }

    public DbHelper getDbHelper() {
        return mDbHelper;
    }


    /**
     * Запуск анимации смены языков.
     */
    private void startAnimateChangeButton() {
        final AnimationDrawable ad = (AnimationDrawable) mLanguageChange.getBackground();
        ad.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                changeText();
                ad.stop();
            }
        }, 400);
    }

    /**
     * Изменение языков после анимации.
     */
    private void changeText() {
        String firstText = mFirstLanguage.getText().toString();
        String secondText = mSecondLanguage.getText().toString();

        mFirstLanguage.setText(secondText);
        mSecondLanguage.setText(firstText);
    }

    /**
     * Направление перевода.
     */
    public String getLang() {
        return mLanguages.getLanguages().get(mFirstLanguage.getText().toString()) + "-" +
                mLanguages.getLanguages().get(mSecondLanguage.getText().toString());
    }
}
