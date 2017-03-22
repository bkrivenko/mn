package com.example.penyok.yandextestproject.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.penyok.yandextestproject.R;
import com.example.penyok.yandextestproject.server.DbHelper;
import com.example.penyok.yandextestproject.ui.activities.MainActivity;
import com.example.penyok.yandextestproject.ui.adapters.TranslatedWordAdapter;

/**
 * Фрагмент страницы Избранное
 */

public class FavoritesFragment extends Fragment {

    private DbHelper mDbHelper;
    private RecyclerView mRecyclerView;

    public static FavoritesFragment newInstance() {
        return new FavoritesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_results, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mDbHelper = ((MainActivity) getActivity()).getDbHelper();

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_results);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        update();
    }

    /**
     * Обновление данных в списке
     */
    public void update() {
        mRecyclerView.setAdapter(new TranslatedWordAdapter(mDbHelper.getFavoriteWords(), mDbHelper));
    }
}
