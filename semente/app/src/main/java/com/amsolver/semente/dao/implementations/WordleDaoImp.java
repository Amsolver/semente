package com.amsolver.semente.dao.implementations;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.amsolver.semente.dao.DbHelper;
import com.amsolver.semente.dao.interfaces.WordleDao;
import com.amsolver.semente.model.Wordle;

import java.util.ArrayList;
import java.util.List;

public class WordleDaoImp implements WordleDao {

    @SuppressLint("Range")
    @Override
    public Wordle getLevelByNumber(Context context, int number) {
        Wordle wordle = null;
        SQLiteDatabase db = getDb(context);
        String sql = "SELECT DISTINCT * FROM " + DbHelper.TABLE_WORDLE + " WHERE number = '" + number + "'";
        try {
            Cursor c = db.rawQuery(sql,null);
            if (c.moveToFirst()) {
                wordle = new Wordle();
                wordle.setId(c.getInt(c.getColumnIndex("id")));
                wordle.setNumber(c.getInt(c.getColumnIndex("number")));
                wordle.setWord(c.getString(c.getColumnIndex("word")));
                wordle.setCompleted(c.getInt(c.getColumnIndex("completed")));
            }
            c.close();
        } catch (SQLiteException e) {
            Log.e("SQL.getLevelByNumber", e.getMessage());
        }
        return wordle;
    }

    @Override
    public SQLiteDatabase getDb(Context context) {
        String pathDB = context.getDatabasePath("semente.sqlite").toString();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathDB, null, SQLiteDatabase.OPEN_READWRITE);
        return db;
    }

    @SuppressLint("Range")
    @Override
    public List<Wordle> getLevelsForPage(Context context, int page) {
        List<Wordle> wordles = new ArrayList<>();
        SQLiteDatabase db = getDb(context);
        String sql = "SELECT DISTINCT * FROM " + DbHelper.TABLE_WORDLE + getWhereByPage(page);
        try {
            Cursor c = db.rawQuery(sql,null);
            if (c.moveToFirst()) {
                do {
                    Wordle wordle = new Wordle();
                    wordle.setId(c.getInt(c.getColumnIndex("id")));
                    wordle.setNumber(c.getInt(c.getColumnIndex("number")));
                    wordle.setWord(c.getString(c.getColumnIndex("word")));
                    wordle.setCompleted(c.getInt(c.getColumnIndex("completed")));
                    wordles.add(wordle);
                } while (c.moveToNext());

            }
            c.close();
        } catch (SQLiteException e) {
            Log.e("SQL.getLevelsForPage", e.getMessage());
        }
        return wordles;
    }

    @Override
    public String getWhereByPage(int page) {
        int min = page * 50;
        int max = min + 50;
        return " WHERE number > " + min + " AND number <= " + max;
    }



    @Override
    public boolean markLevelAsCompleted(Context context, int number) {
        SQLiteDatabase db = getDb(context);
        String sql = "UPDATE " + DbHelper.TABLE_WORDLE + " set completed = 1 WHERE number = '" + number + "'";
        try {
            db.execSQL(sql);
        } catch (SQLiteException e) {
            Log.e("SQL.markLevelAsCompleted", e.getMessage());
        }
        return true;
    }
}
