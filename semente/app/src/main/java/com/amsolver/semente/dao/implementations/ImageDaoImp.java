package com.amsolver.semente.dao.implementations;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.amsolver.semente.dao.DbHelper;
import com.amsolver.semente.dao.interfaces.ImageDao;
import com.amsolver.semente.model.Image;

import java.util.ArrayList;
import java.util.List;

public class ImageDaoImp implements ImageDao {
    @Override
    public SQLiteDatabase getDb(Context context) {
        String pathDB = context.getDatabasePath("semente.sqlite").toString();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathDB, null, SQLiteDatabase.OPEN_READWRITE);
        return db;
    }

    @SuppressLint("Range")
    @Override
    public List<Image> getImagesForEmocionsLevel(Context context, String emocion) {
        List<Image> images = new ArrayList<>();
        SQLiteDatabase db = getDb(context);
        String sql = "SELECT DISTINCT * FROM " + DbHelper.TABLE_EMOCIONS_IMAGES + " WHERE emocion = '" + emocion + "'";
        try {
            Cursor c = db.rawQuery(sql,null);
            if (c.moveToFirst()) {
                do {
                    Image image = new Image();
                    image.setId(c.getInt(c.getColumnIndex("id")));
                    image.setImage(c.getBlob(c.getColumnIndex("image")));
                    image.setEmocion(c.getString(c.getColumnIndex("emocion")));
                    images.add(image);
                } while (c.moveToNext());

            }
            c.close();
        } catch (SQLiteException e) {
            Log.e("SQL.getImagesForEmocionsLevel", e.getMessage());
        }
        return images;
    }
}
