package com.example.petapp.domain;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterPetsGrid extends RecyclerView.Adapter<AdapterPetsGrid.ViewHolderPets> {

    private final List<Pets> pets;
    private Intent intent;

    public AdapterPetsGrid(List<Pets> pets) {
        this.pets = pets;
    }

    @NonNull
    @Override
    public ViewHolderPets onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pet_second, null, false);
        return new ViewHolderPets(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPets holder, @SuppressLint("RecyclerView") int position) {
        Picasso.get()
                .load(pets.get(position).getImg())
                .placeholder(R.drawable.paw)
                .error(R.drawable.paw)
                .into(holder.ivPet);
        holder.countPet.setText(""+pets.get(position).getBones());
    }

    @Override
    public int getItemCount() {
        return pets.size();
    }

    public static class ViewHolderPets extends RecyclerView.ViewHolder {

        ImageView ivPet;
        TextView countPet;

        public ViewHolderPets(@NonNull View itemView) {
            super(itemView);
            ivPet = itemView.findViewById(R.id.ivPet);
            countPet = itemView.findViewById(R.id.countPet);
        }
    }
}
