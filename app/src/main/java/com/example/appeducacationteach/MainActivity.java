package com.example.appeducacationteach;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import android.view.MenuItem;
import com.google.android.material.navigation.NavigationBarView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.navigationevent.NavigationEventDispatcherOwner;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class MainActivity extends AppCompatActivity {

    Button Login;
    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Login = findViewById(R.id.btnLogin);

        Login.setOnClickListener(v -> {
            Intent TelaLogin = new Intent(MainActivity.this, MainLogin.class);
            startActivity(TelaLogin);
        });

        bottomNavigation = findViewById(R.id.bottomNavigation);

        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                int id = item.getItemId();

                if (id == R.id.nav_inicio) {
                    return true;

                } else if (id == R.id.nav_trilhas) {
                    Intent Telaprojeto = new Intent(MainActivity.this, MainProject.class);
                    startActivity(Telaprojeto);
                    return true;

                } else if (id == R.id.nav_ranking) {
                    Intent Telarank = new Intent(MainActivity.this, MainRank.class);
                    startActivity(Telarank);
                    return true;

                } else if (id == R.id.nav_educacao) {
                    Intent Telaeducacao = new Intent(MainActivity.this, MainEducation.class);
                    startActivity(Telaeducacao);
                    return true;

                } else if (id == R.id.nav_configuracao) {
                    Intent TelaConfiguracao = new Intent(MainActivity.this, MainSettings.class);
                    startActivity(TelaConfiguracao);
                    return true;

                }

                return false;
            }
        });
    }
}
