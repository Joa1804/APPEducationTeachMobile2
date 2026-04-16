package com.example.appeducacationteach;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainRank extends AppCompatActivity {


    Button Casa, Rank, Configuracao, Projeto, Educacoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_rank);

        Casa = findViewById(R.id.btnCasa);
        Rank = findViewById(R.id.btnRank);
        Configuracao  =findViewById(R.id.btnConfiguracao);
        Projeto = findViewById(R.id.btnDesafio);
        Educacoa = findViewById(R.id.btnEducao);

        Configuracao.setOnClickListener(v -> {
            Intent TelaConfiguracao = new Intent(MainRank.this, MainSettings.class);
            startActivity(TelaConfiguracao);
        });

        Projeto.setOnClickListener(v -> {
            Intent TelsProjeto = new Intent(MainRank.this, MainProject.class);
            startActivity(TelsProjeto);
        });

        Educacoa.setOnClickListener(v -> {
            Intent TelaEducacao = new Intent(MainRank.this, MainEducation.class );
            startActivity(TelaEducacao);
        });

        Casa.setOnClickListener(v -> {
            Intent TelaCasa = new Intent(MainRank.this, MainActivity.class );
            startActivity(TelaCasa);
        });

    }

}