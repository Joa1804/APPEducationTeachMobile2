package com.example.appeducacationteach;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appeducacationteach.adapter.AtividadeAdapter;
import com.example.appeducacationteach.dao.AtividadeDAO;
import com.example.appeducacationteach.model.Atividade;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class MainProject extends AppCompatActivity {

    private RecyclerView rvAtividades;
    private AtividadeAdapter adapter;
    private List<Atividade> listaAtividades = new ArrayList<>();
    private AtividadeDAO atividadeDAO;
    private Button btnNovoProjeto;
    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_project);

        atividadeDAO = new AtividadeDAO(this);
        rvAtividades = findViewById(R.id.rvAtividades);
        btnNovoProjeto = findViewById(R.id.btnNovoProjeto);
        bottomNavigation = findViewById(R.id.bottomNavigation);

        configurarRecyclerView();
        carregarAtividades();

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

        EditText edtTitulo = view.findViewById(R.id.edtTituloAtividade);
        EditText edtData = view.findViewById(R.id.edtDataEntrega);
        EditText edtEnunciado = view.findViewById(R.id.edtEnunciado);
        Spinner spnDificuldade = view.findViewById(R.id.spnDificuldade);

        String[] dificuldades = {"Fácil", "Médio", "Difícil"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dificuldades);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnDificuldade.setAdapter(spinnerAdapter);

        // Preenche com os dados atuais
        edtTitulo.setText(atividade.getTitulo());
        edtData.setText(atividade.getDataEntrega());
        edtEnunciado.setText(atividade.getEnunciado());
        for (int i = 0; i < dificuldades.length; i++) {
            if (dificuldades[i].equals(atividade.getDificuldade())) {
                spnDificuldade.setSelection(i);
                break;
            }
        }

        builder.setView(view)
                .setTitle("Editar Atividade")
                .setPositiveButton("Salvar", (dialog, which) -> {
                    atividade.setTitulo(edtTitulo.getText().toString());
                    atividade.setDataEntrega(edtData.getText().toString());
                    atividade.setEnunciado(edtEnunciado.getText().toString());
                    atividade.setDificuldade(spnDificuldade.getSelectedItem().toString());

                    if (!atividade.getTitulo().isEmpty() && !atividade.getDataEntrega().isEmpty()) {
                        if (atividadeDAO.atualizar(atividade)) {
                            Toast.makeText(this, "Atividade atualizada!", Toast.LENGTH_SHORT).show();
                            carregarAtividades();
                        }
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    private void confirmarExclusao(Atividade atividade) {
        new AlertDialog.Builder(this)
                .setTitle("Excluir Atividade")
                .setMessage("Tem certeza que deseja excluir '" + atividade.getTitulo() + "'?")
                .setPositiveButton("Sim", (dialog, which) -> {
                    if (atividadeDAO.deletar(atividade.getId())) {
                        Toast.makeText(this, "Atividade excluída!", Toast.LENGTH_SHORT).show();
                        carregarAtividades();
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }

    private void carregarAtividades() {
        listaAtividades.clear();
        listaAtividades.addAll(atividadeDAO.listar());
        adapter.notifyDataSetChanged();
    }

    private void exibirDialogNovaAtividade() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_nova_atividade, null);

        EditText edtTitulo = view.findViewById(R.id.edtTituloAtividade);
        EditText edtData = view.findViewById(R.id.edtDataEntrega);
        EditText edtEnunciado = view.findViewById(R.id.edtEnunciado);
        Spinner spnDificuldade = view.findViewById(R.id.spnDificuldade);

        String[] dificuldades = {"Fácil", "Médio", "Difícil"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dificuldades);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnDificuldade.setAdapter(spinnerAdapter);

        builder.setView(view)
                .setTitle("Nova Atividade")
                .setPositiveButton("Salvar", (dialog, which) -> {
                    String titulo = edtTitulo.getText().toString();
                    String data = edtData.getText().toString();
                    String enunciado = edtEnunciado.getText().toString();
                    String dificuldade = spnDificuldade.getSelectedItem().toString();

                    if (!titulo.isEmpty() && !data.isEmpty()) {
                        Atividade novaAt = new Atividade(titulo, enunciado, dificuldade, "", "", data);
                        if (atividadeDAO.inserir(novaAt)) {
                            Toast.makeText(this, "Atividade adicionada!", Toast.LENGTH_SHORT).show();
                            carregarAtividades();
                        }
                    } else {
                        Toast.makeText(this, "Preencha o título e a data", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    private void configurarNavegacao() {
        bottomNavigation.setSelectedItemId(R.id.nav_trilhas);
        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_inicio) {
                    startActivity(new Intent(MainProject.this, MainActivity.class));
                    return true;
                } else if (id == R.id.nav_trilhas) {
                    return true;
                } else if (id == R.id.nav_ranking) {
                    startActivity(new Intent(MainProject.this, MainRank.class));
                    return true;
                } else if (id == R.id.nav_educacao) {
                    startActivity(new Intent(MainProject.this, MainEducation.class));
                    return true;
                } else if (id == R.id.nav_configuracao) {
                    startActivity(new Intent(MainProject.this, MainSettings.class));
                    return true;
                }
                return false;
            }
        });
    }
}
