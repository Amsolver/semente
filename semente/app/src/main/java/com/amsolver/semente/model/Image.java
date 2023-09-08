package com.amsolver.semente.model;

import android.annotation.SuppressLint;
import android.database.Cursor;

import java.sql.ResultSet;

public class Image {

    public int id;
    public byte[] image;
    public String emocion;

    public Image() {
    }


    public Image(int id, byte[] image, String number) {
        this.id = id;
        this.image = image;
        this.emocion = number;
    }


    @SuppressLint("Range")
    public Image(Cursor cursor)
    {
        try {
            this.id = cursor.getInt(cursor.getColumnIndex("id"));
            this.image = cursor.getBlob(cursor.getColumnIndex("image"));
            this.emocion = cursor.getString(cursor.getColumnIndex("number"));
        } catch (Exception e) {

        }
    }

    public Image(ResultSet rs) {
        try {
            this.id = rs.getInt("id");
            this.image = rs.getBytes("image");
        } catch (Exception e) {

        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmocion() {
        return emocion;
    }

    public void setEmocion(String emocion) {
        this.emocion = emocion;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
