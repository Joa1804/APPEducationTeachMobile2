package com.example.appeducacationteach;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    Button Casa, Rank, Configuracao, Projeto, Educacoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Casa = findViewById(R.id.btnCasa);
        Rank = findViewById(R.id.btnRank);
        Configuracao  =findViewById(R.id.btnConfiguracao);
        Projeto = findViewById(R.id.btnDesafio);
        Educacoa = findViewById(R.id.btnEducao);

        Rank.setOnClickListener(v -> {
            Intent TelaRank = new Intent(MainActivity.this, MainRank.class);
            startActivity(TelaRank);
        });

        Configuracao.setOnClickListener(v -> {
            Intent TelaConfiguracao = new Intent(MainActivity.this, MainSettings.class);
            startActivity(TelaConfiguracao);
        });

        Projeto.setOnClickListener(v -> {
            Intent TelsProjeto = new Intent(MainActivity.this, MainProject.class);
            startActivity(TelsProjeto);
        });

        Educacoa.setOnClickListener(v -> {
            Intent TelaEducacao = new Intent(MainActivity.this, MainEducation.class );
            startActivity(TelaEducacao);
        });

    }

}