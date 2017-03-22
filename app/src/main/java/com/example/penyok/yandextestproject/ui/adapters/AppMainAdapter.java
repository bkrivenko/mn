package com.example.penyok.yandextestproject.ui.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.penyok.yandextestproject.ui.fragments.FavoritesFragment;
import com.example.penyok.yandextestproject.ui.fragments.HistoryFragment;
import com.example.penyok.yandextestproject.ui.fragments.TranslatorFragment;

/**
 * Адапрет основной страницы.
 */

public class AppMainAdapter extends FragmentPagerAdapter {

    private TranslatorFragment mTranslatorFragment; // Франмерт страницы переводчик.
    private HistoryFragment mHistoryFragment; // Фрагмент страницы история.
    private FavoritesFragment mFavoritesFragment; // Фрагмент страницы избранное.

    public AppMainAdapter(FragmentManager fm) {
        super(fm);
        mTranslatorFragment = TranslatorFragment.newInstance();
        mHistoryFragment = HistoryFragment.newInstance();
        mFavoritesFragment = FavoritesFragment.newInstance();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return mTranslatorFragment;
            case 1:
                return mHistoryFragment;
            case 2:
                return mFavoritesFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    public TranslatorFragment getTranslatorFragment() {
        return mTranslatorFragment;
    }

    public HistoryFragment getHistoryFragment() {
        return mHistoryFragment;
    }

    public FavoritesFragment getFavoritesFragment() {
        return mFavoritesFragment;
    }
}
