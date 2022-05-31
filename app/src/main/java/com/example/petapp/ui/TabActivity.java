package com.example.petapp.ui;

import android.content.Intent;
import android.os.Bundle;

import com.example.petapp.R;
import com.example.petapp.databinding.ActivityTabBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.example.petapp.ui.ui.main.SectionsPagerAdapter;

public class TabActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    private ActivityTabBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTabBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = binding.floatingActionButton;

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Hola :D", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        binding.back.setOnClickListener(v -> onBackPressed());

        binding.menuIcon.setOnClickListener(v -> {
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
}