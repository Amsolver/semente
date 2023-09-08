package com.amsolver.semente.dao.interfaces;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.amsolver.semente.model.Image;

import java.util.List;

public interface ImageDao {
    SQLiteDatabase getDb(Context context);

    List<Image> getImagesForEmocionsLevel(Context context, String emocion);
}
