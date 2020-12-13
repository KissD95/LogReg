package com.example.logreg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



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

    public boolean Regisztracio(String email,String username,String password,String fullname){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COL_EMAIL,email);
        values.put(COL_FELHNEV,username);
        values.put(COL_JELSZO,password);
        values.put(COL_TELJESNEV,fullname);
        return db.insert(TABLE_NAME,null,values)!=1;
    }

    public Cursor bejelentkezes(String userName,String passWord) {
            SQLiteDatabase db=this.getReadableDatabase();

            return db.rawQuery("SELECT "+COL_FELHNEV+","+COL_EMAIL+","+COL_JELSZO+" FROM "+TABLE_NAME+
                    " WHERE ("+COL_FELHNEV+" OR "+COL_EMAIL+"=?) AND "+COL_JELSZO+"=?",new String[]{userName,passWord});
    }
    public Cursor userDatas(){
        SQLiteDatabase db=this.getReadableDatabase();

        return  db.query(TABLE_NAME,new String[]{COL_TELJESNEV},null,null,null,null,null);
    }

    public Cursor adatLekeredezes() {
        SQLiteDatabase db=this.getReadableDatabase();

        return db.query(TABLE_NAME,new String[]{COL_ID,COL_EMAIL,COL_FELHNEV,COL_JELSZO,COL_TELJESNEV},null,null,
                null,null,null,null);
    }
}
