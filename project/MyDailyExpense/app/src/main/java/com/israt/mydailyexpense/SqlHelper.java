package com.israt.mydailyexpense;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SqlHelper extends SQLiteOpenHelper {


    private static  String DTABASE_NAME = "Daily_expenses";
    public static  String TABLE_NAME = "Expense";
    public static String COL_ID = "Id";
    public static String COL_TYPE = "Type";
    public static String COL_AMOUNT = "Amount";
    public static String COL_DATE = "Date";
    public static String COL_TIME = "Time";
//    public static String COL_IMAGE = "Image";
    private static int VERSION = 1;

    private String createTable = "create table "+TABLE_NAME+"(Id Integer Primary Key autoincrement,Type Text,Amount int,Date Text,Time Text)";

    public SqlHelper(@Nullable Context context) {
        super(context, TABLE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(createTable);
    }

    public long  insertData(String type,int amount,String date,String time){

        ContentValues values = new ContentValues();
        values.put(COL_TYPE,type);
        values.put(COL_AMOUNT,amount);
        values.put(COL_DATE,date);
        values.put(COL_TIME,time);
//        values.put(COL_IMAGE,image);

        SQLiteDatabase sql = getWritableDatabase();
        long id =sql.insert(TABLE_NAME,null,values);
        sql.close();
        return id;
 }

    public Cursor showData(String sql){

        String show_query = "Select * from "+TABLE_NAME;
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(show_query,null);
        return cursor;
    }

    public void updateData(int id, String type, int amount, String date, String time){

        ContentValues values = new ContentValues();
        values.put(COL_ID,id);
        values.put(COL_TYPE,type);
        values.put(COL_AMOUNT,amount);
        values.put(COL_DATE,date);
        values.put(COL_TIME,time);
//        values.put(COL_IMAGE,image);

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.update(TABLE_NAME,values,"Id=?",new String[]{String.valueOf(id)});
        sqLiteDatabase.close();

    }

    public void deleteData(int id){
        getWritableDatabase().delete(TABLE_NAME,"Id=?",new String[]{String.valueOf(id)});
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
