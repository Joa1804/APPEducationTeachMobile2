package com.example.appeducacationteach;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainSettings extends AppCompatActivity {


    BottomNavigationView bottomNavigation;
    TextView btnSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_settings);

        bottomNavigation = findViewById(R.id.bottomNavigation);
        btnSair = findViewById(R.id.btnSair);

        btnSair.setOnClickListener(v -> {
            Intent intent = new Intent(MainSettings.this, MainLogin.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });

        bottomNavigation.setSelectedItemId(R.id.nav_configuracao);

        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.nav_inicio) {
                    startActivity(new Intent(MainSettings.this, MainActivity.class));
                    return true;
                } else if (id == R.id.nav_trilhas) {
                    startActivity(new Intent(MainSettings.this, MainProject.class));
                    return true;
                } else if (id == R.id.nav_ranking) {
                    startActivity(new Intent(MainSettings.this, MainRank.class));
                    return true;
                } else if (id == R.id.nav_educacao) {
                    startActivity(new Intent(MainSettings.this, MainEducation.class));
                    return true;
                } else if (id == R.id.nav_configuracao) {
                    return true; // já está aqui
                }
                return false;
            }
        });

    }

}