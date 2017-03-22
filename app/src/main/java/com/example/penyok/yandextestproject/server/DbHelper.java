package com.example.penyok.yandextestproject.server;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.penyok.yandextestproject.models.TranslatedWord;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для работы с внутренней базой данных.
 */

public class DbHelper {

    private SQLiteDatabase mDb;

    public DbHelper(Context context) {
        mDb = new ResultDbHelper(context).getWritableDatabase();
    }

    /**
     * Добавить слово в базу
     *
     * @param lang        сторона перевода
     * @param word        слово
     * @param translation перевод
     */
    public void addWord(String lang, String word, String translation) {
        ContentValues cv = new ContentValues();
        cv.put(ResultDbHelper.LANG, lang);
        cv.put(ResultDbHelper.WORD, word);
        cv.put(ResultDbHelper.TRANSLATION, translation);
        cv.put(ResultDbHelper.IS_FAVORITE, false);
        mDb.insert(ResultDbHelper.DATABASE_NAME, null, cv);
    }

    /**
     * Обновить избранное
     *
     * @param id        идентификатор слов
     * @param isChecked избранное или нет
     */

    public void updateFavorite(String id, boolean isChecked) {
        ContentValues cv = new ContentValues();
        cv.put(ResultDbHelper.IS_FAVORITE, (isChecked ? "1" : "0"));
        mDb.update(ResultDbHelper.DATABASE_NAME, cv, ResultDbHelper.ID + " = ?", new String[]{id});
    }

    /**
     * @return 5 последних переведенных слов.
     */

    public List<TranslatedWord> getMainTranslatedList() {
        Cursor c = mDb.query(false, ResultDbHelper.DATABASE_NAME, null, null, null, null, null,
                ResultDbHelper.ID + " DESC", "5");
        return fillList(new ArrayList<TranslatedWord>(), c);
    }

    /**
     * @return вся история запросов.
     */

    public List<TranslatedWord> getAllWords() {
        Cursor c = mDb.query(ResultDbHelper.DATABASE_NAME, null, null, null, null, null, null);
        return fillList(new ArrayList<TranslatedWord>(), c);
    }


    /**
     * @return список избранных слов.
     */
    public List<TranslatedWord> getFavoriteWords() {
        Cursor c = mDb.query(ResultDbHelper.DATABASE_NAME, null, ResultDbHelper.IS_FAVORITE + " = ?",
                new String[]{"1"}, null, null, null);
        return fillList(new ArrayList<TranslatedWord>(), c);
    }


    /**
     * Удалить все слова, которые не отмечены как "Избранное"
     */
    public void deleteWords() {
        mDb.delete(ResultDbHelper.DATABASE_NAME, ResultDbHelper.IS_FAVORITE + " = ?",
                new String[]{"0"});
    }

    /**
     * Заполниить список слов
     *
     * @param list список
     * @param c    курсор
     * @return Заполненный список.
     */
    private List<TranslatedWord> fillList(List<TranslatedWord> list, Cursor c) {
        if (c.moveToFirst()) {
            do {
                list.add(new TranslatedWord(c.getString(c.getColumnIndex(ResultDbHelper.ID)),
                        c.getString(c.getColumnIndex(ResultDbHelper.LANG)),
                        c.getString(c.getColumnIndex(ResultDbHelper.WORD)),
                        c.getString(c.getColumnIndex(ResultDbHelper.TRANSLATION)),
                        c.getString(c.getColumnIndex(ResultDbHelper.IS_FAVORITE))));
            } while (c.moveToNext());
        } else {
            c.close();
        }
        return list;
    }
}
