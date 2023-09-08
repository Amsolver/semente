package com.amsolver.semente.dao.implementations;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.amsolver.semente.dao.DbHelper;
import com.amsolver.semente.dao.interfaces.RefranDao;
import com.amsolver.semente.model.Refran;
import com.amsolver.semente.utilities.DateUtilities;

import java.util.Date;

public class RefranDaoImp implements RefranDao {


    @Override
    public SQLiteDatabase getDb(Context context) {
        String pathDB = context.getDatabasePath("semente.sqlite").toString();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathDB, null, SQLiteDatabase.OPEN_READWRITE);
        return db;
    }

    @SuppressLint("Range")
    @Override
    public Refran getRefranToPlay(Context context) {
        Refran refran = null;
        SQLiteDatabase db = getDb(context);

        //Buscar el primero que tenga completed = 0
        String sql = "SELECT DISTINCT * FROM " + DbHelper.TABLE_REFRANS + " WHERE completed = 0 ORDER BY number";
        try {
            Cursor c = db.rawQuery(sql,null);
            if (c.moveToFirst()) {
                refran = new Refran();
                refran.setId(c.getInt(c.getColumnIndex("id")));
                refran.setNumber(c.getInt(c.getColumnIndex("number")));
                refran.setRefran(c.getString(c.getColumnIndex("refran")));
                refran.setOpcionCorrecta(c.getString(c.getColumnIndex("opcion_correcta")));
                refran.setOpcion2(c.getString(c.getColumnIndex("opcion_2")));
                refran.setOpcion3(c.getString(c.getColumnIndex("opcion_3")));
                refran.setOpcion4(c.getString(c.getColumnIndex("opcion_4")));
                refran.setCompleted(c.getInt(c.getColumnIndex("completed")));
                refran.setDateCompleted(DateUtilities.convertFromStringToDate(c.getString(c.getColumnIndex("date_completed"))));
            }
            c.close();
        } catch (SQLiteException e) {
            Log.e("SQL.getRefranToPlay", e.getMessage());
        }
        if (refran != null) {
            return refran;
        } else {
            //Si todos tienen completed a 1, los actualizamos todos a 0, y volvemos a empezar
            markAllRefransAsNotCompleted(context);
            //Devolver el refran de id 0
            return getFirstRefranNotCompleted(context);
        }
    }

    @Override
    public boolean anyRefranResolvedToday(Context context) {
        boolean exists = false;
        SQLiteDatabase db = getDb(context);
        String date = DateUtilities.convertFromDateToString(new Date());
        String sql = "SELECT DISTINCT * FROM " + DbHelper.TABLE_REFRANS + " WHERE completed = 1 AND date_completed = '" + date + "'";
        try {
            Cursor c = db.rawQuery(sql,null);
            if (c.moveToFirst()) {
                exists = true;
            }
            c.close();
        } catch (SQLiteException e) {
            Log.e("SQL.anyRefranResolvedToday", e.getMessage());
        }
        return exists;
    }

    @SuppressLint("Range")
    @Override
    public Refran getFirstRefranNotCompleted(Context context) {
        Refran refran = null;
        SQLiteDatabase db = getDb(context);
        //Buscar el primero que tenga completed = 0
        String sql = "SELECT DISTINCT * FROM " + DbHelper.TABLE_REFRANS + " WHERE completed = 0 ORDER BY number";
        try {
            Cursor c = db.rawQuery(sql,null);
            if (c.moveToFirst()) {
                refran = new Refran();
                refran.setId(c.getInt(c.getColumnIndex("id")));
                refran.setNumber(c.getInt(c.getColumnIndex("number")));
                refran.setRefran(c.getString(c.getColumnIndex("refran")));
                refran.setOpcionCorrecta(c.getString(c.getColumnIndex("opcion_correcta")));
                refran.setOpcion2(c.getString(c.getColumnIndex("opcion_2")));
                refran.setOpcion3(c.getString(c.getColumnIndex("opcion_3")));
                refran.setOpcion4(c.getString(c.getColumnIndex("opcion_4")));
                refran.setCompleted(c.getInt(c.getColumnIndex("completed")));
            }
            c.close();
        } catch (SQLiteException e) {
            Log.e("SQL.getRefranToPlay", e.getMessage());
        }
        return refran;
    }

    @Override
    public boolean markAllRefransAsNotCompleted(Context context) {
        SQLiteDatabase db = getDb(context);
        String sql = "UPDATE " + DbHelper.TABLE_REFRANS + " set completed = 0 ";
        try {
            db.execSQL(sql);
        } catch (SQLiteException e) {
            Log.e("SQL.markAllRefransAsNotCompleted", e.getMessage());
        }
        return true;
    }

    @Override
    public boolean markARefranAsCompleted(Context context, int number) {
        SQLiteDatabase db = getDb(context);
        String date = DateUtilities.convertFromDateToString(new Date());
        String sql = "UPDATE " + DbHelper.TABLE_REFRANS + " set completed = 1, date_completed = '" + date +  "' WHERE number = " + number;
        try {
            db.execSQL(sql);
        } catch (SQLiteException e) {
            Log.e("SQL.markARefranAsCompleted", e.getMessage());
        }
        return true;
    }
}
