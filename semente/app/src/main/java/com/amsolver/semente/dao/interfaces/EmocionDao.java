package com.amsolver.semente.dao.interfaces;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.amsolver.semente.model.Emocion;

import java.util.List;

public interface EmocionDao {
    SQLiteDatabase getDb(Context context);

    List<Emocion> getLevelsForPage(Context context, int page);

    String getWhereByPage(int page);

    Emocion getLevelByNumber(Context context, int number);

    boolean markLevelAsCompleted(Context context, int number);
}
