package com.example.petapp.domain;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.petapp.R;
import com.example.petapp.core.db.PetsInteractor;
import com.example.petapp.core.db.SQLiteHelper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterPets extends RecyclerView.Adapter<AdapterPets.ViewHolderPets> {

    private final List<Pets> pets;
    private SQLiteHelper sqlh;
    private SQLiteDatabase db;
    private PetsInteractor petsInteractor = null;


    public AdapterPets(List<Pets> pets) {
        this.pets = pets;
    }

    @NonNull
    @Override
    public ViewHolderPets onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pet_first, null, false);
        return new ViewHolderPets(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPets holder, @SuppressLint("RecyclerView") int position) {
        holder.namePet.setText(pets.get(position).getName());
        Picasso.get()
                .load(pets.get(position).getImg())
                .placeholder(R.drawable.paw)
                .error(R.drawable.paw)
                .into(holder.ivPet);
        holder.countPet.setText(""+pets.get(position).getBones());

        holder.ivLike.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "Me gusta - "+pets.get(position).getName(), Toast.LENGTH_SHORT).show();
            holder.ivLike.setImageResource(R.drawable.paw_blue);

            // Change like value
            // Enabled BD
            sqlh = new SQLiteHelper(v.getContext(), "pets.db", null, 1);
            // Open connection
            db = sqlh.getWritableDatabase();
            // Interactor pets
            petsInteractor = new PetsInteractor(v.getContext(), db);
            // Update in BD
            petsInteractor.updatePet(pets.get(position).getId(), pets.get(position).getBones()+1);
            db.close();

            // Change value
            holder.countPet.setText(""+(pets.get(position).getBones()+1));
        });
    }

    @Override
    public int getItemCount() {
        return pets.size();
    }

    public static class ViewHolderPets extends RecyclerView.ViewHolder {

        ImageView ivPet;
        ImageView ivLike;
        TextView namePet, countPet;

        public ViewHolderPets(@NonNull View itemView) {
            super(itemView);
            ivPet = itemView.findViewById(R.id.ivPet);
            ivLike = itemView.findViewById(R.id.ivLike);
            namePet = itemView.findViewById(R.id.namePet);
            countPet = itemView.findViewById(R.id.countPet);
        }
    }
}
