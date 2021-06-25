package com.example.sairamkrishna.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends  SQLiteOpenHelper {
    public static final String DATABASE_NAME ="mylist.db";
    public static final String TABLE_NAME ="mylist_tbl";
    public static final String COL_ID ="ID";
    public static final String COL_ITEM ="ITEM";

    public DatabaseHelper(Context context){super(context,DATABASE_NAME,null,1);}
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME+ " (" + COL_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+ COL_ITEM +" TEXT)";
        db. execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE  IF EXISTS "+TABLE_NAME);
    }
    public boolean addData(String item1){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_ITEM,item1);

        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result==-1){
            return  false;
        }
        else{
            return true;
        }
    }

    public Cursor getListContents(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor data = db.rawQuery(" SELECT * FROM " + TABLE_NAME,null);
        return data;
    }
}