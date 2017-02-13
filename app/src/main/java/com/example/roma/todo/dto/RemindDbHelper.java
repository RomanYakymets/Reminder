package com.example.roma.todo.dto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;



public class RemindDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Reminders.db";

    private static final String TABLE_NAME = "remind";

    private static final String REMIND_COLUMN_ID = "_id";
    private static final String REMIND_COLUMN_TITLE = "title";
    private static final String REMIND_COLUMN_DETAILS = "details";
    private static final String REMIND_COLUMN_TYPE = "type";
    private static final String REMIND_COLUMN_DONE = "done";

    private static final String SQL_CREATE_ENTRIES =
            "create table remind ("
            + "_id integer primary key autoincrement,"
            + "title text,"
            + "details text,"
            + "type text,"
            + "done INTEGER" + ");";
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS remind";
    private SQLiteDatabase db;
    public RemindDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public ArrayList<RemindDTO> getAllReminds() {
        ArrayList<RemindDTO> array_list = new ArrayList<>();
        db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from remind", null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            array_list.add(
                    new RemindDTO(
                            res.getInt(res.getColumnIndex(REMIND_COLUMN_ID)),
                            res.getString(res.getColumnIndex(REMIND_COLUMN_TITLE)),
                            res.getString(res.getColumnIndex(REMIND_COLUMN_DETAILS)),
                            res.getString(res.getColumnIndex(REMIND_COLUMN_TYPE)),
                            res.getInt(res.getColumnIndex(REMIND_COLUMN_DONE))));
            res.moveToNext();
        }
        res.close();
        return array_list;
    }

    public ArrayList<RemindDTO> getSomeTypeReminds(String type) {
        ArrayList<RemindDTO> array_list = new ArrayList<>();
        db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from remind where "
                +REMIND_COLUMN_TYPE+" = "+"'"+type+"'", null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            array_list.add(
                    new RemindDTO(
                            res.getInt(res.getColumnIndex(REMIND_COLUMN_ID)),
                            res.getString(res.getColumnIndex(REMIND_COLUMN_TITLE)),
                            res.getString(res.getColumnIndex(REMIND_COLUMN_DETAILS)),
                            res.getString(res.getColumnIndex(REMIND_COLUMN_TYPE)),
                            res.getInt(res.getColumnIndex(REMIND_COLUMN_DONE))));
            res.moveToNext();
        }
        res.close();
        return array_list;
    }

    public boolean insertRemind (String title, String details, String type) {
        db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(REMIND_COLUMN_TITLE, title);
        contentValues.put(REMIND_COLUMN_DETAILS, details);
        contentValues.put(REMIND_COLUMN_TYPE, type);
        contentValues.put(REMIND_COLUMN_DONE, 0);
        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }
    public void updateRemind(Integer _id, Integer done){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(REMIND_COLUMN_DONE, done);
        db.update(TABLE_NAME, contentValues, "_id = "+_id, null);
    }
    public void update(Integer _id, String title, String type, String details){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(REMIND_COLUMN_TITLE, title);
        contentValues.put(REMIND_COLUMN_TYPE, type);
        contentValues.put(REMIND_COLUMN_DETAILS, details);
        db.update(TABLE_NAME, contentValues, "_id = "+_id, null);
    }
    public void deleteRemind(Integer _id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "_id = "+_id, null);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}