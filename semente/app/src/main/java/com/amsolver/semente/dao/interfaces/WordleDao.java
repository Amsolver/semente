package com.amsolver.semente.dao.interfaces;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.amsolver.semente.model.Wordle;

import java.util.List;

public interface WordleDao {

    SQLiteDatabase getDb(Context context);

    List<Wordle> getLevelsForPage(Context context, int page);

    String getWhereByPage(int page);

    Wordle getLevelByNumber(Context context, int number);

    boolean markLevelAsCompleted(Context context, int number);
}
