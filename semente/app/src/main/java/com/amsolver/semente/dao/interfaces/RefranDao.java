package com.amsolver.semente.dao.interfaces;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.amsolver.semente.model.Refran;

public interface RefranDao {
    SQLiteDatabase getDb(Context context);

    Refran getRefranToPlay(Context context);

    boolean anyRefranResolvedToday(Context context);

    Refran getFirstRefranNotCompleted(Context context);

    boolean markAllRefransAsNotCompleted(Context context);

    boolean markARefranAsCompleted(Context context, int number);
}
