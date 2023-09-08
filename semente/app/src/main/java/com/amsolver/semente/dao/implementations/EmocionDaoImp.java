package com.amsolver.semente.dao.implementations;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.amsolver.semente.dao.DbHelper;
import com.amsolver.semente.dao.interfaces.EmocionDao;
import com.amsolver.semente.model.Emocion;

import java.util.ArrayList;
import java.util.List;

public class EmocionDaoImp implements EmocionDao {


    @Override
    public SQLiteDatabase getDb(Context context) {
        String pathDB = context.getDatabasePath("semente.sqlite").toString();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathDB, null, SQLiteDatabase.OPEN_READWRITE);
        return db;
    }

    @SuppressLint("Range")
    @Override
    public List<Emocion> getLevelsForPage(Context context, int page) {
        List<Emocion> emocions = new ArrayList<>();
        SQLiteDatabase db = getDb(context);
        String sql = "SELECT DISTINCT * FROM " + DbHelper.TABLE_EMOCIONS + getWhereByPage(page);
        try {
            Cursor c = db.rawQuery(sql,null);
            if (c.moveToFirst()) {
                do {
                    Emocion emocion = new Emocion();
                    emocion.setId(c.getInt(c.getColumnIndex("id")));
                    emocion.setNumber(c.getInt(c.getColumnIndex("number")));
                    emocion.setEmocion(c.getString(c.getColumnIndex("emocion")));
                    emocion.setCompleted(c.getInt(c.getColumnIndex("completed")));
                    emocions.add(emocion);
                } while (c.moveToNext());

            }
            c.close();
        } catch (SQLiteException e) {
            Log.e("SQL.getLevelsForPage", e.getMessage());
        }
        return emocions;
    }

    @Override
    public String getWhereByPage(int page) {
        int min = page * 50;
        int max = min + 50;
        return " WHERE number > " + min + " AND number <= " + max;
    }

    @SuppressLint("Range")
    @Override
    public Emocion getLevelByNumber(Context context, int number) {
        Emocion emocion = null;
        SQLiteDatabase db = getDb(context);
        String sql = "SELECT DISTINCT * FROM " + DbHelper.TABLE_EMOCIONS + " WHERE number = '" + number + "'";
        try {
            Cursor c = db.rawQuery(sql,null);
            if (c.moveToFirst()) {
                emocion = new Emocion();
                emocion.setId(c.getInt(c.getColumnIndex("id")));
                emocion.setNumber(c.getInt(c.getColumnIndex("number")));
                emocion.setEmocion(c.getString(c.getColumnIndex("emocion")));
                emocion.setCompleted(c.getInt(c.getColumnIndex("completed")));
            }
            c.close();
        } catch (SQLiteException e) {
            Log.e("SQL.getLevelByNumber", e.getMessage());
        }
        return emocion;
    }

    @Override
    public boolean markLevelAsCompleted(Context context, int number) {
        SQLiteDatabase db = getDb(context);
        String sql = "UPDATE " + DbHelper.TABLE_EMOCIONS + " set completed = 1 WHERE number = '" + number + "'";
        try {
            db.execSQL(sql);
        } catch (SQLiteException e) {
            Log.e("SQL.markLevelAsCompleted", e.getMessage());
        }
        return true;
    }

}
