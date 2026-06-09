package com.example.appeducacationteach;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AtividadeDetalheActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividade_detalhe);

        TextView tvTitulo = findViewById(R.id.tvDetalheTitulo);
        TextView tvData = findViewById(R.id.tvDetalheData);
        TextView tvEnunciado = findViewById(R.id.tvDetalheEnunciado);
        EditText edtResolucao = findViewById(R.id.edtResolucao);
        Button btnEnviar = findViewById(R.id.btnEnviarAtividade);
        Button btnVoltar = findViewById(R.id.btnVoltar);

        String titulo = getIntent().getStringExtra("titulo");
        String data = getIntent().getStringExtra("data");
        String enunciado = getIntent().getStringExtra("enunciado");

        tvTitulo.setText(titulo);
        tvData.setText("Entrega: " + data);
        tvEnunciado.setText(enunciado);

        btnEnviar.setOnClickListener(v -> {
            String resolucao = edtResolucao.getText().toString();
            if (!resolucao.isEmpty()) {
                Toast.makeText(this, "Atividade enviada com sucesso!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Por favor, anexe sua resolução antes de enviar.", Toast.LENGTH_SHORT).show();
            }
        });

        btnVoltar.setOnClickListener(v -> finish());
    }
}
