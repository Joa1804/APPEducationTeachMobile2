package com.example.appeducacationteach;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainProject extends AppCompatActivity {


    Button Casa, Rank, Configuracao, Projeto, Educacoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_project);


        Casa = findViewById(R.id.btnCasa);
        Rank = findViewById(R.id.btnRank);
        Configuracao  =findViewById(R.id.btnConfiguracao);
        Projeto = findViewById(R.id.btnDesafio);
        Educacoa = findViewById(R.id.btnEducao);

        Rank.setOnClickListener(v -> {
            Intent TelaRank = new Intent(MainProject.this, MainRank.class);
            startActivity(TelaRank);
        });

        Configuracao.setOnClickListener(v -> {
            Intent TelaConfiguracao = new Intent(MainProject.this, MainSettings.class);
            startActivity(TelaConfiguracao);
        });


        Educacoa.setOnClickListener(v -> {
            Intent TelaEducacao = new Intent(MainProject.this, MainEducation.class );
            startActivity(TelaEducacao);
        });

        Casa.setOnClickListener(v -> {
            Intent TelaCasa = new Intent(MainProject.this, MainActivity.class );
            startActivity(TelaCasa);
        });

    }

}