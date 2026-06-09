package com.example.appeducacationteach;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.example.appeducacationteach.adapter.AtividadeAdapter;
import com.example.appeducacationteach.dao.AtividadeDAO;
import com.example.appeducacationteach.model.Atividade;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class MainProject extends AppCompatActivity {

    Button Casa, Rank, Configuracao, Projeto, Educacoa, novaAtividade;
    LinearLayout containerProjetos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_project);

        novaAtividade = findViewById(R.id.btnNovoProjeto);
        Casa = findViewById(R.id.btnCasa);
        Rank = findViewById(R.id.btnRank);
        Configuracao  =findViewById(R.id.btnConfiguracao);
        Projeto = findViewById(R.id.btnDesafio);
        Educacoa = findViewById(R.id.btnEducao);
        containerProjetos = findViewById(R.id.containerProjetos);

        btnNovoProjeto.setOnClickListener(v -> exibirDialogNovaAtividade());

        configurarNavegacao();
    }

    private void configurarRecyclerView() {
        rvAtividades.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AtividadeAdapter(listaAtividades, atividade -> {
            Intent intent = new Intent(MainProject.this, AtividadeDetalheActivity.class);
            intent.putExtra("titulo", atividade.getTitulo());
            intent.putExtra("data", atividade.getDataEntrega());
            intent.putExtra("enunciado", atividade.getEnunciado());
            startActivity(intent);
        }, atividade -> {
            exibirOpcoesAtividade(atividade);
        });
        rvAtividades.setAdapter(adapter);
    }

    private void exibirOpcoesAtividade(Atividade atividade) {
        String[] opcoes = {"Editar", "Excluir"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Opções para: " + atividade.getTitulo())
                .setItems(opcoes, (dialog, which) -> {
                    if (which == 0) {
                        exibirDialogEditarAtividade(atividade);
                    } else if (which == 1) {
                        confirmarExclusao(atividade);
                    }
                })
                .show();
    }

    private void exibirDialogEditarAtividade(Atividade atividade) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_nova_atividade, null);

        Casa.setOnClickListener(v -> {
            Intent TelaCasa = new Intent(MainProject.this, MainActivity.class );
            startActivity(TelaCasa);
        });
        
        novaAtividade.setOnClickListener(v -> {
            mostrarDialogNovoProjeto();
        });

    }

    private void mostrarDialogNovoProjeto() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_novo_projeto, null);
        builder.setView(dialogView);

        EditText editNome = dialogView.findViewById(R.id.editNomeAtividade);
        EditText editData = dialogView.findViewById(R.id.editDataAtividade);

        // Preencher data atual por padrão
        String dataAtual = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        editData.setText(dataAtual);

        builder.setPositiveButton("Criar", (dialog, which) -> {
            String nome = editNome.getText().toString().trim();
            String data = editData.getText().toString().trim();

            if (!nome.isEmpty() && !data.isEmpty()) {
                adicionarCardProjeto(nome, data);
            } else {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancelar", null);
        builder.create().show();
    }

    private void adicionarCardProjeto(String nome, String data) {
        if (containerProjetos == null) {
            Toast.makeText(this, "Erro: Container não inicializado", Toast.LENGTH_SHORT).show();
            return;
        }

        LayoutInflater inflater = LayoutInflater.from(this);
        View viewProjeto = inflater.inflate(R.layout.item_projeto, containerProjetos, false);

        TextView txtTitulo = viewProjeto.findViewById(R.id.txtTituloProjeto);
        TextView txtData = viewProjeto.findViewById(R.id.txtDataProjeto);
        Button btnEntrar = viewProjeto.findViewById(R.id.btnEntrarProjeto);
        View card = viewProjeto.findViewById(R.id.cardProjeto);

        txtTitulo.setText(nome);
        txtData.setText(data);

        // Ao clicar no card, mostra/esconde o botão de entrar
        card.setOnClickListener(v -> {
            if (btnEntrar.getVisibility() == View.GONE) {
                btnEntrar.setVisibility(View.VISIBLE);
            } else {
                btnEntrar.setVisibility(View.GONE);
            }
        });

        // Configurar ação do botão Entrar
        btnEntrar.setOnClickListener(v -> {
            Toast.makeText(this, "Entrando em: " + nome, Toast.LENGTH_SHORT).show();
            // Aqui você pode adicionar a lógica para abrir a tela da atividade específica
        });

        containerProjetos.addView(viewProjeto);
    }

}
