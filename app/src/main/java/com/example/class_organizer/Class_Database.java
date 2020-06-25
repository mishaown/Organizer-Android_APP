package com.example.class_organizer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Class_Database extends SQLiteOpenHelper {
    private Context context;
    public static final String DATABASE_NAME = "classDatabase.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Class_table";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "Class_name";
    private static final String COLUMN_DES = "Class_des";
    private static final String COLUMN_DATE = "Class_date";

    public Class_Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION );
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_DES +  " TEXT, " +
                COLUMN_DATE + " DATETIME);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    void addClass(String class_name, String class_des, String class_date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, class_name);
        cv.put(COLUMN_DES, class_des);
        cv.put(COLUMN_DATE, class_date);

        long result = db.insert(TABLE_NAME, null, cv);

        if(result == -1) {
            Toast.makeText(context,"Failed", Toast.LENGTH_SHORT).show();
        }   else {
            Toast.makeText(context, "Added Succesfully", Toast.LENGTH_SHORT).show();
        }
    }
    Cursor readData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase dB = this.getReadableDatabase();

        Cursor cursor = null;
        if(dB != null) {
            cursor = dB.rawQuery(query, null);
        }
        return cursor;
    }
}
