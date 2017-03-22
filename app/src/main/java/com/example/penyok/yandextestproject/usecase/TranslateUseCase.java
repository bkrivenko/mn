package com.example.penyok.yandextestproject.usecase;


import com.example.penyok.yandextestproject.ResultCallback;
import com.example.penyok.yandextestproject.models.MyError;
import com.example.penyok.yandextestproject.models.Translation;
import com.example.penyok.yandextestproject.server.DbHelper;
import com.example.penyok.yandextestproject.server.ServerLoader;

/**
 * Юз кейс для перевода текста.
 */

public class TranslateUseCase {

    private final ServerLoader serverLoader;
    private final DbHelper dbHelper;

    public TranslateUseCase(ServerLoader serverLoader, DbHelper dbHelper) {
        this.serverLoader = serverLoader;
        this.dbHelper = dbHelper;
    }

    /**
     * Перевести текст.
     * @param lang сторона перевода
     * @param text Текст, который необходимо перевестию
     * @param callback ответ
     */

    public void translateText(final String lang, final String text, final ResultCallback<Translation> callback) {
        serverLoader.translateText(lang, text, new ServerLoader.ServerCallback<Translation>() {
            @Override
            public void onComplete(Translation result) {
                dbHelper.addWord(lang, text, result.getText().get(0));
                callback.onComplete(result);
            }

            @Override
            public void onError(MyError error) {
                callback.onError(error);
            }
        });
    }
}
