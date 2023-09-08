package com.amsolver.semente.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;

import com.amsolver.semente.R;
import com.amsolver.semente.utilities.EmocionsUtilities;
import com.amsolver.semente.utilities.RefransUtilities;
import com.amsolver.semente.utilities.WordUtilities;

import java.io.ByteArrayOutputStream;
import java.util.List;


public class DbHelper extends SQLiteOpenHelper {

    private Context context;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "semente.sqlite";
    public static final String TABLE_WORDLE = "wordle";
    public static final String TABLE_POINTS = "points";
    public static final String TABLE_REFRANS = "refrans";
    public static final String TABLE_EMOCIONS = "emocions";
    public static final String TABLE_EMOCIONS_IMAGES = "images_emocions";


    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTables(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    private void createTables(SQLiteDatabase db) {
        createWordleTable(db);
        createPointsTable(db);
        createRefransTable(db);
        createEmocionsTable(db);
        createImagesEmocionsTable(db);
        fillWordles(db);
        fillRefrans(db);
        fillEmocions(db);
        fillImagesEmocions(db);
    }

    private void createWordleTable(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_WORDLE + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "number INTEGER," +
                "word TEXT," +
                "completed INTEGER)";
        db.execSQL(sql);
    }


    private void createPointsTable(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_POINTS + "(" +
                "id INTEGER PRIMARY KEY," +
                "points INTEGER)";
        db.execSQL(sql);
    }

    private void createRefransTable(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_REFRANS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "number INTEGER," +
                "refran TEXT," +
                "opcion_correcta TEXT," +
                "opcion_2 TEXT," +
                "opcion_3 TEXT," +
                "opcion_4 TEXT," +
                "completed INTEGER," +
                "date_completed TEXT)";
        db.execSQL(sql);
    }

    private void createEmocionsTable(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_EMOCIONS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "number INTEGER," +
                "emocion TEXT," +
                "completed INTEGER)";
        db.execSQL(sql);
    }

    private void createImagesEmocionsTable(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_EMOCIONS_IMAGES + "(" +
                "id INTEGER PRIMARY KEY," +
                "image BLOB," +
                "emocion TEXT )";
        db.execSQL(sql);
    }


    private void fillWordles(SQLiteDatabase db) {
        StringBuilder sbSql;
        List<String> words = WordUtilities.getWordList();
        for (int i = 0; i<words.size(); i++) {
            sbSql = new StringBuilder("INSERT INTO " + TABLE_WORDLE + " (id,number, word, completed) VALUES ");
            sbSql.append("(NULL").append(", ").append(i+1).append(", '").append(words.get(i)).append("', ").append(0).append(");");
            db.execSQL(sbSql.toString());
        }
    }

    private void fillRefrans(SQLiteDatabase db) {
        StringBuilder sbSql;
        List<String> refrans = RefransUtilities.getRefrans();
        List<String> opcions = RefransUtilities.getOptions();
        for (int i = 0; i<refrans.size(); i++) {
            sbSql = new StringBuilder("INSERT INTO " + TABLE_REFRANS + " (id,number, refran, opcion_correcta, opcion_2, opcion_3, opcion_4, completed, date_completed) VALUES ");
            sbSql.append("(NULL").append(", ").append(i+1).append(", '").append(refrans.get(i)).append("', '")
                    .append(opcions.get(i*4)).append("', '").append(opcions.get((4*i)+1)).append("', '").append(opcions.get((4*i)+2)).append("', '").append(opcions.get((4*i)+3)).append("', ").append(0).append(", '").append("").append("');");
            db.execSQL(sbSql.toString());
        }
    }

    private void fillEmocions(SQLiteDatabase db) {
        StringBuilder sbSql;
        List<String> emocions = EmocionsUtilities.getEmocions();
        for (int i = 0; i<emocions.size(); i++) {
            sbSql = new StringBuilder("INSERT INTO " + TABLE_EMOCIONS + " (id,number, emocion, completed) VALUES ");
            sbSql.append("(NULL").append(", ").append(i+1).append(", '").append(emocions.get(i)).append("', ").append(0).append(");");
            db.execSQL(sbSql.toString());
        }
    }

    private void fillImagesEmocions(SQLiteDatabase db) {
        String[] drawableFiles = context.getApplicationContext().getResources().getStringArray(R.array.drawable_files);
        int id = 1;
        for (String filename : drawableFiles) {
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), context.getResources().getIdentifier(filename, "drawable", context.getPackageName()));
            byte[] imageBytes = getByteArrayFromBitmap(bitmap);

            ContentValues values = new ContentValues();
            values.put("id", id);
            values.put("image", imageBytes);
            String[] emocionParts = filename.split("_");
            String emocion = emocionParts[0];
            if (emocion.equals("carino")) {
                emocion = "cariño";
            }
            values.put("emocion", emocion);

            db.insert(TABLE_EMOCIONS_IMAGES, null, values);
            id++;
        }
    }

    private byte[] getByteArrayFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }
}
