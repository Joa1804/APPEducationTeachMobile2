package com.example.appeducacationteach;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainEducation extends AppCompatActivity {


    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_education);


        bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.setSelectedItemId(R.id.nav_educacao);

        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.nav_inicio) {
                    startActivity(new Intent(MainEducation.this, MainActivity.class));
                    return true;
                } else if (id == R.id.nav_trilhas) {
                    startActivity(new Intent(MainEducation.this, MainProject.class));
                    return true;
                } else if (id == R.id.nav_ranking) {
                    startActivity(new Intent(MainEducation.this, MainRank.class));
                    return true;
                } else if (id == R.id.nav_educacao) {
                    return true;
                } else if (id == R.id.nav_configuracao) {
                    startActivity(new Intent(MainEducation.this, MainSettings.class));
                    return true;
                }
                return false;
            }
        });

    }

}