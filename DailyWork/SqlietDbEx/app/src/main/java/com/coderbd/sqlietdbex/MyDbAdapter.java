package com.coderbd.sqlietdbex;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class MyDbAdapter {
    MyDbHelper helper;

    public MyDbAdapter(Context context) {
        this.helper = new MyDbHelper(context);
    }

    static class MyDbHelper extends SQLiteOpenHelper {
        // Database Information
        static final String DB_NAME = "PROD.DB";
        // database version
        static final int DB_VERSION = 1;

        public MyDbHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        ////////// Start ///////////
        // Table Name
        public static final String TABLE_NAME = "PRODUCT";
        // Table columns
        public static final String ID = "id";
        private static final String PRODUCT_NAME = "product_name";
        private static final String QTY = "qty";

        // Creating table query
        private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + PRODUCT_NAME + " TEXT NOT NULL, " + QTY + " INTEGER NOT NULL)";


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
        cv.put(MyDbHelper.PRODUCT_NAME, product.getProductname());
        cv.put(MyDbHelper.QTY, product.getQuantity());
        long id = db.insert(MyDbHelper.TABLE_NAME, null, cv);
        return id;
    }
    public List<Product> getList(){
        SQLiteDatabase db = helper.getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                MyDbHelper.ID,
                MyDbHelper.PRODUCT_NAME,
                MyDbHelper.QTY
        };

// Filter results WHERE "id" = 'product Id'
        String selection = MyDbHelper.ID + " = ?";


// How you want the results sorted in the resulting Cursor
        String sortOrder = MyDbHelper.PRODUCT_NAME + " DESC";

        Cursor cursor = db.query(
                MyDbHelper.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        List<Product> list = new ArrayList<>();
        while(cursor.moveToNext()) {
            Product product=new Product(cursor.getColumnIndex(MyDbHelper.ID),String.valueOf(cursor.getColumnIndex(MyDbHelper.PRODUCT_NAME)),cursor.getColumnIndex(MyDbHelper.QTY));
            list.add(product);
        }
        cursor.close();

return list;
    }
}
