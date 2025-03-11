package com.example.firstlesson.workwithsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.firstlesson.data.SinhVien;

import java.util.List;

import kotlin.Pair;
import kotlin.Triple;

public class SQLiteHelper extends SQLiteOpenHelper {
    static final String DBName = "mydb.db";
     static final int VERSION = 1;
     static final String TABLE_NAME = "NhanSu";
     static final String ID = "_id";
     static final String NAME = "name";
     static final String YOB = "yob";
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

    public Cursor displayAll() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "select * from " + TABLE_NAME;
        return db.rawQuery(query, null);
    }

    public long updateToSQL(int id, String name, int yob) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ID, id);
        cv.put(NAME, name);
        cv.put(YOB, yob);
        String where = ID + " = " + id;
        return db.update(TABLE_NAME, cv, where, null);
    }

    public long deleteFromSQL(int id) {
        SQLiteDatabase db = getWritableDatabase();
        String where = ID + " = " + id;
        return db.delete(TABLE_NAME, where, null);
    }

    public Pair<String, Integer> fetchFromID(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cr = db.rawQuery("select * from " + TABLE_NAME + " where " + ID + " = " + id, null);
        cr.moveToFirst();
        if (cr.isAfterLast()) return null;
        return new Pair<String, Integer>(cr.getString(cr.getColumnIndexOrThrow(NAME)), cr.getInt(cr.getColumnIndexOrThrow(YOB)));
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
