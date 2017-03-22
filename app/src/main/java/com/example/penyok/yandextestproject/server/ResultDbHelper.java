package com.example.penyok.yandextestproject.server;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class ResultDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "resultsdata";
    public static final String ID = "id";
    public static final String LANG = "lang";
    public static final String WORD = "word";
    public static final String TRANSLATION = "translation";
    public static final String IS_FAVORITE = "is_favorite";

    public ResultDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + DATABASE_NAME + " ("
                + ID + " integer primary key autoincrement,"
                + LANG + " text,"
                + WORD + " text,"
                + TRANSLATION + " text,"
                + IS_FAVORITE + " text" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
