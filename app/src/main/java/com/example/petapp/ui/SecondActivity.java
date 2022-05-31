package com.example.petapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.petapp.R;
import com.example.petapp.core.Data;
import com.example.petapp.core.db.PetsInteractor;
import com.example.petapp.core.db.SQLiteHelper;
import com.example.petapp.domain.AdapterPets;
import com.example.petapp.domain.Pet;
import com.example.petapp.domain.Pets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    private RecyclerView recyclerView;
    private ImageView back;
    private ImageView menuIcon;
    private Pet localData;
    private AdapterPets adapter;

    private SQLiteHelper sqlh;
    private SQLiteDatabase db;
    private PetsInteractor petsInteractor = null;

    private int rows = 0;
    private List<Pets> listPets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Enabled BD
        sqlh = new SQLiteHelper(this, "pets.db", null, 1);

        recyclerView = findViewById(R.id.recyclerView);
        back = findViewById(R.id.back);
        menuIcon = findViewById(R.id.menuIcon);

        String dataPets = Data.DATA_PET_FAVORITE;

        localData = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().fromJson(dataPets, Pet.class);

        /*// For LOCAL DATA
        if (localData.getPets().size() > 0) {
            adapter = new AdapterPets(localData.getPets());

            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        }*/

        // For DB DATA
        consultDataDB();

        back.setOnClickListener(v -> onBackPressed());

        menuIcon.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(this, v);
            popupMenu.setOnMenuItemClickListener(this);
            MenuInflater inflater = popupMenu.getMenuInflater();
            inflater.inflate(R.menu.menu, popupMenu.getMenu());
            popupMenu.show();
        });
    }

    @Override
    public boolean onMenuItemClick(MenuItem menu){
        switch (menu.getItemId()){
            case R.id.contact:
                startActivity(new Intent(getApplicationContext(), MailActivity.class));
                return true;
            case R.id.aboutMe:
                startActivity(new Intent(getApplicationContext(), InfoActivity.class));
                return true;
            default:
                return false;
        }
    }

    private void consultDataDB() {
        // Open connection
        db = sqlh.getWritableDatabase();
        // Interactor pets
        petsInteractor = new PetsInteractor(getApplicationContext(), db);
        Cursor resultCursor = petsInteractor.consultTop();

        if(obtainDataDB(resultCursor) > 0){
            adapter = new AdapterPets(listPets);

            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        } else {
            Toast.makeText(getApplicationContext(), "No hay Datos", Toast.LENGTH_SHORT).show();
        }
    }

    private int obtainDataDB(Cursor cursor){
        listPets = null;
        // Obtain Cursor Size
        rows = cursor.getCount();
        listPets = new ArrayList<>();

        // Fill list pets
        cursor.moveToFirst();
        for(int count2 = 5; count2 < rows; count2++){
            Pets pets = new Pets();
            pets.setId(cursor.getInt(0));
            pets.setName(cursor.getString(1));
            pets.setImg(cursor.getString(2));
            pets.setBones(cursor.getInt(3));

            listPets.add(pets);
            cursor.moveToNext();
        }
        return rows;
    }
}