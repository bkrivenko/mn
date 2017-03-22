package com.example.penyok.yandextestproject.ui.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.penyok.yandextestproject.R;

import java.util.List;

/**
 * Адаптер для вывода результатов перевода.
 */

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultViewHolder> {

    private List<String> mResults;

    public ResultAdapter(List<String> results) {
        mResults = results;
    }

    class ResultViewHolder extends RecyclerView.ViewHolder {

        TextView mAnswer;
        CheckBox mFavorite;

        ResultViewHolder(View itemView) {
            super(itemView);
            mAnswer = (TextView) itemView.findViewById(R.id.tv_result_answer);
            mFavorite = (CheckBox) itemView.findViewById(R.id.cb_result_favorites);
        }
    }

    @Override
    public ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ResultViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_result, parent, false));
    }

    @Override
    public void onBindViewHolder(ResultViewHolder holder, int position) {
        holder.mAnswer.setText(mResults.get(position));
    }

    @Override
    public int getItemCount() {
        return mResults.size();
    }

}
