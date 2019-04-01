package com.example.button;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

public class MyDbAdapter {

    MyDbHelper helper;

    public MyDbAdapter(Context context) {
        this.helper = new MyDbHelper(context);
    }

    static class MyDbHelper extends SQLiteOpenHelper {
        MyDbHelper helper;
        static final String DB_NAME = "PROD.DB";

        static final int DB_VERSION = 1;

        public MyDbHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        public static final String TABLE_NAME = "PRODUCT";
        public static final String ID = "id";
        private static String PRODUCT_NAME = "product_name";
        private static String Qty = "qty";

        // creating table query
        private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PRODUCT_NAME + "TEXT NOT NULL, " + Qty + "INTEGER NOT NULL)";


        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);

        }
    }

    public long insertData(Product product) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(MyDbHelper.PRODUCT_NAME, product.getProductName());
        cv.put(MyDbHelper.Qty, product.getQuantity());
        long id = db.insert(MyDbHelper.TABLE_NAME, null, cv);
        return id;
    }

    public List<Product> getList() {
        SQLiteDatabase db = helper.getReadableDatabase();
        String[] projection = {MyDbHelper.ID, MyDbHelper.PRODUCT_NAME, MyDbHelper.Qty};
        Cursor cursor = db.query(
                MyDbHelper.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        return null;
    }

}
