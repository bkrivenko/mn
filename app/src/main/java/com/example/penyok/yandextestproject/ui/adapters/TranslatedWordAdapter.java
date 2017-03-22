package com.example.penyok.yandextestproject.ui.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.penyok.yandextestproject.R;
import com.example.penyok.yandextestproject.models.TranslatedWord;
import com.example.penyok.yandextestproject.server.DbHelper;

import java.util.List;

/**
 * Адаптер для вывода данных из базы. История запросов и Избранное.
 */

public class TranslatedWordAdapter extends RecyclerView.Adapter<TranslatedWordAdapter.ResultViewHolder> {

    private List<TranslatedWord> mResults;
    private DbHelper dbHelper;

    public TranslatedWordAdapter(List<TranslatedWord> results, DbHelper dbHelper) {
        this.mResults = results;
        this.dbHelper = dbHelper;
    }

    class ResultViewHolder extends RecyclerView.ViewHolder {

        TextView mQuestion, mAnswer, mLang;
        CheckBox mFavorite;

        ResultViewHolder(View itemView) {
            super(itemView);
            mQuestion = (TextView) itemView.findViewById(R.id.tv_result_question);
            mAnswer = (TextView) itemView.findViewById(R.id.tv_result_answer);
            mFavorite = (CheckBox) itemView.findViewById(R.id.cb_result_favorites);
            mLang = (TextView) itemView.findViewById(R.id.tv_result_lang);
            mQuestion.setVisibility(View.VISIBLE);
            mLang.setVisibility(View.VISIBLE);
            mFavorite.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ResultViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_result, parent, false));
    }

    @Override
    public void onBindViewHolder(ResultViewHolder holder, int position) {
        final TranslatedWord word = mResults.get(position);

        holder.mQuestion.setText(word.getWord());
        holder.mAnswer.setText(word.getTranslation());
        holder.mFavorite.setChecked(word.is_favorite());
        holder.mLang.setText(word.getLang());

        holder.mFavorite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                dbHelper.updateFavorite(word.getId(), isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mResults.size();
    }
}
