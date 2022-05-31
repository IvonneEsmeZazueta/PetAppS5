package com.example.petapp.core.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
    // Construct
    public SQLiteHelper(Context context, String nameDB, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, nameDB, factory, version);
    }
    // Create Table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE pets (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "img TEXT," +
                "bones INTEGER);");
    }

    // Update BD - Verify bd version - Drop and recreate
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS pets;");
        onCreate(db);
    }
}
