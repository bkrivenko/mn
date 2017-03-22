package com.example.penyok.yandextestproject.ui.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.penyok.yandextestproject.R;
import com.example.penyok.yandextestproject.ResultCallback;
import com.example.penyok.yandextestproject.TranslatorApp;
import com.example.penyok.yandextestproject.models.MyError;
import com.example.penyok.yandextestproject.models.Translation;
import com.example.penyok.yandextestproject.server.DbHelper;
import com.example.penyok.yandextestproject.ui.activities.MainActivity;
import com.example.penyok.yandextestproject.ui.adapters.ResultAdapter;
import com.example.penyok.yandextestproject.ui.adapters.TranslatedWordAdapter;
import com.example.penyok.yandextestproject.usecase.TranslateUseCase;

/**
 * Фрагмент страницы Перевода
 */


public class TranslatorFragment extends Fragment {

    private EditText mEntryField; // Поле ввода текста для перевода.
    private ImageView mDeleteText; // Очистка поля ввода текста для перевода.
    private Button mTranslate; // Кнопка для перевода текста.

    private TranslateUseCase mTranslateUseCase; // Юз кейс для перевода текста.

    private RecyclerView mRecyclerView;

    private DbHelper mDbHelper;

    public static TranslatorFragment newInstance() {
        return new TranslatorFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_translator, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);

        update();

        mEntryField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mEntryField.getText().toString().length() > 0) {
                    changeWidgetVisibility(View.VISIBLE);
                } else {
                    changeWidgetVisibility(View.INVISIBLE);
                }
            }
        });

        mEntryField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((actionId == EditorInfo.IME_ACTION_DONE) || ((event.getKeyCode() == KeyEvent.KEYCODE_ENTER)
                        && (event.getAction() == KeyEvent.ACTION_DOWN))) {
                    hideKeyboard();
                    return true;
                }
                return false;
            }
        });

        mDeleteText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEntryField.setText("");
                changeWidgetVisibility(View.INVISIBLE);
                update();
            }
        });

        mTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
                final String lang = ((MainActivity) getActivity()).getLang();
                mTranslateUseCase.translateText(lang, mEntryField.getText().toString(), new ResultCallback<Translation>() {
                    @Override
                    public void onComplete(Translation result) {
                        mRecyclerView.setAdapter(new ResultAdapter(result.getText()));
                    }

                    @Override
                    public void onError(MyError error) {

                    }
                });
            }
        });
    }

    /**
     * Инициализация виджетов
     */
    private void init(View v) {
        mDbHelper = ((MainActivity) getActivity()).getDbHelper();
        mTranslateUseCase = new TranslateUseCase(((TranslatorApp) getActivity().getApplication())
                .getServerLoader(), mDbHelper);

        mEntryField = (EditText) v.findViewById(R.id.et_translator_entry_field);
        mDeleteText = (ImageView) v.findViewById(R.id.iv_translator_delete_text);
        mTranslate = (Button) v.findViewById(R.id.button_translator_translate);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.rv_translator_result);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    /**
     * Изменение видимости кнопки "перевести" и "очистить поле"
     * @param value принимает значение VISIBLE, INVISIBLE или GONE
     */

    private void changeWidgetVisibility(int value) {
        mDeleteText.setVisibility(value);
        mTranslate.setVisibility(value);
    }

    /**
     * Скрыть клавиатуру в поле ввода текста для перевода
     */
    private void hideKeyboard() {
        InputMethodManager in = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(mEntryField.getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * Обновление данных в списке
     */
    public void update() {
        mRecyclerView.setAdapter(new TranslatedWordAdapter(mDbHelper.getMainTranslatedList(), mDbHelper));
    }
}
