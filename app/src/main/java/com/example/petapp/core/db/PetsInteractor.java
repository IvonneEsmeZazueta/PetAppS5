package com.example.petapp.core.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PetsInteractor {
    // Objects
    SQLiteDatabase db;
    Context context;
    String tableName = "pets";
    ContentValues container = new ContentValues();

    public PetsInteractor(Context context, SQLiteDatabase db){
        this.db = db;
        this.context = context;
    }

    // New register
    public void newPet(String name, String img, int bones){
        // LE ASIGNAMOS LOS VALORES
        container.put("name", name);
        container.put("img", img);
        container.put("bones", bones);

        // Insert new data
        db.insert(tableName, null, container);
        container.clear();
    }

    // Update register
    public void updatePet(int id, int bones){
        container.put("bones", bones);

        // Update data
        String[] valueWhere = new String[]{""+id};
        db.update(tableName, container, "id=?", valueWhere);
        container.clear();
    }

    // Consult Date
    public Cursor consultAll(){
        // Obtain data db in cursor
        Cursor dataCursor = db.query(
                tableName,
                null,
                null,
                null,
                null,
                null,
                null);
        // Return data consult
        return dataCursor;
    }

    public Cursor consultTop(){
        // Obtain data db in cursor
        Cursor dataCursor = db.query(
                tableName,
                null,
                null,
                null,
                null,
                null,
                "bones DESC");
        // Return data consult
        return dataCursor;
    }
}
