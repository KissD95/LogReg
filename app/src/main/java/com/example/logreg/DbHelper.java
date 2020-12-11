package com.example.logreg;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME="felhasznalo.db";
    public static final int DB_VERSION=1;

    public static final String TABLE_NAME="felhasznalok";
    public static final String COL_ID="id";
    public static final String COL_EMAIL="email";
    public static final String COL_FELHNEV="felhnev";
    public static final String COL_JELSZO="jelszo";
    public static final String COL_TELJESNEV="teljesnev";


    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql ="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+ "("+
            COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_EMAIL +" VARCHAR NOT NULL," +
            COL_FELHNEV+ " VARCHAR NOT NULL," +
            COL_JELSZO+ " VARCHAR NOT NULL," +
            COL_TELJESNEV+ " VARCHAR NOT NULL"+
                ")";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql="DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }
}
