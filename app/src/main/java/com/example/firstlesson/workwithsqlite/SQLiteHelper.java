package com.example.firstlesson.workwithsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DBName = "mydb.db";
    private static final int VERSION = 1;
    private static final String TABLE_NAME = "NhanSu";
    private static final String ID = "_id";
    private static final String NAME = "name";
    private static final String YOB = "yob";
    private SQLiteDatabase mydb;

    public SQLiteHelper(@Nullable Context context) {
        super(context, DBName, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table " + TABLE_NAME + " ( " +
                ID + " integer primary key autoincrement, " +
                NAME + " text not null, " +
                YOB + " integer not null )";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    /**
     * Phuong thuc de them ban ghi vao database
     */
    public long insertToSQL(String name, int yob) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, name);
        values.put(YOB, yob);
        return db.insert(TABLE_NAME, null, values);
    }

    @Override
    public synchronized void close() {
        if (mydb != null && mydb.isOpen()) {
            mydb.close();
        }
        super.close();
    }

    public Cursor DisplayAll() {
        String query = "select * from " + TABLE_NAME;
        return mydb.rawQuery(query, null);
    }

    public static String getID() {
        return ID;
    }

    public static String getNAME() {
        return NAME;
    }

    public static String getYOB() {
        return YOB;
    }

    public SQLiteDatabase getDB() {
        return mydb;
    }
}
